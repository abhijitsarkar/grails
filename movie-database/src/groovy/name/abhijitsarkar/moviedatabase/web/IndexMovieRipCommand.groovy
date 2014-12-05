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

	private int count

	void index() {
		count = getMovieRipIndexService().index(movieDirectory)

		log.debug("Indexed ${count} movie rips.")
	}

	int getCount() {
		count
	}
}