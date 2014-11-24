import java.util.Collection

import name.abhijitsarkar.moviedatabase.domain.Movie
import name.abhijitsarkar.moviedatabase.domain.CastAndCrew

class BootStrap {

    def init = { servletContext ->
    	if (Movie.count() == 0) {
    		Movie terminator2Movie = new Movie(
        		title: 'Terminator 2 Judgment Day',
		        releaseYear: 1991,
		        director: new CastAndCrew('James Cameron'),
        		imdbRating: 8.5f
    		)

    		Collection<String> genres = ['Action', 'Sci-Fi', 'Thriller'] as Set
		    terminator2Movie.genres = genres

		    Collection<CastAndCrew> stars = [] as Set
	        stars.add(new CastAndCrew('Arnold Schwarzenegger'))
	        stars.add(new CastAndCrew('Linda Hamilton'))
	        stars.add(new CastAndCrew('Edward Furlong'))
	        stars.add(new CastAndCrew('Robert Patrick'))

    		terminator2Movie.stars = stars

    		terminator2Movie.save()
    	}
    }

    def destroy = {
    }
}
