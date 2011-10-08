package ericfieldis.profile.components

import ericfieldis.profile.Profile
import ericfieldis.profile.components.talent.Talent

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
