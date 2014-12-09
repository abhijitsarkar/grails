package name.abhijitsarkar.moviedatabase.web

import static org.springframework.http.HttpStatus.OK
import static org.springframework.http.HttpStatus.CREATED
import static org.springframework.http.HttpStatus.BAD_REQUEST
import static org.springframework.http.HttpStatus.NOT_FOUND

import org.springframework.http.HttpStatus

import grails.plugin.cache.Cacheable

import name.abhijitsarkar.moviedatabase.domain.MovieRip

/* GRAILS-11859 - respond method gives preference to User-Agent header rather than Accept 
   GRAILS-11860 - PageFragmentCachingFilter.doFilter throws NPE (This I found was caused by an incorrect form post URL where show 
   was being called instead of save. Still NPE is unacceptable.)
*/
class MovieRipController {

	@Cacheable(value='movieRipCache')
	def index(final SearchMovieRipCommand cmd) {
		final Map<String, SearchMovieRipCommand> resp = ['command': cmd]

		cmd.search()

		respond(resp as Map, [model: resp])
	}

	/* GRAILS-11869 - Cacheable advice executes before command object is initialized, throws NPE */
	@Cacheable(value='movieRipCache')
	def save(final IndexMovieRipCommand cmd) {
		HttpStatus responseStatus = null
		final Map<String, IndexMovieRipCommand> resp = ['command': cmd]

		if (cmd.hasErrors()) {
			responseStatus = BAD_REQUEST
		} else {
			cmd.index()
			flash.message = "Indexed ${cmd.count} movie rips."

			responseStatus = CREATED
		}

		respond(resp as Map, [status: responseStatus, view: 'create', model: resp])    
	}

	@Cacheable(value='movieRipCache', key="#m != null ? m.id : ''")
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
