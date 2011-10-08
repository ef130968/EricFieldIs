package ericfieldis.profile.components.talent

import ericfieldis.profile.Profile

class Talent {

    String name
    String description

    static belongsTo = [profile: Profile]
    static hasMany = [skills: Skill]

    static constraints = {
        name(unique: true, blank: false, maxSize: 100)
        description(blank: false)
    }
}
