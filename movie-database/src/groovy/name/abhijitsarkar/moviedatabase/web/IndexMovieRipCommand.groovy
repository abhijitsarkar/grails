package name.abhijitsarkar.moviedatabase.web

import groovy.transform.ToString

import grails.validation.Validateable
import org.grails.databinding.BindUsing

import name.abhijitsarkar.moviedatabase.service.MovieRipIndexService

@Validateable
@ToString
class IndexMovieRipCommand {

	static constraints = {
	    movieDirectory nullable: false
	}

	MovieRipIndexService movieRipIndexService

	@BindUsing({
        obj, source -> source['movieDirectory']?.trim()
    })
	String movieDirectory

	int index() {
		getMovieRipIndexService().index(movieDirectory)
	}
}