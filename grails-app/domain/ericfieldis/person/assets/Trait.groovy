package ericfieldis.person.assets

import ericfieldis.person.user.profile.Profile

class Trait {
    String title
    String description

    static belongsTo = [profile: Profile]

    static constraints = {
        title(unique: true, blank: false, maxSize: 100)
        description(blank: false)
    }
}
