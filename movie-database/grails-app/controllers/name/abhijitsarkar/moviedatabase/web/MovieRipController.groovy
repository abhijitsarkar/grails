package name.abhijitsarkar.moviedatabase.web

import grails.transaction.Transactional

import name.abhijitsarkar.moviedatabase.domain.MovieRip
import name.abhijitsarkar.moviedatabase.service.MovieRipSearchService
import name.abhijitsarkar.moviedatabase.service.MovieRipIndexService

@Transactional
class MovieRipController {

	/* Dependency Injection gotcha: Grails uses autowiring by name; the type isn't really used. */
	MovieRipSearchService movieRipSearchService
	MovieRipIndexService movieRipIndexService

	def show(final Integer max) {
		log.debug("Params: ${params}.")

	    params.max = Math.min(max ?: 10, 100)

	    final String searchFieldName = params.fieldName?.trim()
	    final String searchFieldValue = params.fieldValue?.trim() ?: ''

	    final Collection<MovieRip> movieRips = movieRipSearchService.search(searchFieldName, searchFieldValue, params.max)

	    respond movieRips, model: [movieCount: movieRips.size()]
	}

	def save(final String movieDirectory) {
		log.debug("Params: ${params}.")

	    movieRipIndexService.index(movieDirectory)

	    respond movieDirectory
	}
}
