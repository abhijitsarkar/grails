package name.abhijitsarkar.moviedatabase.service

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Shared

import org.springframework.transaction.TransactionStatus
import org.springframework.transaction.PlatformTransactionManager

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(MovieRipService)
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

    void 'Test that a genre is correctly identified'() {
    	expect:
        service.genres.each { service.isGenre it }.inject { acc, val -> acc && val }
    }

    void 'Test that not a genre is correctly identified'() {
        expect:
        !service.isGenre('Action')
    }

    void 'Test that a movie rip is correctly identified'() {
        expect:
        service.includes.each { service.isMovieRip it }.inject { acc, val -> acc && val }
    }

    void 'Test that not a movie rip is correctly identified'() {
        expect:
        !service.isMovieRip('.wmv')
    }

    void 'Test getParent returns parent when parent is not a directory'() {
        when:
        def f = Mock(File)
        def parentFile = Mock(File)

        1 * f.parentFile >> parentFile
        1 * parentFile.isDirectory() >> false

        def rootDirectory = null

        then:
            'immediateParent' == service.getParent(f, 'currentGenre', rootDirectory, 'immediateParent')
    }

    void 'Test getParent returns parent when parent is a subdirectory of root directory'() {
        when:
        def f = Mock(File)
        def parentFile = Mock(File)

        1 * f.parentFile >> parentFile
        1 * parentFile.isDirectory() >> true

        def rootDirectory = null

        1 * parentFile.compareTo(rootDirectory) >> -1

        then:
            'immediateParent' == service.getParent(f, 'currentGenre', rootDirectory, 'immediateParent')
    }

    void 'Test getParent returns file name when parent is current genre'() {
        when:
        def f = Mock(File)
        def parentFile = Mock(File)

        1 * f.parentFile >> parentFile
        1 * parentFile.isDirectory() >> true

        def rootDirectory = null

        1 * parentFile.compareTo(rootDirectory) >> 1
        1 * parentFile.name >> 'currentGenre'

        1 * f.isDirectory() >> true
        1 * f.name >> 'Sci-Fi'

        then:
            'Sci-Fi' == service.getParent(f, 'currentGenre', null, 'immediateParent')
    }

    void 'Test that a list of movie rips are returned from a root directory'() {
        when:
        def (rootDirectory , drama, horror, thriller) = (1..4).each { Mock(File) }

        rootDirectory.eachFileRecurse >> [drama, horror, thriller] as List

        then:
        [rootDirectory , drama, horror, thriller].each { it instanceof File }

        //rootDirectory.eachFileRecurse >> [drama, horror, thriller] as List
    }
}
