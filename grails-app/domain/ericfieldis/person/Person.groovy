package ericfieldis.person

import ericfieldis.person.assets.Money
import ericfieldis.person.citizen.coordinate.Coordinate

class Person {

    int id

    String firstName
    String middleInitial
    String lastName

    Coordinate coordinate
    Money money

    static constraints = {
        firstName(blank: false, maxSize: 20)
        middleInitial(blank: false, size: 1..1)
        lastName(blank: false, maxSize: 20)
    }

    String personName() {
        firstName + " " + middleInitial + " " + lastName
    }
}
