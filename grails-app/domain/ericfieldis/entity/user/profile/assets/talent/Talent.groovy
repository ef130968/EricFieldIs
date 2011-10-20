package ericfieldis.entity.user.profile.assets.talent

import ericfieldis.entity.user.profile.Profile
import ericfieldis.entity.user.profile.assets.talent.skill.Skill

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
