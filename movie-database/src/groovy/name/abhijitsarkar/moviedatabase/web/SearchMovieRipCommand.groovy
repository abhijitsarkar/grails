package name.abhijitsarkar.moviedatabase.web

import groovy.transform.ToString

import org.grails.databinding.BindUsing
import grails.validation.Validateable

import name.abhijitsarkar.moviedatabase.service.MovieRipSearchService
import name.abhijitsarkar.moviedatabase.domain.MovieRip

@Validateable
@ToString
class SearchMovieRipCommand {

	MovieRipSearchService movieRipSearchService

	/* GOTCHA ALERT: data binding is not triggered if the parameter is not present in the request */

	@BindUsing({
        obj, source -> source['fieldName']?.trim()
    })
	String fieldName

	@BindUsing({
        obj, source -> source['fieldValue']?.trim() ?: ''
    })
	String fieldValue

	@BindUsing({
        obj, source ->  Math.min(source['max'] ?: 10, 100) 
    })
	int max = 10

	Collection<MovieRip> search() {
		log.debug("fieldName: ${fieldName}, fieldValue: ${fieldValue}, max: ${max}.")

		getMovieRipSearchService().search(fieldName, fieldValue, max)
	}
}