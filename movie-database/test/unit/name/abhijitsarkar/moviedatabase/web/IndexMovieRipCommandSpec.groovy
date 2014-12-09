package name.abhijitsarkar.moviedatabase.web

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestMixin(GrailsUnitTestMixin)
class IndexMovieRipCommandSpec extends Specification {

    void 'test that equals considers only movieDirectory'() {
    	when:
    	IndexMovieRipCommand cmd1 = new IndexMovieRipCommand(movieDirectory: 'abc')
    	IndexMovieRipCommand cmd2 = new IndexMovieRipCommand(movieDirectory: 'abc')

    	then:
    	cmd1 == cmd2

    	when:
    	cmd1 = new IndexMovieRipCommand(movieDirectory: 'abc')
    	cmd2 = new IndexMovieRipCommand(movieDirectory: 'def')

    	then:
    	cmd1 != cmd2
    }

    void 'test that hashCode considers only movieDirectory'() {
    	when:
    	IndexMovieRipCommand cmd1 = new IndexMovieRipCommand(movieDirectory: 'abc')
    	IndexMovieRipCommand cmd2 = new IndexMovieRipCommand(movieDirectory: 'abc')

    	then:
    	cmd1.hashCode() == cmd2.hashCode()

    	when:
    	cmd1 = new IndexMovieRipCommand(movieDirectory: 'abc')
    	cmd2 = new IndexMovieRipCommand(movieDirectory: 'def')

    	then:
    	cmd1.hashCode() != cmd2.hashCode()
    }
}
