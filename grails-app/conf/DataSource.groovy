dataSource {
    pooled = true
    driverClassName = "com.mysql.jdbc.Driver"
    username = "root"
    password = "root"
}
hibernate {
    cache.use_second_level_cache = true
    cache.use_query_cache = true
    cache.provider_class = "net.sf.ehcache.hibernate.EhCacheProvider"
}

// environment specific settings
environments {
    development {
        dataSource {
            dbCreate = "update" // one of 'create', 'create-drop','update'
            url = "jdbc:mysql://localhost:3306/EricFieldIs_development"
        }
    }
    test {
        dataSource {
            dbCreate = "update"
            url = "jdbc:mysql://localhost:3306/EricFieldIs_test"
        }
    }
    production {
        dataSource {
            pooled = true
            driverClassName = "com.mysql.jdbc.Driver"
            dbCreate = "update" // one of 'create', 'create-drop','update'
            username = 'root'
            password = 'fierik130968'
            url = 'jdbc:mysql://mydbinstance.cxksmkm5noom.us-east-1.rds.amazonaws.com:3306/EricFieldIs'
            dialect = org.hibernate.dialect.MySQL5InnoDBDialect
            properties {
                validationQuery = "SELECT 1"
                testOnBorrow = true
                testOnReturn = true
                testWhileIdle = true
                timeBetweenEvictionRunsMillis = 1000 * 60 * 30
                numTestsPerEvictionRun = 3
                minEvictableIdleTimeMillis = 1000 * 60 * 30
            }
        }
    }
}