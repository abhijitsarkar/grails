package name.abhijitsarkar.moviedatabase.domain

class MovieMock extends Movie {
    MovieMock() {
        title = 'Terminator 2 Judgment Day'
        genres = [
                'Action',
                'Sci-Fi',
                'Thriller'
        ] as Set
        releaseYear = 1991
        director = new CastAndCrew('James Cameron')

        Collection<CastAndCrew> stars = [] as Set
        stars.add(new CastAndCrew('Arnold Schwarzenegger'))
        stars.add(new CastAndCrew('Linda Hamilton'))
        stars.add(new CastAndCrew('Edward Furlong'))
        stars.add(new CastAndCrew('Robert Patrick'))

        this.stars = stars

        imdbRating = 8.5f
    }
}