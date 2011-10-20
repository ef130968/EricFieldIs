package me.ericfieldis.framework.plugin.bootstrap.database.creator

import groovy.util.slurpersupport.NodeChild

abstract class AbstractXMLParsingDataCreator<SOMETHING> extends me.ericfieldis.framework.plugin.bootstrap.database.creator.AbstractDataCreator<SOMETHING> {

    abstract void execute(NodeChild slurp)

    abstract InputStream xmlStream()

    void execute() {
        execute(new XmlSlurper().parse(xmlStream()))
    }
}
