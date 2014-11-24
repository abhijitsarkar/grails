package name.abhijitsarkar.moviedatabase.domain

import grails.test.mixin.TestFor
import spock.lang.Specification

@TestFor(CastAndCrew)
class CastAndCrewSpec extends Specification {

	private CastAndCrew star1
    private CastAndCrew star2

    def setup() {
    	star1 = new CastAndCrew('Arnold Schwarzenegger')

        star2 = new CastAndCrew('Arnold Schwarzenegger')
    }

    def cleanup() {
    }

    void 'Test toString'() {
    	assert 'CastAndCrew[name:Arnold Schwarzenegger]' == star1?.toString()
    }
}
