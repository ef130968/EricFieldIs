package ericfieldis.entity.business

import ericfieldis.entity.Entity

import ericfieldis.entity.user.User

class Business {

    static belongsTo = [entity: Entity]

    String name

    User user;
    //Coordinate coordinate

    static constraints = {
        user(nullable: true)
        name(blank: false, maxSize: 50)
    }

    String fullName() {
        name
    }
}
