package ericfieldis.profile.components

import ericfieldis.Person
import ericfieldis.profile.Profile

class Reference extends Person {
    static belongsTo = [profile: Profile]

    static constraints = {
    }
}
