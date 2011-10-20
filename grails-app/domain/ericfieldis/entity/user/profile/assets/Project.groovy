package ericfieldis.entity.user.profile.assets

import ericfieldis.entity.user.profile.Profile

class Project {

    String title
    String description

    static belongsTo = [profile: Profile]

    static constraints = {
        title(blank: false, maxSize: 100)
        description(blank: false)
    }
}
