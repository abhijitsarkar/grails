package name.abhijitsarkar.moviedatabase.web

import grails.transaction.Transactional

import name.abhijitsarkar.moviedatabase.domain.MovieRip

@Transactional(readOnly = true)
class MovieRipController {

	def index(Integer max) {
	    params.max = Math.min(max ?: 10, 100)

	    MovieRip mr = new MovieRip()
	    mr.properties = request

	    final int count = MovieRip.count()
	    log.debug("Found ${count} movie rips.")

	    respond MovieRip.list(params), model: [movieCount: count]
	}
}
