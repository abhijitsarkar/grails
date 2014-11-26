package name.abhijitsarkar.moviedatabase.domain

import static name.abhijitsarkar.moviedatabase.domain.test.MovieTestHelper.terminator2MovieLite
import static name.abhijitsarkar.moviedatabase.domain.test.MovieTestHelper.terminator2Movie

import grails.test.spock.IntegrationSpec

class MovieRipIntegrationSpec extends IntegrationSpec {

    MovieRip mr
	MovieRip o

	def setupSpec() {
        def terminator2Movie = terminator2Movie()
        def terminator2MovieLite = terminator2MovieLite()

        saveMovie(terminator2Movie)
        saveMovie(terminator2MovieLite)

		new MovieRip(movie: terminator2Movie, fileSize: 1L, fileExtension: '.mkv').save(failOnError: true, flush: true)
		new MovieRip(movie: terminator2MovieLite, fileSize: 1L, fileExtension: '.mkv').save(failOnError: true, flush: true)
	}

    private saveMovie(m) {
        saveCastAndCrew(m)
        m.save(failOnError: true, flush: true)
    }

    private saveCastAndCrew(m) {
        m?.director?.save(failOnError: true, flush: true)
        m?.stars*.save(failOnError: true, flush: true)
    }

    void setup() {
        mr = MovieRip.where {
            title == 'Terminator 2 Judgment Day' && imdbRating > 8.0f
        }.find()

        o = MovieRip.where {
            title == 'Terminator 2 Judgment Day' && imdbRating < 8.0f
        }.find()

        [mr, o].each { assert it }
    }

    void 'test nothing'() {
        expect:
        true
    }
}
