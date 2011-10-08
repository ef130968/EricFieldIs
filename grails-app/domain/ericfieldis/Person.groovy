package ericfieldis

class Person {

    String name

    static hasMany = [talents: Talent]

    static constraints = {
        name (blank: false)
    }
}
