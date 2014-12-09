package name.abhijitsarkar.moviedatabase.web 

import spock.lang.Specification

import name.abhijitsarkar.moviedatabase.service.MovieRipSearchService

class SearchMovieRipCommandSpec extends Specification {

	def 'test that equals considers only fieldName and fieldValue'() {
		when:
		SearchMovieRipCommand cmd1 = new SearchMovieRipCommand(
			fieldName: 'f1 ', fieldValue: 'v1', max: 1)
		SearchMovieRipCommand cmd2 = new SearchMovieRipCommand(
			fieldName: 'f1 ', fieldValue: 'v1', max: 2)

		then:
		cmd1 == cmd2

		when:
		cmd1 = new SearchMovieRipCommand(
			fieldName: 'f1 ', fieldValue: 'v1', max: 1)
		cmd2 = new SearchMovieRipCommand(
			fieldName: 'f2 ', fieldValue: 'v1', max: 1)

		then:
		cmd1 != cmd2

		when:
		cmd1 = new SearchMovieRipCommand(
			fieldName: 'f1 ', fieldValue: 'v1', max: 1)
		cmd2 = new SearchMovieRipCommand(
			fieldName: 'f1 ', fieldValue: 'v2', max: 1)

		then:
		cmd1 != cmd2
	}

	def 'test that hashCode considers only fieldName and fieldValue'() {
		when:
		SearchMovieRipCommand cmd1 = new SearchMovieRipCommand(
			fieldName: 'f1 ', fieldValue: 'v1', max: 1)
		SearchMovieRipCommand cmd2 = new SearchMovieRipCommand(
			fieldName: 'f1 ', fieldValue: 'v1', max: 2)

		then:
		cmd1.hashCode() == cmd2.hashCode()

		when:
		cmd1 = new SearchMovieRipCommand(
			fieldName: 'f1 ', fieldValue: 'v1', max: 1)
		cmd2 = new SearchMovieRipCommand(
			fieldName: 'f2 ', fieldValue: 'v1', max: 1)

		then:
		cmd1.hashCode() != cmd2.hashCode()

		when:
		cmd1 = new SearchMovieRipCommand(
			fieldName: 'f1 ', fieldValue: 'v1', max: 1)
		cmd2 = new SearchMovieRipCommand(
			fieldName: 'f1 ', fieldValue: 'v2', max: 1)

		then:
		cmd1.hashCode() != cmd2.hashCode()
	}
}