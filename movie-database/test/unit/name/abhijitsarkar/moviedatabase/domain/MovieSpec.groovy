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

    void 'test that properties are set as expected when a new movie is created'() {
    	expect:
        m instanceof Movie    	
        m.title == 'Terminator 2 Judgment Day'
    	m.genres.find { it.toString() == 'Sci-Fi' }
		m.stars.find { it.name == 'Arnold Schwarzenegger' }
		m.releaseYear == 1991
		m.imdbRating == 8.5f
    }

    void 'test toString'() {
    	expect:
        m.toString() == 'Terminator 2 Judgment Day[year:1991, genres:[Action, Sci-Fi, Thriller]]'
    }

    void 'test compareTo'() {
    	expect:
        !o.compareTo(m)
    }

    void 'test that with everything else equal, compareTo result is directly proportional to release year'() {
        when:
        o.releaseYear = 1992

        then:
        o > m
    }

    void 'test that with everything else equal, compareTo result is directly proportional to number of genres'() {
        when:
        o.genres.remove('Thriller')

        then:
        o < m
    }

    void 'test that compareTo throws an exception when passed an object that is not a movie'() {
        when:
        m.compareTo 'I am not a movie'

        then:
        thrown(IllegalArgumentException)
    }

    void 'test equals'() {
        expect:
        o == m
    }

    void 'test that two movies are not equal when release years are different'() {
        when:
        o.releaseYear  = 1992

        then:
        o != m
    }

    void 'test that two movies are not equal when genres are different'() {
        when:
        o.genres.remove('Thriller')

        then:
        o != m
    }

    void 'test that equals returns false when passed an object that is not a movie'() {
        expect:
        m != 'I am not a movie'
    }

    void 'test hashCode'() {
    	expect:
        m.hashCode() == o.hashCode()
    }

    void 'test that two movies have different hash codes when release years are different'() {
        when:
        o.releaseYear  = 1992

        then:
        m.hashCode() != o.hashCode()
    }

    void 'test that two movies have different hash codes when genres are different'() {
        when:
        o.genres.remove('Thriller')

        then:
        m.hashCode() != o.hashCode()
    }
}
