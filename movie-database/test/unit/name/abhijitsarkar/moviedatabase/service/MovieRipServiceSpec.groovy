package name.abhijitsarkar.moviedatabase.service

import java.nio.file.Path
import java.nio.file.Files
import java.nio.file.Paths
import java.util.stream.Stream

import grails.test.mixin.TestFor
import grails.test.mixin.TestMixin
import grails.test.mixin.domain.DomainClassUnitTestMixin
import spock.lang.Specification
import spock.lang.Shared
import spock.lang.Ignore

import org.springframework.transaction.TransactionStatus
import org.springframework.transaction.PlatformTransactionManager

import name.abhijitsarkar.moviedatabase.domain.Movie
import name.abhijitsarkar.moviedatabase.domain.MovieRip
import name.abhijitsarkar.moviedatabase.service.MovieRipParser

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(MovieRipService)
@TestMixin(DomainClassUnitTestMixin)
@Ignore('Ignored until the domain classes are integration tested')
class MovieRipServiceSpec extends Specification {

    @Shared MovieRipService service

    def setupSpec() {
        service = new MovieRipService(genres: [
            'Action and Adventure',
            'Animation',
            'Comedy',
            'Documentary',
            'Drama',
            'Horror',
            'R-Rated Mainstream Movies',
            'Romance',
            'Sci-Fi',
            'Thriller'
        ] as Set, 
        includes: [
            '.avi',
            '.mkv',
            '.mp4',
            '.divx',
            '.mov'
        ] as Set)

        /* GRAILS-10538 - Unit test of service doesn't work with @Transactional annotation */
        service.transactionManager = Mock(PlatformTransactionManager) { 
            getTransaction(_) >> Mock(TransactionStatus) 
        }
    }

    def setup() {
    }

    def cleanup() {
    }

    void 'test that a genre is correctly identified'() {
    	expect:
        service.genres.each { service.isGenre it }.inject { acc, val -> acc && val }
    }

    void 'test that not a genre is correctly identified'() {
        expect:
        !service.isGenre('Action')
    }

    void 'test that a movie rip is correctly identified'() {
        expect:
        service.includes.each { service.isMovieRip it }.inject { acc, val -> acc && val }
    }

    void 'test that not a movie rip is correctly identified'() {
        expect:
        !service.isMovieRip('.wmv')
    }

    void 'test getParent returns parent when parent is not a directory'() {
        when:
        def p = Mock(Path)
        def parent = Mock(Path)

        p.parent >> parent

        GroovySpy(Files, global: true)

        Files.isDirectory(parent) >> false

        def rootDirectory = null

        then:
            'immediateParent' == service.getParent(p, 'currentGenre', rootDirectory, 'immediateParent')
    }

     void 'test getParent returns parent when parent is a subdirectory of root directory'() {
        when:
        def p = Mock(Path)
        def parent = Mock(Path)

        p.parent >> parent

        GroovySpy(Files, global: true)

        Files.isDirectory(parent) >> true

        def rootDirectory = null

        parent.compareTo(rootDirectory) >> -1

        then:
            'immediateParent' == service.getParent(p, 'currentGenre', rootDirectory, 'immediateParent')
    }

    void 'test getParent returns file name when parent is current genre'() {
        when:
        def p = Mock(Path)
        def parent = Mock(Path)

        p.parent >> parent

        GroovySpy(Files, global: true)

        Files.isDirectory(parent) >> true

        def rootDirectory = null

        parent.compareTo(rootDirectory) >> 1
        parent.fileName >> Paths.get('currentGenre')

        Files.isDirectory(p) >> true
        p.fileName >> Paths.get('Sci-Fi')

        then:
            'Sci-Fi' == service.getParent(p, 'currentGenre', null, 'immediateParent')
    }

    void 'test that movie rips are parsed as expected'() {
        when:
        def (rootDirectory, drama, horror, thriller, aBeautifulMind, theExorcist, memento) = (1..7).collect { Mock(Path) }
        def movieDirectory = 'movieDirectory'

        rootDirectory.isAbsolute() >> true

        drama.fileName >> Paths.get('Drama')
        aBeautifulMind.fileName >> Paths.get('A Beautiful Mind (2001).mkv')
        horror.fileName >> Paths.get('Horror')
        theExorcist.fileName >> Paths.get('The Exorcist (1973).mkv')
        thriller.fileName >> Paths.get('Thriller')
        memento.fileName >> Paths.get('Memento (2000).avi')

        GroovySpy(Files, global: true)
        Files.walk(rootDirectory) >> Stream.of(rootDirectory, drama, aBeautifulMind, horror, theExorcist, thriller, memento)

        GroovySpy(Paths, global: true)
        Paths.get(movieDirectory) >> rootDirectory

        [drama, horror, thriller].each {
            Paths.isDirectory(it) >> true
            it.parent >> rootDirectory
            it.compareTo(rootDirectory) >> -1
        }

        [aBeautifulMind, theExorcist, memento].each {
            Paths.size(it) >> 1000l
        }

        aBeautifulMind.parent >> drama
        theExorcist.parent >> horror
        memento.parent >> thriller

        def aBeautifulMindMovie = mockDomain(Movie, [
            [title: 'A Beautiful Mind', releaseYear: 2001, genres: ['Drama']]
        ])
        def theExorcistMovie = mockDomain(Movie, [
            [title: 'The Exorcist', releaseYear: 1973, genres: ['Horror']]
        ])
        def mementoMovie = mockDomain(Movie, [
            [title: 'Memento', releaseYear: 2000, genres: ['Thriller']]
        ])

        GroovySpy(MovieRipParser, global: true)

        mockDomain(MovieRip, [
            [movie: aBeautifulMindMovie, fileExtension: '.mkv', fileSize: 1000l]
        ])
        mockDomain(MovieRip, [
            [movie: theExorcistMovie, fileExtension: '.mkv', fileSize: 1000l]
        ])
        mockDomain(MovieRip, [
            [movie: mementoMovie, fileExtension: '.avi', fileSize: 1000l]
        ])

        then:
        true
    }    
}
