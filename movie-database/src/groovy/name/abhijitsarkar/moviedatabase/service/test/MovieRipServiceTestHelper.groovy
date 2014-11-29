package name.abhijitsarkar.moviedatabase.service.test

class MovieRipServiceTestHelper {
	static Collection<String> getGenres() {
		[
            'Action and Adventure',
            'Animation',
            'Comedy',
            'Documentary',
            'Drama',
            'Horror',
            'R-Rated Mainstream Movies',
            'Romance',
            'Sci-Fi',
            'Thriller'
        ] as Set
	}

	static Collection<String> getIncludes() {
		[
            '.avi',
            '.mkv',
            '.mp4',
            '.divx',
            '.mov'
        ] as Set
	}
}