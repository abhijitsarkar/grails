package name.abhijitsarkar.moviedatabase.domain

import spock.lang.Specification

class CastAndCrewSpec extends Specification {

	private CastAndCrew star1
    private CastAndCrew star2

    def setup() {
    	star1 = new CastAndCrew('Arnold Schwarzenegger')

        star2 = new CastAndCrew('Arnold Schwarzenegger')
    }

    def cleanup() {
    }

    void 'test toString'() {
    	assert 'CastAndCrew[name:Arnold Schwarzenegger]' == star1?.toString()
    }
}
