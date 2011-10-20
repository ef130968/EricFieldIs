package ericfieldis.entity.user.profile.assets

import ericfieldis.entity.user.profile.Profile

import ericfieldis.entity.user.profile.assets.talent.Talent

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
