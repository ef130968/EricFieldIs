package ericfieldis

class Profile {

    static belongsTo = [person: Person]
    static hasMany = [talents: Talent, projects: Project]

    static constraints = {
    }
}
