import name.abhijitsarkar.moviemanager.domain.MovieRipFileExtension
import org.apache.lucene.store.Directory
import org.apache.lucene.store.FSDirectory
import org.apache.lucene.util.Version

beans = {
    indexDirectory(Directory) {
        File indexDirectory = new File("${grailsApplication.config.indexDirectoryPath}")
        FSDirectory.open(indexDirectory)
    }

    version(Version) {
        "${grailsApplication.config.luceneVersion}"
    }

    genres(List) {
        "${grailsApplication.config.genres}"
    }

    includes(List) {
        MovieRipFileExtension.values().collect {
            // GOTCHA ALERT: GString is not equal to String; "a" != 'a'
            ".${it.name().toLowerCase()}".toString()
        }
    }
}