package ericfieldis.entity.user.profile.assets

import ericfieldis.entity.user.profile.Profile

class Opinion {
    String title
    String description

    static belongsTo = [profile: Profile]

    static constraints = {
        title(unique: true, blank: false, maxSize: 100)
        description(blank: false)
    }
}
