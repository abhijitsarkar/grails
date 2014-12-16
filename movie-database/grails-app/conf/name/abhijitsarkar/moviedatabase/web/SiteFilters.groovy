package name.abhijitsarkar.moviedatabase.web

import groovy.transform.PackageScope

import org.apache.commons.logging.LogFactory
import org.apache.commons.logging.Log

class SiteFilters {

    private static final Log log = LogFactory.getLog(this)

    def filters = {
        blankToNullAndTrimIfNotNull(controller:'*', action:'*') {
            before = {
                blankToNullAndTrimIfNotNull(params)
            }

            true
        }
    }

    @PackageScope
    static void blankToNullAndTrimIfNotNull(def map) {
        def keys = map.keySet().asImmutable()
        
        keys.each { aKey ->
            def value = map[aKey]

            if (!value || value instanceof String) {
                log.debug("key: ${aKey}, value: ${value}")

                map[aKey] = value?.trim() ?: null
            } else if (value instanceof Map) {
                // recurse with nested param, e.g., "location":["id":""]
                blankToNullAndTrimIfNotNull(value)
            }
        }
    }
}
