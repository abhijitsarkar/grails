package name.abhijitsarkar.moviedatabase.domain

import static name.abhijitsarkar.moviedatabase.domain.MovieSpecHelper.terminator2Movie

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(Movie)
class MovieSpec extends Specification {

	private Movie m
    private Movie o

    def setup() {
    	m = new MovieMock()
        o = terminator2Movie()
    }

    def cleanup() {
    }

    void 'Test that properties are set as expected when a new movie is created'() {
    	expect:
        m instanceof Movie    	
        m.title == 'Terminator 2 Judgment Day'
    	m.genres.find { it.toString() == 'Sci-Fi' }
		m.stars.find { it.name == 'Arnold Schwarzenegger' }
		m.releaseYear == 1991
		m.imdbRating == 8.5f
    }

    void 'Test toString'() {
    	expect:
        m.toString() == 'Terminator 2 Judgment Day[year:1991, genres:[Action, Sci-Fi, Thriller]]'
    }

    void 'Test compareTo'() {
    	expect:
        !o.compareTo(m)
    }

    void 'Test that with everything else equal, compareTo result is directly proportional to release year'() {
        when:
        o.releaseYear = 1992

        then:
        o > m
    }

    void 'Test that with everything else equal, compareTo result is directly proportional to number of genres'() {
        when:
        o.genres.remove('Thriller')

        then:
        o < m
    }

    void 'Test that compareTo throws an exception when passed an object that is not a movie'() {
        when:
        m.compareTo 'I am not a movie'

        then:
        thrown(IllegalArgumentException)
    }

    void 'Test equals'() {
        expect:
        o == m
    }

    void 'Test that two movies are not equal when release years are different'() {
        when:
        o.releaseYear  = 1992

        then:
        o != m
    }

    void 'Test that two movies are not equal when genres are different'() {
        when:
        o.genres.remove('Thriller')

        then:
        o != m
    }

    void 'Test that equals returns false when passed an object that is not a movie'() {
        expect:
        m != 'I am not a movie'
    }

    void 'Test hashCode'() {
    	expect:
        m.hashCode() == o.hashCode()
    }

    void 'Test that two movies have different hash codes when release years are different'() {
        when:
        o.releaseYear  = 1992

        then:
        m.hashCode() != o.hashCode()
    }

    void 'Test that two movies have different hash codes when genres are different'() {
        when:
        o.genres.remove('Thriller')

        then:
        m.hashCode() != o.hashCode()
    }
}
