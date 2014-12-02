package name.abhijitsarkar.moviedatabase.domain.test

import name.abhijitsarkar.moviedatabase.domain.MovieRip
import name.abhijitsarkar.moviedatabase.domain.CastAndCrew

class MovieRipTestHelper {

	static MovieRip terminator2MovieRipLite() {
		new MovieRip().with {
        	title = 'Terminator 2 Judgment Day'
            
            genres = ['Sci-Fi', 'Action', 'Thriller'] as Set

            releaseYear = 1991

            fileSize = 1L
            fileExtension = '.mkv'
            //parent = 'Sci-Fi'

            delegate
        }
    }

    static MovieRip terminator2MovieRip() {
        terminator2MovieRipLite().with {
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