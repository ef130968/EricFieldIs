package ericfieldis.entity.user.profile.assets.talent.skill

import ericfieldis.entity.user.profile.assets.talent.Talent

class Skill {

    String name
    String description

    static belongsTo = [talent: Talent]

    static constraints = {
        name(blank: false, maxSize: 100)
        description(blank: false)
    }

}
