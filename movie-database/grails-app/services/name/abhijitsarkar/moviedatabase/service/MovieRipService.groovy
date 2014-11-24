package name.abhijitsarkar.moviedatabase.service

import static name.abhijitsarkar.moviedatabase.service.MovieRipParser.fileExtension
import static name.abhijitsarkar.moviedatabase.service.MovieRipParser.parse

import grails.transaction.Transactional

import name.abhijitsarkar.moviedatabase.domain.MovieRip

@Transactional
class MovieRipService {

    Collection<String> genres

    Collection<String> includes

    Collection<MovieRip> getMovieRips(String movieDirectory) {
        log.debug('Indexing movies from {}.', movieDirectory)

        File rootDir = new File(movieDirectory)

        if (!rootDir.isAbsolute()) {
            log.warn('Path {} is not absolute and is resolved to {}.', movieDirectory, rootDir.absolutePath)
        }

        String currentGenre
        Collection<MovieRip> movieRips = [] as SortedSet

        DirectoryStream<Path> stream = Files.newDirectoryStream(rootDir)

        for (Path p : stream) {
            File f = p.toFile()

            log.trace('Found file, path {}, name {}.', f.absolutePath, f.name)

            if (f.isDirectory() && isGenre(f.name)) {
                log.debug('Setting current genre to {}.', f.name)

                currentGenre = f.name
            } else if (isMovieRip(f.name)) {
                MovieRip movieRip = parseMovieRip(f.name)

                movieRip.genres = (movieRip.genres ?: [] as Set)

                movieRip.genres << currentGenre

                movieRip.fileSize = f.size()

                String parent = getParent(f, currentGenre, rootDir)

                if (!currentGenre?.equalsIgnoreCase(parent)) {
                    movieRip.parent = parent
                }

                log.info('Found movie {}.', movieRip)

                boolean isUnique = movieRips.add(movieRip)

                if (!isUnique) {
                    log.info('Found duplicate movie {}.', movieRip)
                }
            }
        }

        stream.close()

        movieRips
    }

    boolean isMovieRip(final String fileName) {
        fileExtension(fileName).toLowerCase() in includes
    }

    boolean isGenre(final String fileName) {
        fileName in genres
    }

    final String getParent(File file, String currentGenre, File rootDirectory, String immediateParent = null) {
        File parentFile = file.parentFile

        if (!parentFile?.isDirectory() || parentFile?.compareTo(rootDirectory) <= 0) {
            return immediateParent
        }

        if (parentFile.name.equalsIgnoreCase(currentGenre)) {
            if (file.isDirectory()) {
                return file.name
            }
        }

        getParent(parentFile, currentGenre, rootDirectory, parentFile.name)
    }
}
