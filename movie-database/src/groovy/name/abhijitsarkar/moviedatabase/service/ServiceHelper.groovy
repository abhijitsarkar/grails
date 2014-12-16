package name.abhijitsarkar.moviedatabase.service

class ServiceHelper {
    
	final Collection<String> getGenres() { 
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

    final Collection<String> getIncludes() {
        [
            '.avi',
            '.mkv',
            '.mp4',
            '.divx',
            '.mov'
        ] as Set
    }
}