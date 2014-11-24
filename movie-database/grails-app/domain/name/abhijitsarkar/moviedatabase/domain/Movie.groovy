package name.abhijitsarkar.moviedatabase.domain

class Movie implements Comparable {

	static hasMany = [genres: String, stars: CastAndCrew]

	static constraints = {
		imdbRating nullable: true
		imdbURL nullable: true, url: true
    }

    String title
    int releaseYear
    CastAndCrew director
    float imdbRating
    // GRAILS-9256 - Cannot generate scaffolded views for java.net.URL attributes in a domain class 
    String imdbURL

    @Override
    String toString() {
        "${title}[year:${releaseYear}, genres:${genres}]"
    }

    @Override
    boolean equals(Object obj) {
        if (!this.class.isAssignableFrom(obj?.class)) {
            return false
        }

        !(this <=> obj)
    }

    @Override
    int hashCode() {
        int result = 17
        int c = 0
        int magicNum = 37

        c = title ? title.hashCode() : 0
        result = magicNum * result + c

        c = this.releaseYear ?: 0
        result = magicNum * result + c

        // Use the Spread operator to sum all hash codes
        c = genres ? genres*.hashCode().sum() : 0
        result = magicNum * result + c
    }

    @Override
    int compareTo(Object other) {
        final int EQUAL = 0
        final int GREATER = 1

        if (!this.class.isAssignableFrom(other?.class)) {
            throw new IllegalArgumentException("Invalid type parameter: ${other?.class.name}")
        }

        if (title == other.title) {
            int releaseYearDiff = (releaseYear ?: 0) - (other.releaseYear ?: 0)

            if (releaseYearDiff == 0) {
                int genreSizeDiff = (genres?.size() ?: 0) - (other.genres?.size() ?: 0)

                if (genreSizeDiff == 0) {
                    return genres?.containsAll(other.genres) ? EQUAL : GREATER
                }
                return genreSizeDiff
            }
            return releaseYearDiff
        }
        title?.hashCode() - other.title?.hashCode()
    }
}
