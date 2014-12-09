package name.abhijitsarkar.moviedatabase.domain

import static name.abhijitsarkar.moviedatabase.domain.test.MovieRipTestHelper.terminator2MovieRipLite
import static name.abhijitsarkar.moviedatabase.domain.test.MovieRipTestHelper.terminator2MovieRip

import spock.lang.Specification

class MovieRipSpec extends Specification {

	private MovieRip m
    private MovieRip o

    void setup() {
    	m = terminator2MovieRip()
        o = terminator2MovieRipLite()
    }

    void 'test that properties are set as expected when a new movie rip is created'() {
    	expect:
        m instanceof MovieRip    	
        m.title == 'Terminator 2 Judgment Day'
    	m.genres.find { it == 'Sci-Fi' }
		m.stars.find { it.name == 'Arnold Schwarzenegger' }
		m.releaseYear == 1991
		m.imdbRating == 8.5f
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

    void 'test that compareTo throws an exception when passed an object that is not a movie rip'() {
        when:
        m.compareTo 'I am not a movie'

        then:
        thrown(IllegalArgumentException)
    }

    void 'test equals'() {
        expect:
        o == m
    }

    void 'test that two movie rips are not equal when release years are different'() {
        when:
        o.releaseYear  = 1992

        then:
        o != m
    }

    void 'test that two movie rips are not equal when genres are different'() {
        when:
        o.genres.remove('Thriller')

        then:
        o != m
    }

    void 'test that equals returns false when passed an object that is not a movie rip'() {
        expect:
        m != 'I am not a movie'
    }

    void 'test hashCode'() {
    	expect:
        m.hashCode() == o.hashCode()
    }

    void 'test that two movie rips have different hash codes when release years are different'() {
        when:
        o.releaseYear  = 1992

        then:
        m.hashCode() != o.hashCode()
    }

    void 'test that two movie rips have different hash codes when genres are different'() {
        when:
        o.genres.remove('Thriller')

        then:
        m.hashCode() != o.hashCode()
    }
}
