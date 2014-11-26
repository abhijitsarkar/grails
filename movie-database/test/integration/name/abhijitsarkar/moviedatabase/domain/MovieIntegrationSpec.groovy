package name.abhijitsarkar.moviedatabase.domain

import static name.abhijitsarkar.moviedatabase.domain.test.MovieTestHelper.terminator2MovieLite
import static name.abhijitsarkar.moviedatabase.domain.test.MovieTestHelper.terminator2Movie

import grails.test.spock.IntegrationSpec

class MovieIntegrationSpec extends IntegrationSpec {

    void setup() {
        def terminator2Movie = terminator2Movie()
        saveCastAndCrew(terminator2Movie)        
        terminator2Movie.save(failOnError: true, flush: true)
    }

    void 'test that movie can be saved and found'() {
    	when:
		terminator2MovieLite().save(failOnError: true, flush: true)

        then:
        Movie.findAllByTitle('Terminator 2 Judgment Day')?.size() == 2
    }

    private saveCastAndCrew(m) {
        m.director.save(failOnError: true, flush: true)
        m.stars*.save(failOnError: true, flush: true)
    }

    void 'test that director name can be retrived using a where query'() {
        setup:
        def query = Movie.where {
            title == 'Terminator 2 Judgment Day' && imdbRating > 8.0f
        }

        when:
        def movie = query.find()

        then:
        movie?.director?.name == 'James Cameron'
    }

    void 'test that director name can be retrived using a criteria query'() {
        setup:
        def c = Movie.createCriteria()

        when:
        def results = c.get {
            eq('title', 'Terminator 2 Judgment Day')
            gt('imdbRating', 8.0f)
        }

        then:
        results?.director?.name == 'James Cameron'
    }
}
