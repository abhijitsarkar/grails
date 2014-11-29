package name.abhijitsarkar.moviedatabase.domain

import static name.abhijitsarkar.moviedatabase.domain.test.MovieRipTestHelper.terminator2MovieRipLite
import static name.abhijitsarkar.moviedatabase.domain.test.MovieRipTestHelper.terminator2MovieRip

import grails.test.spock.IntegrationSpec

class MovieRipIntegrationSpec extends IntegrationSpec {

    void setup() {
        def terminator2MovieRip = terminator2MovieRip()
        saveCastAndCrew(terminator2MovieRip)        
        terminator2MovieRip.save(failOnError: true, flush: true)
    }

    void 'test that movie rip can be saved and found'() {
    	when:
		terminator2MovieRipLite().save(failOnError: true, flush: true)

        then:
        MovieRip.findAllByTitle('Terminator 2 Judgment Day')?.size() == 2
    }

    private saveCastAndCrew(m) {
        m.director.save(failOnError: true, flush: true)
        m.stars*.save(failOnError: true, flush: true)
    }

    void 'test that director name can be retrived using a where query'() {
        setup:
        def query = MovieRip.where {
            title == 'Terminator 2 Judgment Day' && imdbRating > 8.0f
        }

        when:
        def movieRip = query.find()

        then:
        movieRip?.director?.name == 'James Cameron'
    }

    void 'test that director name can be retrived using a criteria query'() {
        setup:
        def c = MovieRip.createCriteria()

        when:
        def results = c.get {
            eq('title', 'Terminator 2 Judgment Day')
            gt('imdbRating', 8.0f)
        }

        then:
        results?.director?.name == 'James Cameron'
    }
}
