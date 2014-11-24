package name.abhijitsarkar.moviedatabase.domain

class MovieSpecHelper {

	static Movie terminator2Movie() {
		Movie m = new Movie() 

    	m.title = 'Terminator 2 Judgment Day'
        
        m.genres = [
                'Sci-Fi',
                'Action',
                'Thriller'
        ] as Set

        m.releaseYear = 1991

        m
    }
}