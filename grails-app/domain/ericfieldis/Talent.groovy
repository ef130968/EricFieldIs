package ericfieldis

class Talent {

    String name

    static hasMany = [skills: Skill]

    static constraints = {
        name (blank: false)
    }
}
