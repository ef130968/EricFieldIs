package ericfieldis.entity.user.profile.skin

import ericfieldis.entity.user.profile.Profile

class Skin {
    static belongsTo = [profile: Profile]

    String name

    static constraints = {
        name(unique: true, blank: false, maxSize: 100)
    }
}
