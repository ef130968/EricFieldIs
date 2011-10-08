package ericfieldis.profile.components.talent

import ericfieldis.profile.components.talent.Talent

class Skill {

    String name
    String description

    static belongsTo = [talent: Talent]

    static constraints = {
        name(blank: false, maxSize: 100)
        description(blank: false)
    }

}
