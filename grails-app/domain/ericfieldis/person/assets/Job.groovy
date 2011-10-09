package ericfieldis.person.assets

import ericfieldis.person.user.profile.Profile
import ericfieldis.person.assets.talent.Talent

class Job {
    String title
    String description

    static belongsTo = [profile: Profile]
    static hasMany = [talents: Talent]

    static constraints = {
        title(unique: true, blank: false, maxSize: 100)
        description(blank: false)
    }
}
