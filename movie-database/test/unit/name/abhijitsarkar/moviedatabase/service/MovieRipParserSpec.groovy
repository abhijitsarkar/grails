package name.abhijitsarkar.moviedatabase.service

import static name.abhijitsarkar.moviedatabase.service.MovieRipParser.parse

import spock.lang.Specification
import spock.lang.Ignore

import name.abhijitsarkar.moviedatabase.domain.MovieRip 

class MovieRipParserSpec extends Specification {

    void 'test that the parser is able to parse various conventional movie names'() {
        when:
        MovieRip mr = parse 'Casino Royal (2006).mkv'
        
        then:
        mr.title == 'Casino Royal'
        mr.releaseYear == 2006
        mr.fileExtension == '.mkv'
        
        when:
        mr = parse '2 Fast 2 Furious - part 1 (2001).mkv'
        
        then:
        mr.title == '2 Fast 2 Furious - part 1'
        mr.releaseYear == 2001
        mr.fileExtension == '.mkv'

        when:
        mr = parse 'Avengers Confidential Black Widow & Punisher (2014).avi'
        
        then:
        mr.title == 'Avengers Confidential Black Widow & Punisher'
        mr.releaseYear == 2014
        mr.fileExtension == '.avi'
    }

    void 'test that the parser is able to parse an unconventional movie rip name'() {
        when:
        MovieRip mr = parse 'He-Man - A Friend In Need.avi'
        
        then:
        mr.title == 'He-Man - A Friend In Need'
        mr.releaseYear == 0
        mr.fileExtension == '.avi'
    }
}