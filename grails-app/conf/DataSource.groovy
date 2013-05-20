/*dataSource {
    pooled = true
    driverClassName = "org.h2.Driver"
    username = "sa"
    password = ""
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = false
    cache.region.factory_class = 'net.sf.ehcache.hibernate.EhCacheRegionFactory'
}
// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "create-drop" // one of 'create', 'create-drop', 'update', 'validate', ''
            url = "jdbc:h2:mem:devDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
        }
    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:h2:mem:testDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
        }
    }
    production {
        dataSource {
            dbCreate = "update"
            url = "jdbc:h2:prodDb;MVCC=TRUE;LOCK_TIMEOUT=10000"
            pooled = true
            properties {
               maxActive = -1
               minEvictableIdleTimeMillis=1800000
               timeBetweenEvictionRunsMillis=1800000
               numTestsPerEvictionRun=3
               testOnBorrow=true
               testWhileIdle=true
               testOnReturn=true
               validationQuery="SELECT 1"
            }
        }
    }
} */
grails {
    neo4j {
        // define a Neo4j datasource
        // type is either "embedded", "rest", "ha", or any custom class implementing GraphDatabaseService

        // 1) embedded
        // when using embedded, Neo4j runs inside the same JVM like the Grails application
        // options:
        // location: the path where Neo4j is stored, defaults to "data/neo4j"
        // params: optional map passed to EmbeddedGraphDatabase
        //type = "embedded"
        // location = "data/neo4j"
        // params = [:]


        // 2) rest
        // when using a Neo4j server via rest REST uncomment the following line
        // type = "rest"
        // options:
        // location: URL of rest server, defaults to http://localhost:7474/db/data/
        // NB: if heroku is used just omit location, the plugin tries to use env.NEO4J_URL in this case
        // NB: params are not allowed when using REST

        // type="rest"
        // location = "http://localhost:7474/db/data/"

        // 3) HA embedded
        // use a in-JVM Neo4j instance being part of a HA cluster
        // options:
        // location: directory where Neo4j stores the db
        // options: parameters for HA setup, see http://docs.neo4j.org/chunked/stable/ha-configuration.html#_installation_notes
        type = "ha"
        // location = "data/neo4j"
         params = [
                 'ha.server_id': appName.split("\\.")[1], // appSystem.properties['ha.server_id'],
                 'ha.initial_hosts': '127.0.0.1:5001,127.0.0.1:5002,127.0.0.1:5003',
                 'ha.cluster_server': '127.0.0.1:5001-5003'
                 //    ha.coordinators = " localhost:2181,localhost:2182,localhost:2183"
         ]

    }
}
