package name.abhijitsarkar.moviedatabase.service

import grails.test.spock.IntegrationSpec

import spock.lang.Shared

import org.springframework.core.io.ClassPathResource
import org.springframework.transaction.TransactionStatus
import org.springframework.transaction.PlatformTransactionManager

class MovieRipIndexServiceIntegrationSpec extends IntegrationSpec {

    final static String movieDirectory = new ClassPathResource('resources/movies').file.absolutePath
    @Shared def service

    void setupSpec() {
        ServiceHelper serviceHelper = new ServiceHelper()
        service = new MovieRipIndexService(
                genres: serviceHelper.genres, includes: serviceHelper.includes
        )
        /* GRAILS-10538 - Test of service doesn't work with @Transactional annotation */
        service.transactionManager = Mock(PlatformTransactionManager) { 
            getTransaction(_) >> Mock(TransactionStatus) 
        }
    }

    void 'test that movie rips are parsed as expected'() {
        when:
        def count = service.index(movieDirectory)

        then:
        count == 3
    }
}
