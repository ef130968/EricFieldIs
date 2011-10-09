package ericfieldis.person.user.profile.component

import ericfieldis.person.user.profile.Profile
import ericfieldis.person.user.profile.component.talent.Talent

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
