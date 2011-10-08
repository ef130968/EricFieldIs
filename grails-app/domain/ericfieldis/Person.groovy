package ericfieldis

class Person {

    String name

    static hasMany = [profiles: Profile]

    static constraints = {
        name(blank: false)
    }
}
