package ericfieldis

class Talent {

    String name
    String description

    static belongsTo = [profile: Profile]
    static hasMany = [skills: Skill]

    static constraints = {
        name(blank: false, maxSize: 100)
        description(blank: false)
    }
}
