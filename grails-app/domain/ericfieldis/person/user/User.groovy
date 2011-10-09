package ericfieldis.person.user

import ericfieldis.person.Person
import ericfieldis.person.user.profile.Profile

class User extends Person {

    static hasMany = [profiles: Profile]

    String name // User name required to maintain anonymous access to system for everyone. Email will never be shown to other users and will only be used for registering and login into system.
    String password

    static constraints = {
        name(unique: true)
        password(range: 8..20)
    }
}
