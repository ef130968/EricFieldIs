package ericfieldis

class Skill {

    String name

    static belongsTo = [talent: Talent]

    static constraints = {
        name(blank: false)
    }

}
