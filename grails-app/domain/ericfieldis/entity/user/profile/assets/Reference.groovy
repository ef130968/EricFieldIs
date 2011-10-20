package ericfieldis.entity.user.profile.assets

import ericfieldis.entity.Entity
import ericfieldis.entity.user.profile.Profile

class Reference extends Entity {

    static belongsTo = [profile: Profile]

    static constraints = {
    }
}
