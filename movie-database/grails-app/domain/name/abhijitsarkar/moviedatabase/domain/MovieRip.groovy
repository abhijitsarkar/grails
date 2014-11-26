package name.abhijitsarkar.moviedatabase.domain

class MovieRip implements Comparable {

	static constraints = {
        importFrom Movie

        parent nullable: true
    }

    long fileSize
    String fileExtension
    String parent

    /* There's no need to initialize the variable here but Grails tries to make calls from unit tests before this is initialized causing NPE.  */
    @Delegate Movie movie = new Movie()

    @Override
    String toString() {
        "${title}[year:${releaseYear}, genres:${genres}]"
    }

    @Override
    int hashCode() {
        movie.hashCode()
    }

    @Override
    boolean equals(Object obj) {
        if (!this.class.isAssignableFrom(obj?.class)) {
            return false
        }

        !(this.movie <=> obj.movie)
    }

    @Override
    int compareTo(Object o) {
        if (!this.class.isAssignableFrom(o?.class)) {
            throw new IllegalArgumentException("Invalid type parameter: ${o?.class.name}")
        }

        final Movie otherMovie = o.movie;

        movie.compareTo(otherMovie)
    }
}
