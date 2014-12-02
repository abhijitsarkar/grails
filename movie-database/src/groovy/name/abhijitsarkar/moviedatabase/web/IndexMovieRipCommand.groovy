package name.abhijitsarkar.moviedatabase.web

import groovy.transform.ToString

import grails.validation.Validateable

import name.abhijitsarkar.moviedatabase.service.MovieRipIndexService

@Validateable
@ToString
class IndexMovieRipCommand {

	static constraints = {
	    movieDirectory nullable: false
	}

	MovieRipIndexService movieRipIndexService

	String movieDirectory

	int index() {
		movieRipIndexService.index(movieDirectory)
	}
}