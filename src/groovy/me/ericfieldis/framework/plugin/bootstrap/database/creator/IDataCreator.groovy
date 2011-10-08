package me.ericfieldis.framework.plugin.bootstrap.database.creator

/**
 * Created by vincentgiguere 
 * 11-05-31 - 4:23 PM
 * Copyright © 2011 publicprivatecareer.com
 */

public interface IDataCreator<Domain> {
    void execute()
    void printSummary()
}