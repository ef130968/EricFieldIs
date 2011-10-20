package ericfieldis.entity.user.coordinate

import ericfieldis.entity.user.User2

class Coordinate {

    // Location location

    static belongsTo = [user: User2]

    static constraints = {
    }
}
