package name.abhijitsarkar.moviedatabase.domain

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class CastAndCrew {

    static constraints = {
    }

    String name

    CastAndCrew(name) {
        this.name = name
    }

    @Override
    String toString() {
        "${this.class.simpleName}[name:${name}]"
    }
}
