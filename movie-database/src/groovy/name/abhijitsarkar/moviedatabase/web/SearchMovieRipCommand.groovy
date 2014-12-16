package name.abhijitsarkar.moviedatabase.web

import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

import org.grails.databinding.BindUsing
import grails.validation.Validateable

import name.abhijitsarkar.moviedatabase.service.MovieRipSearchService
import name.abhijitsarkar.moviedatabase.domain.MovieRip

@Validateable
@EqualsAndHashCode(includes = ['fieldName', 'fieldValue'])
@ToString(includes = ['fieldName', 'fieldValue'])
class SearchMovieRipCommand {

	MovieRipSearchService movieRipSearchService

	String fieldName

	String fieldValue

	/* GOTCHA ALERT: data binding is not triggered if the parameter is not present in the request */
	@BindUsing({
        obj, source ->  Math.min(source['max'] ?: 10, 100) 
    })
	int max = 10

	private int count
	private Collection<MovieRip> movieRips = [] as List

	void search() {
		log.debug("fieldName: ${fieldName}, fieldValue: ${fieldValue}, max: ${max}.")

		movieRips = getMovieRipSearchService().search(fieldName, fieldValue, max)

		count = movieRips.size()

		log.debug("Found ${count} movie rips.")
	}

	Collection<MovieRip> getMovieRips() {
		movieRips
	}

	int getCount() {
		count
	}
}