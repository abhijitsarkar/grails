import java.util.Collection

import static name.abhijitsarkar.moviedatabase.domain.test.MovieRipTestHelper.terminator2MovieRip

import name.abhijitsarkar.moviedatabase.domain.MovieRip

class BootStrap {

    def init = { servletContext ->
    	if (!MovieRip.findAllByTitle('Terminator 2 Judgment Day')) {
			MovieRip m = terminator2MovieRip()
			saveCastAndCrew(m)

        	m.save(failOnError: true)
    	}
    }

    private saveCastAndCrew(m) {
        m.director.save(failOnError: true)
        m.stars*.save(failOnError: true)
    }

    def destroy = {
    }
}
