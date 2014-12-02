import java.io.File

dataSource {
    pooled = true
    jmxExport = true
    //driverClassName = "com.mysql.jdbc.Driver"
    //username = "mdbadmin"
    //password = "mdbadmin"
    driverClassName = "org.h2.Driver"
    username = "sa"
    password = ""
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
//    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory' // Hibernate 3
    cache.region.factory_class = 'org.hibernate.cache.ehcache.EhCacheRegionFactory' // Hibernate 4
    singleSession = true // configure OSIV singleSession mode
    flush.mode = 'manual' // OSIV session flush mode outside of transactional context
}

// environment specific settings
environments {
    development {
        dataSource {
            //dbCreate = "update"
            //url = "jdbc:mysql://localhost:3306/mdbDev"
            dbCreate = "update" // one of 'create', 'create-drop', 'update', 'validate', ''
            url = "jdbc:h2:mem:mdbDev"
        }
    }

  //def tmpDir = System.getProperty('java.io.tmpdir')
  //def dbDir = new File(tmpDir, 'movie-database').toURI()

  //println "Creting db dir: ${dbDir}"

  //development {
   //     dataSource {
   //         dbCreate = "create" // one of 'create', 'create-drop', 'update', 'validate', ''
    //        url = "jdbc:h2:file:${dbDir}:mdbDev"
    //    }
    //}
    test {
        dataSource {
            //dbCreate = "update"
            //url = "jdbc:mysql://localhost:3306/mdb"
            dbCreate = "update" // one of 'create', 'create-drop', 'update', 'validate', ''
            url = "jdbc:h2:mem:mdbTest"
        }
    }

    production {
        dataSource {
            dbCreate = "update"
            url = "jdbc:h2:mem:mdbProd"
            properties {
               // See http://grails.org/doc/latest/guide/conf.html#dataSource for documentation
               defaultTransactionIsolation = java.sql.Connection.TRANSACTION_READ_COMMITTED
            }
        }
    }
}
