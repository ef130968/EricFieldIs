package ericfieldis

class Person {

    String firstName
    String middleInitial
    String lastName

    static hasMany = [profiles: Profile]

    static constraints = {
        firstName(blank: false, maxSize: 20)
        middleInitial(blank: false, size: 1..1)
        lastName(blank: false, maxSize: 20)
    }
}
