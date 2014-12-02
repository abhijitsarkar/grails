package name.abhijitsarkar.moviedatabase.web

class MovieRipController {

	def show(final SearchMovieRipCommand cmd) {
	    respond(cmd.search())
	}

	def save(final IndexMovieRipCommand cmd) {
		if (cmd.hasErrors()) {
			response.sendError(400)
		} else {
			respond([movieDirectory: cmd.movieDirectory, count: cmd.index()] as Map)
		}	    
	}
}
