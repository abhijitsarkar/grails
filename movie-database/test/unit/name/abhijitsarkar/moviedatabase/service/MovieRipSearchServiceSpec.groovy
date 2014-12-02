package name.abhijitsarkar.moviedatabase.service

import spock.lang.Specification

import grails.test.mixin.TestFor
import grails.test.mixin.Mock

import name.abhijitsarkar.moviedatabase.domain.MovieRip

@TestFor(MovieRipSearchService)
@Mock(MovieRip)
class MovieRipSearchServiceSpec extends Specification {

	/* Can't use setupSpec. Tries to save before GORM is initialized and throws the following error:
       "Method on class [name.abhijitsarkar.moviedatabase.domain.MovieRip] was used outside of a Grails application."
    */
    def setup() {
         MovieRip m = new MovieRip(
     		title: 'A Beautiful Mind',            
            genres: ['Drama'] as Set,
            releaseYear: 2001,
            fileSize: 1L,
            fileExtension: '.mkv'
         )

         save(m)

         m = new MovieRip(
        	title: 'The Exorcist',            
        	genres: ['Horror'] as Set,
        	releaseYear: 1973,
        	fileSize: 1L,
        	fileExtension: '.mkv'
         )

         save(m)
    }

    private save(final MovieRip m) {
    	m.save(failOnError: true, validate: true, flush: true) // flush is critical without which tests fail
    }

    void "test that search is limited to a field if fieldName is not null"() {
    	when:
    	Collection<MovieRip> results = service.search('title', 'beautiful', 10)

    	then:
    	results?.size() == 1
    	results[0]?.title == 'A Beautiful Mind'
    }

    void "test that search returns all movie rips is fieldName is null"() {
    	when:
    	Collection<MovieRip> results = service.search(null, null, 10)

    	then:
    	results?.size() == 2
    	results[0]?.title == 'A Beautiful Mind'
    	results[1]?.title == 'The Exorcist'
    }

    void "test that search result is not null even when no match is found"() {
        when:
        Collection<MovieRip> results = service.search('title', 'junk', 10)

        then:
        !results
    }
}
