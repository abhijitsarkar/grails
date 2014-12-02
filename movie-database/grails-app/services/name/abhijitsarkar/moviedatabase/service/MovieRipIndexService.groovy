package name.abhijitsarkar.moviedatabase.service

import static java.nio.file.Paths.get
import static java.nio.file.Files.walk
import static java.nio.file.Files.isDirectory
import static java.nio.file.Files.size

import static org.springframework.util.Assert.isTrue

import static name.abhijitsarkar.moviedatabase.service.MovieRipParser.fileExtension
import static name.abhijitsarkar.moviedatabase.service.MovieRipParser.parse

import java.nio.file.Path
import javax.annotation.PostConstruct

import grails.transaction.Transactional

import org.hibernate.SessionFactory
import org.hibernate.Session

import name.abhijitsarkar.moviedatabase.domain.MovieRip

@Transactional
class MovieRipIndexService {

    Collection<String> genres

    Collection<String> includes

    SessionFactory sessionFactory

    private Session session

    @PostConstruct
    void postConstruct() {
        isTrue(genres, "Genres must not be null or empty.")
        isTrue(includes, "Includes must not be null or empty.")

        session = sessionFactory.getCurrentSession()

        isTrue(session, "Session must not be null.")
    }

    int index(final String movieDirectory) {
        getMovieRips().eachWithIndex { MovieRip m, int index ->
            MovieRip.save(m)

            if (!(index % 100)) {
                cleanUpSession()
            }
        }
    }

    private Collection<MovieRip> getMovieRips(final String movieDirectory) {
        log.debug("Indexing movies from ${movieDirectory}")

        final Path rootDir = get(movieDirectory)

        if (!rootDir.isAbsolute()) {
            log.warn("Path ${movieDirectory} is not absolute and is resolved to ${rootDir.toAbsolutePath().toString()}.")
        }

        String currentGenre
        final Collection<MovieRip> movieRips = [] as SortedSet

        for (Path p : walk(rootDir)) {
            final String name = p.fileName.toString()

            log.trace("Found file, path ${p.toAbsolutePath().toString()}, name ${name}.")

            if (isDirectory(p) && isGenre(name)) {
                log.debug("Setting current genre to ${name}.")

                currentGenre = name
            } else if (isMovieRip(name)) {
                final MovieRip movieRip = parse(name)

                movieRip.genres = (movieRip.genres ?: [] as Set)

                movieRip.genres << currentGenre

                movieRip.fileSize = size(p)

                final String parent = getParent(p, currentGenre, rootDir)

                if (!currentGenre?.equalsIgnoreCase(parent)) {
                    movieRip.parent = parent
                }

                log.info("Found movie ${movieRip}.")

                final boolean isUnique = movieRips.add(movieRip)

                if (!isUnique) {
                    log.info("Found duplicate movie ${movieRip}.")
                }
            }
        }

        movieRips
    }

    boolean isMovieRip(final String fileName) {
        fileExtension(fileName).toLowerCase() in includes
    }

    boolean isGenre(final String fileName) {
        fileName in genres
    }

    final String getParent(final Path path, final String currentGenre, final Path rootDirectory, final String immediateParent = null) {
        final Path parent = path.parent

        if (!isDirectory(parent) || parent?.compareTo(rootDirectory) <= 0) {
            return immediateParent
        }

        if (parent.fileName.toString().equalsIgnoreCase(currentGenre)) {
            if (isDirectory(path)) {
                return path.fileName.toString()
            }
        }

        getParent(parent, currentGenre, rootDirectory, parent.fileName.toString())
    }

    private void cleanUpSession() {
        session.flush()
        session.clear()
    }
}
