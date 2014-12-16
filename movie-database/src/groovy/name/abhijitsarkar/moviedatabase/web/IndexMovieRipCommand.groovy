package name.abhijitsarkar.moviedatabase.web

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import grails.validation.Validateable

import name.abhijitsarkar.moviedatabase.service.MovieRipIndexService

@Validateable
@EqualsAndHashCode(includes = ['movieDirectory'])
@ToString(includes = ['movieDirectory'])
class IndexMovieRipCommand {

	static constraints = {
	    movieDirectory nullable: false
	}

	MovieRipIndexService movieRipIndexService

	String movieDirectory

	private int count

	void index() {
		count = getMovieRipIndexService().index(movieDirectory)

		log.debug("Indexed ${count} movie rips.")
	}

	int getCount() {
		count
	}
}