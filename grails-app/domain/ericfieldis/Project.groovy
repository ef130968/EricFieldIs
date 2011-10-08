package ericfieldis

import org.dom4j.tree.BackedList

class Project {

    String title
    String description

    static belongsTo = [person: Person]

    static constraints = {
        title(blank: false)
        description(blank: false)
    }
}
