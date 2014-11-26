package name.abhijitsarkar.moviedatabase.domain.test

import name.abhijitsarkar.moviedatabase.domain.Movie
import name.abhijitsarkar.moviedatabase.domain.CastAndCrew

class MovieTestHelper {

	static Movie terminator2MovieLite() {
		new Movie().with {
        	title = 'Terminator 2 Judgment Day'
            
            genres = [
                    'Sci-Fi',
                    'Action',
                    'Thriller'
            ] as Set

            releaseYear = 1991

            delegate
        }
    }

    static Movie terminator2Movie() {
        terminator2MovieLite().with {
            director = new CastAndCrew('James Cameron')

            Collection<CastAndCrew> stars = [] as Set
            stars.add(new CastAndCrew('Arnold Schwarzenegger'))
            stars.add(new CastAndCrew('Linda Hamilton'))
            stars.add(new CastAndCrew('Edward Furlong'))
            stars.add(new CastAndCrew('Robert Patrick'))

            delegate.stars = stars

            imdbRating = 8.5f

            delegate
        }
    }
}