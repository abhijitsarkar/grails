package name.abhijitsarkar.moviedatabase.web

import static org.springframework.http.HttpStatus.OK
import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.BAD_REQUEST
import static org.springframework.http.HttpStatus.NOT_FOUND

import org.springframework.http.HttpStatus

import name.abhijitsarkar.moviedatabase.domain.MovieRip

class MovieRipController {

	def index(final SearchMovieRipCommand cmd) {
		final Map<String, SearchMovieRipCommand> resp = ['command': cmd]

		cmd.search()

		respond(resp as Map, [model: resp])
	}

	def save(final IndexMovieRipCommand cmd) {
		HttpStatus responseStatus = null
		final Map<String, IndexMovieRipCommand> resp = ['command': cmd]

		if (cmd.hasErrors()) {
			responseStatus = BAD_REQUEST
		} else {
			cmd.index()
			responseStatus = CREATED
		}

		respond(resp as Map, [status: responseStatus, view: 'create', model: resp])    
	}

	def show(final MovieRip m) {
		final Map<String, MovieRip> resp = ['movieRip': m]

		if (m) {			
			respond(resp as Map, [model: resp])
		} else {
			respond(resp as Map, [status: NOT_FOUND, view: 'error', model: resp])
		}
	}

	def create() {
		respond([status: OK])
	}
}
