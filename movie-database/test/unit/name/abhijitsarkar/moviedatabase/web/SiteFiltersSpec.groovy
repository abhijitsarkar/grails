package name.abhijitsarkar.moviedatabase.web

import static name.abhijitsarkar.moviedatabase.web.SiteFilters.blankToNullAndTrimIfNotNull

import spock.lang.Specification

class SiteFiltersSpec extends Specification {

    void 'Test that blank param value is converted too null'() {
    	setup:
    	Map<String, String> map = ['key':'']

    	when:
    	blankToNullAndTrimIfNotNull(map)

    	then:
    	map['key'] == null
    }

    void 'Test that param value with space is trimmed'() {
    	setup:
    	Map<String, String> map = ['key':'value ']

    	when:
    	blankToNullAndTrimIfNotNull(map)

    	then:
    	map['key'] == 'value'
    }

    void 'Test that nested param values are handled too'() {
    	setup:
    	Map<String, Object> map = ['key1':' ', 'key2':['key3':'value2 ']]

    	when:
    	blankToNullAndTrimIfNotNull(map)

    	then:
    	map['key1'] == null
    	map['key2']['key3'] == 'value2'
    }
}
