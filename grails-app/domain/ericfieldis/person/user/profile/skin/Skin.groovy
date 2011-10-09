package ericfieldis.person.user.profile.skin

import ericfieldis.person.user.profile.Profile

class Skin {
    static belongsTo = [profile: Profile]

    String name

    static constraints = {
        name(unique: true, blank: false, maxSize: 100)
    }
}
