package ericfieldis.person.user.profile.component

import ericfieldis.person.Person
import ericfieldis.person.user.profile.Profile

class Reference extends Person {

    static belongsTo = [profile: Profile]

    static constraints = {
    }
}
