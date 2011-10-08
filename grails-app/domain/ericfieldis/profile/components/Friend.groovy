package ericfieldis.profile.components

import ericfieldis.Person
import ericfieldis.profile.Profile

class Friend extends Person {
    static belongsTo = [profile: Profile]

    static constraints = {
    }
}
