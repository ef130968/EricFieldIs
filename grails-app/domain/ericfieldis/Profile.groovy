package ericfieldis

class Profile {

    String name
    String description

    static belongsTo = [person: Person]
    static hasMany = [talents: Talent, projects: Project]

    static constraints = {
        name(blank: false, maxSize: 100)
        description(blank: false)
    }
}
