package me.ericfieldis.framework.plugin.bootstrap.database

import me.ericfieldis.framework.plugin.bootstrap.database.creator.IDataCreator
import groovy.time.TimeCategory
import groovy.time.TimeDuration
import org.hibernate.Session
import org.hibernate.Transaction

class DataGenesis {

    List<me.ericfieldis.framework.plugin.bootstrap.database.creator.IDataCreator> creators

    public void createTheWorld(Session session) {
        Date startTime = new Date()
        println("################## BEGINNING DATA GENESIS AT : ${startTime.getTimeString()}")
        creators.each {creator ->
            Transaction transaction = session.beginTransaction()
            println("################## DATA CREATOR ${creator.class.getSimpleName()} HAS BEGUN")
            try {
                creator.execute()
                transaction.commit()
            } catch (Exception e) {
                e.printStackTrace()
                transaction.rollback()
            } finally {
                session.clear()
            }
            println("################## DATA CREATOR ${creator.class.getSimpleName()} HAS FINISHED")
            creator.printSummary()
        }

        Date endTime = new Date()
        println("################## ENDING DATA GENESIS AT : ${endTime.getTimeString()}")
        TimeDuration duration = TimeCategory.minus(endTime, startTime)
        println("################## ELAPSED TIME:  ${duration.toString()}")
        println("################## DATA GENESIS COMPETED")
    }
}
