package name.abhijitsarkar.moviedatabase.service

import static name.abhijitsarkar.moviedatabase.service.test.MovieRipServiceTestHelper.getGenres
import static name.abhijitsarkar.moviedatabase.service.test.MovieRipServiceTestHelper.getIncludes

import grails.test.spock.IntegrationSpec

import spock.lang.Shared

import org.springframework.core.io.ClassPathResource
import org.springframework.transaction.TransactionStatus
import org.springframework.transaction.PlatformTransactionManager

class MovieRipServiceIntegrationSpec extends IntegrationSpec {

	final static movieDirectory = new ClassPathResource('resources/movies').file.absolutePath
	@Shared def service = new MovieRipService(genres: getGenres(), includes: getIncludes())

	def setupSpec() {
        service = new MovieRipService(genres: getGenres(), includes: getIncludes())

        /* GRAILS-10538 - Test of service doesn't work with @Transactional annotation */
        service.transactionManager = Mock(PlatformTransactionManager) { 
            getTransaction(_) >> Mock(TransactionStatus) 
        }
    }

    void 'test that movie rips are parsed as expected'() {
    	when:
    	def movieRips = service.getMovieRips(movieDirectory)

    	then:
    	movieRips?.size() == 3
    	movieRips.find { it.title == 'A Beautiful Mind' }
    	movieRips.find { it.title == 'The Exorcist' }
    	movieRips.find { it.title == 'Memento' }
    }
}
