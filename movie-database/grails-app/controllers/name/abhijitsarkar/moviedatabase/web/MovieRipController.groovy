package name.abhijitsarkar.moviedatabase.web

import name.abhijitsarkar.moviedatabase.domain.MovieRip

class MovieRipController {

	def index(final SearchMovieRipCommand cmd) {
		final Collection<MovieRip> movieRips = cmd.search()

		final Map<String, Object> resp = [movieRips: movieRips, count: movieRips.size()]

	    /* The coercion to Map is required to prevent an ambiguous arguments error. */
		respond(resp as Map, [model: resp])
	}

	def save(final IndexMovieRipCommand cmd) {
		if (cmd.hasErrors()) {
			respond(null, [status: 400, view: 'error'])
		} else {
			final Map<String, String> resp = [movieDirectory: cmd.movieDirectory, count: cmd.index()]
			
			/* The coercion to Map is required to prevent an ambiguous arguments error. */
			respond(resp as Map, [model: resp])
		}	    
	}

	def show(final MovieRip m) {
		if (m) {
			final Map<String, MovieRip> resp = ['movieRip': m]
			
			respond(resp as Map, [model: resp])
		} else {
			respond(null, [status: 404, view: 'error'])
		}
	}
}
