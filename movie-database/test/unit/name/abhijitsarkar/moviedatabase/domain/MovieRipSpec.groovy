package name.abhijitsarkar.moviedatabase.domain

import static name.abhijitsarkar.moviedatabase.domain.MovieSpecHelper.terminator2Movie

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(MovieRip)
class MovieRipSpec extends Specification {

	private MovieRip mr
	private MovieRip o

    def setup() {
    	mr = new MovieRip(movie: new MovieMock(), fileSize: 1L, fileExtension: '.mkv')
    	o = new MovieRip(movie: terminator2Movie(), fileSize: 1L, fileExtension: '.mkv')
    }

    def cleanup() {
    }

    void 'Test that calls to movie rip are delegated to movie as expected'() {
    	expect:
        mr.title == 'Terminator 2 Judgment Day'
    	mr.genres.find { it.toString() == 'Sci-Fi' }
		mr.stars.find { it.name == 'Arnold Schwarzenegger' }
		mr.releaseYear == 1991
		mr.imdbRating == 8.5f
    }

    void 'Test toString'() {
    	expect:
        mr.toString() == 'Terminator 2 Judgment Day[year:1991, genres:[Action, Sci-Fi, Thriller]]'
    }

    void 'Test compareTo'() {
    	expect:
        !o.compareTo(mr)
    }

    void 'Test that with everything else equal, compareTo result is directly proportional to release year'() {
        when:
        o.releaseYear = 1992

        then:
        o > mr
    }

    void 'Test that with everything else equal, compareTo result is directly proportional to number of genres'() {
        when:
        o.genres.remove('Thriller')

        then:
        o < mr
    }

    void 'Test that compareTo throws an exception when passed an object that is not a movie rip'() {
        when:
        mr.compareTo 'I am not a movie rip'

        then:
        thrown(IllegalArgumentException)
    }

    void 'Test equals'() {
        expect:
        o == mr
    }

    void 'Test that two movie rips are not equal when release years are different'() {
        when:
        o.releaseYear  = 1992

        then:
        o != mr
    }

    void 'Test that two movie rips are not equal when genres are different'() {
        when:
        o.genres.remove('Thriller')

        then:
        o != mr
    }

    void 'Test that equals returns false when passed an object that is not a movie rip'() {
        expect:
        mr != 'I am not a movie rip'
    }

    void 'Test hashCode'() {
        expect:
        o.hashCode() == mr.hashCode()
    }

    void 'Test that two movie rips have different hash codes when release years are different'() {
        when:
        o.releaseYear = 1992

        then:
        o.hashCode() != mr.hashCode()
    }

    void 'Test that two movie rips have different hash codes when genres are different'() {
        when:
        o.genres.remove('Thriller')

        then:
        o.hashCode() != mr.hashCode()
    }
}
