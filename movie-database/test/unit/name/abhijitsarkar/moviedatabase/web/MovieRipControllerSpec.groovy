package name.abhijitsarkar.moviedatabase.web

import static name.abhijitsarkar.moviedatabase.domain.test.MovieRipTestHelper.terminator2MovieRipLite

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Shared

import name.abhijitsarkar.moviedatabase.service.MovieRipSearchService
import name.abhijitsarkar.moviedatabase.service.MovieRipIndexService

@TestFor(MovieRipController)
class MovieRipControllerSpec extends Specification {

	/* For unknown reason, Spock mock doesn't work; throws NPE when defining interactions in test methods */
	@Shared def mockSearchService = mockFor(MovieRipSearchService)
	@Shared def mockIndexService = mockFor(MovieRipIndexService)

    def setupSpec() {
    	SearchMovieRipCommand.metaClass.getMovieRipSearchService = { ->
    		mockSearchService.createMock()
    	}

    	IndexMovieRipCommand.metaClass.getMovieRipIndexService = { ->
    		mockIndexService.createMock()
    	}
    }

    def setup() {
    	request.method = 'GET'
        response.format = 'json'
    }

    void 'test that trailing space in the field name is trimmed during search command data binding'() {
    	setup:
    	params.fieldName = 'fieldNameWithTrailingSpace '
    	params.fieldValue = null
    	params.max = 101

    	mockSearchService.demand.search { fieldName, fieldValue, max -> 
    		assert fieldName == 'fieldNameWithTrailingSpace'
    		assert fieldValue == ''
    		assert max == 100

    		[terminator2MovieRipLite()] as List
    	}

    	when:
    	controller.show()

    	then:
    	mockSearchService.verify()

    	response.status == 200
    	response.json.size() == 1

    	response.json[0].title == 'Terminator 2 Judgment Day'
    }

    void 'test that when search parameters are not present in the request, they are assigned default values'() {
    	setup:
    	mockSearchService.demand.search { fieldName, fieldValue, max -> 
    		assert !fieldName
    		assert !fieldValue
    		assert max == 10

    		[terminator2MovieRipLite()] as List
    	}

    	when:
    	controller.show()

    	then:
    	mockSearchService.verify()

    	response.status == 200
    	response.json.size() == 1

    	response.json[0].title == 'Terminator 2 Judgment Day'
    }

    void 'test that trailing space in the movie directory path is trimmed during index command data binding'() {
    	setup:
    	params.movieDirectory = 'movieDirectoryWithTrailingSpace '

    	mockIndexService.demand.index { movieDirectory -> 
    		assert movieDirectory == 'movieDirectoryWithTrailingSpace'

    		1
    	}

    	when:
    	controller.save()

    	then:
    	mockIndexService.verify()

    	response.status == 200
    	response.json.size() == 2

    	response.json.movieDirectory == 'movieDirectoryWithTrailingSpace'
    	response.json.count == 1
    }

    void 'test that when index parameter is not present in the request, an error response is returned'() {
    	when:
    	controller.save()

    	then:
    	response.status == 400
    }
}
