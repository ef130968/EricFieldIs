package ericfieldis.entity.citizen

import ericfieldis.entity.Entity
import ericfieldis.entity.user.User

class Citizen {

    static belongsTo = [entity: Entity]

    String firstName
    String middleInitial
    String lastName

    User user
    //Coordinate coordinate

//    static mapping = {
//        user cascade: 'all'
//    }

    static constraints = {
        firstName(blank: false, maxSize: 20)
        middleInitial(blank: true, size: 1..1, matches: "[A-Z]")
        lastName(blank: false, maxSize: 20)
    }

    /*
    String fullName() {
        firstName + " " + middleInitial + " " + lastName
    }

    @Override
    boolean equals(other) {
        this.id = other.id
    }

    @Override
    int hashCode() {
        id.hashCode()
    }
*/
}
