package ericfieldis.person.user.profile.component

import ericfieldis.person.Person
import ericfieldis.person.user.profile.Profile

class Friend extends Person {
    static belongsTo = [profile: Profile]

    static constraints = {
    }
}
