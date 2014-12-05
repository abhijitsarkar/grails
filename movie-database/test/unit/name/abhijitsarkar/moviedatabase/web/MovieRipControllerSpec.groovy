package name.abhijitsarkar.moviedatabase.web

import static org.springframework.http.HttpStatus.OK
import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.BAD_REQUEST
import static org.springframework.http.HttpStatus.NOT_FOUND

import static name.abhijitsarkar.moviedatabase.domain.test.MovieRipTestHelper.terminator2MovieRipLite

import grails.test.mixin.TestFor
import grails.test.mixin.Mock

import spock.lang.Specification
import spock.lang.Shared

import name.abhijitsarkar.moviedatabase.service.MovieRipSearchService
import name.abhijitsarkar.moviedatabase.service.MovieRipIndexService
import name.abhijitsarkar.moviedatabase.domain.MovieRip

@TestFor(MovieRipController)
@Mock(MovieRip)
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

    void 'test that search parameters in the request are set to expected values during command data binding'() {
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
    	controller.index()

    	then:
    	mockSearchService.verify()

    	response.status == OK.value()
    	response.json.size() == 1

        response.json.command.movieRips.size() == 1

    	response.json.command.movieRips[0].title == 'Terminator 2 Judgment Day'
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
    	controller.index()

    	then:
    	mockSearchService.verify()

    	response.status == OK.value()
    	response.json.size() == 1

    	response.json.command.movieRips.size() == 1

        response.json.command.movieRips[0].title == 'Terminator 2 Judgment Day'
    }

    void 'test that index parameter in request is set to expected value during command data binding'() {
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

    	response.status == CREATED.value()
        response.json.command.count == 1
    }

    void 'test that index returns an error if movie directory is not in the request'() {
    	when:
    	controller.save()

    	then:
    	response.status == BAD_REQUEST.value()
    }

    void 'test that when an id param is present in the request, a movie rip is looked up by id and injected in the method'() {
        setup:
        terminator2MovieRipLite().save(flush: true)
        
        params.id = 1

        when:
        controller.show()

        then:
        response.status == OK.value()

        response.json.movieRip.title == 'Terminator 2 Judgment Day'
    }

    void 'test that show returns an error if there is no movie rip for the specified id'() {
        params.id = 1

        when:
        controller.show()

        then:
        response.status == NOT_FOUND.value()
        !response.json.movieRip
    }
}
