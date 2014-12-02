package name.abhijitsarkar.moviedatabase.service

import grails.transaction.Transactional

import name.abhijitsarkar.moviedatabase.domain.MovieRip

@Transactional(readOnly = true)
class MovieRipSearchService {

	Collection<MovieRip> search(final String fieldName, final String fieldValue, final int max) {
	    Collection<MovieRip> movieRips = null

	    if (fieldName) {
	    	movieRips = MovieRip.findAllByField(fieldName, fieldValue, max)
	    } else {
	    	movieRips = MovieRip.list()
	    }

	    movieRips = movieRips ?: [] as List

	    log.debug("Found ${movieRips.size()} movie rip(s).")

	    movieRips
	}
}