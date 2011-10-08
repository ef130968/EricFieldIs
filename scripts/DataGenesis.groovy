import org.springframework.orm.hibernate3.SessionHolder
import org.springframework.transaction.support.TransactionSynchronizationManager
import org.springframework.orm.hibernate3.SessionFactoryUtils

includeTargets << grailsScript("Init")
includeTargets << grailsScript("_GrailsBootstrap")
includeTargets << grailsScript("_GrailsEvents")
includeTargets << new File("${basedir}/scripts/CleanDatabase.groovy")
//includeTargets << new File("Security.groovy")

target(setup: "Setup prior to executing") {
    System.setProperty('forceCreateDrop', 'true')
}

target(create: "Creates data in underlying database") {
    depends(setup, drop_database, configureProxy, packageApp, classpath, loadApp, configureApp)

    try {
        def datagenesis = appCtx.getBean('dataGenesis')
        def sessionFactory = appCtx.getBean("sessionFactory")
        def session = SessionFactoryUtils.getSession(sessionFactory, true)

        TransactionSynchronizationManager.bindResource(sessionFactory, new SessionHolder(session))

        datagenesis.createTheWorld(session)
        session.flush();

    } catch (Exception e) {
        e.printStackTrace()
    }

}

setDefaultTarget("create")
