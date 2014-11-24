package name.abhijitsarkar.moviedatabase.service

import name.abhijitsarkar.moviedatabase.domain.Movie
import name.abhijitsarkar.moviedatabase.domain.MovieRip

import java.util.regex.Matcher
import java.util.regex.Pattern

class MovieRipParser {

    /*
     * The following regex matches file names with release year in parentheses,
     * something like Titanic (1997).mkv Each part of the regex is explained
     * further:
     *
     * Regex group named 'title': Matches one or more occurrences of any
     * alphabet, number or the following special characters in the movie name:
     * dash (-), apostrophe ('), comma (,), exclamation sign (!), ampersand (&), square braces ([]), full stop (.).
     * Dash needs to be first because otherwise it represents range in character classes.
     *
     * Regex group named 'year': Matches 4 digit release year within parentheses.
     *
     * Regex group named 'lastPart': Matches one or more occurrences of any character.
     */
    private static final Pattern MOVIE_NAME_WITH_RELEASE_YEAR_REGEX =
            ~/(?<title>[-',!&\[\]\.\w\s]++)(?:\((?<year>\d{4})\))?+(?<lastPart>.++)/
    
    static MovieRip parse(final String fileName) {
        String movieTitle
        int releaseYear = 0
        final String fileExtension = fileExtension(fileName)

        final Matcher matcher = fileName =~ MOVIE_NAME_WITH_RELEASE_YEAR_REGEX

        if (matcher.matches()) {
            movieTitle = title matcher
            releaseYear = year matcher
        } else {
            // Couldn't parse file name, extract as-is without file extension
            movieTitle = fileName.minus(fileExtension)
        }

        final Movie m = new Movie(title: movieTitle, releaseYear: releaseYear)

        new MovieRip(movie: m, fileExtension: fileExtension)
    }

    static String fileExtension(final String fileName) {
        final String fileExtension = fileName.tokenize('.').last()

        fileExtension != fileName ? ".${fileExtension}".toString() : ''
    }

    private static String title(final Matcher matcher) {
        final String fileExtension = fileExtension matcher.group()
        final String qualifier = qualifier(matcher, fileExtension)
        String title = matcher.group('title')

        title += (qualifier == fileExtension ? '' : qualifier)

        title.trim()
    }

    private static int year(final Matcher matcher) {
        final String group2 = matcher.group('year')

        group2 ? Integer.parseInt(group2) : 0
    }

    private static String qualifier(final Matcher matcher, final String fileExtension) {
        matcher.group('lastPart').minus(fileExtension)
    }
}