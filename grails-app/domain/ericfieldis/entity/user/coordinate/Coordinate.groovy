package ericfieldis.entity.user.coordinate

import ericfieldis.entity.user.User

class Coordinate {

    // Location location

    static belongsTo = [user: User]

    static constraints = {
    }
}
