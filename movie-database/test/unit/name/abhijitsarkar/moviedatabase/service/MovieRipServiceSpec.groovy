package name.abhijitsarkar.moviedatabase.service

import static name.abhijitsarkar.moviedatabase.service.test.MovieRipServiceTestHelper.getGenres
import static name.abhijitsarkar.moviedatabase.service.test.MovieRipServiceTestHelper.getIncludes

import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.Files

import grails.test.mixin.TestFor
import grails.test.mixin.TestMixin
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
        service = new MovieRipService(genres: getGenres(), includes: getIncludes())

        /* GRAILS-10538 - Test of service doesn't work with @Transactional annotation */
        service.transactionManager = Mock(PlatformTransactionManager) { 
            getTransaction(_) >> Mock(TransactionStatus) 
        }
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
}
