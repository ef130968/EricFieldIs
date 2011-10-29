grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"
//grails.project.war.file = "target/${appName}-${appVersion}.war"
grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    repositories {
        grailsPlugins()
        grailsHome()
        grailsCentral()

        // uncomment the below to enable remote dependency resolution
        // from public Maven repositories
        //mavenLocal()
        //mavenCentral()
        //mavenRepo "http://snapshots.repository.codehaus.org"
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        //mavenRepo "http://repository.jboss.com/maven2/"
    }
    dependencies {
        // specify dependencies here under either 'build', 'compile', 'runtime', 'test' or 'provided' scopes eg.

        // runtime 'mysql:mysql-connector-java:5.1.13'
    }
    plugins{
        runtime ":bean-fields:1.0.RC5"
        runtime ":blueprint:1.0.2"
        runtime ":cache-headers:1.1.5"
        runtime ":ckeditor:3.6.0.0"
        runtime ":feeds:1.5"
        runtime ":jquery:1.6.1.1"
        runtime ":navigation:1.3.1"
        runtime ":quartz:0.4.2"
        runtime ":searchable:0.6.3"
        runtime ":taggable:1.0"
        runtime ":weceem:1.0"
    }
}
