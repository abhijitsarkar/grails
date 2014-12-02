package name.abhijitsarkar.moviedatabase.service

import grails.test.spock.IntegrationSpec

import spock.lang.Shared

import org.springframework.core.io.ClassPathResource
import org.springframework.transaction.TransactionStatus
import org.springframework.transaction.PlatformTransactionManager

import org.hibernate.SessionFactory
import org.hibernate.Session

class MovieRipIndexServiceIntegrationSpec extends IntegrationSpec {

	final static movieDirectory = new ClassPathResource('resources/movies').file.absolutePath
	@Shared def service

	def setupSpec() {
        service = new MovieRipIndexService()

        /* GRAILS-10538 - Test of service doesn't work with @Transactional annotation */
        service.transactionManager = Mock(PlatformTransactionManager) { 
            getTransaction(_) >> Mock(TransactionStatus) 
        }
    }

    void 'test that movie rips are parsed as expected'() {
    	setup:
        def mockSession = Mock(Session)

        def mockSessionFactory = Mock(SessionFactory) {
            getCurrentSession() >> mockSession
        }

        service.sessionFactory = mockSessionFactory

        when:
    	def count = service.index(movieDirectory)

    	then:
    	count == 3
    }
}
