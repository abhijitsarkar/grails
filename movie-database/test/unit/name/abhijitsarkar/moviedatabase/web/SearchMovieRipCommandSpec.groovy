package name.abhijitsarkar.moviedatabase.web 

import grails.test.mixin.TestMixin
import grails.test.mixin.web.ControllerUnitTestMixin

import spock.lang.Specification
import spock.lang.Ignore

import name.abhijitsarkar.moviedatabase.service.MovieRipSearchService

@TestMixin(ControllerUnitTestMixin)
@Ignore
class SearchMovieRipCommandSpec extends Specification {

	def setup() {
		mockCommandObject(SearchMovieRipCommand)
	}

	def 'test that command object fields are validated'() {
		setup:
		//MovieRipSearchService service = Mock(MovieRipSearchService)
		//1 * service.search('fieldNameWithTrailingSpace', 'terminator', 100) >> []

		SearchMovieRipCommand cmd = new SearchMovieRipCommand(
			fieldName: 'fieldNameWithTrailingSpace ', fieldValue: 'terminator', max: 101)

		//cmd.movieRipSearchService = service

		expect:
		cmd.validate()

		cmd.fieldName == 'fieldNameWithTrailingSpace'
		cmd.fieldValue == 'terminator'
		cmd.max == 100
	}	
}