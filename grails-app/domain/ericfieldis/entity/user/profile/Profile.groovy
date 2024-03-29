package ericfieldis.entity.user.profile

import me.ericfieldis.datatypes.enums.ProfileType

import ericfieldis.entity.Entity
import ericfieldis.entity.user.profile.assets.Money
import ericfieldis.entity.user.profile.skin.Skin
import ericfieldis.entity.user.profile.assets.Friend
import ericfieldis.entity.user.profile.assets.Job
import ericfieldis.entity.user.profile.assets.Opinion

import ericfieldis.entity.user.profile.assets.Reference
import ericfieldis.entity.user.profile.assets.Trait
import ericfieldis.entity.user.profile.assets.Value
import ericfieldis.entity.user.profile.assets.talent.Talent

class Profile {
    static belongsTo = [person: Entity]
    static hasMany = [skins: Skin]

    Long id

    String name
    String description

    ProfileType profileType

    static constraints = {
        name(unique: true, blank: false, maxSize: 100)
        description(blank: false)
        profileType(nullable: false)
        person(nullable: true)
    }

    Profile update(ProfileCommand cmd) {
        profileType = ProfileType.list().get(cmd.profileTypeSelection - 1 as int)
        name = cmd.name
        description = cmd.description
        this
    }
}

class JobSeeker extends Profile {
    static hasMany = [
        talents: Talent, // which are part of a Resume
        jobs: Job, // which are part of a Resume
        references: Reference // which are part of a Resume
    ]

    static constraints = {
    }
}

class JobProvider extends Profile {
    static hasOne = [jobs: Job] // which is offered to a JobSeeker
    static hasMany = [
        talents: Talent // which are required
    ]

    int requiredReferences

    static constraints = {
    }
}

class HelpWanted extends Profile {
    static hasMany = [
        talents: Talent // which someone else is offering
    ]

    Money money // offered for a service

    static constraints = {
    }
}

class HelpOffered extends Profile {
    static hasMany = [
        talents: Talent // which someone else wants
    ]

    Money money // wanted for a service

    static constraints = {
    }
}

class Social extends Profile {
    static hasMany = [
        talents: Talent, // to share with others
        opinions: Opinion, // to share with others
        friends: Friend // to share with others
    ]

    static constraints = {
    }
}

class SeekingSomeone extends Profile {
    static hasMany = [
        traits: Trait, // someone else must have
        values: Value // someone else must have
    ]

    static constraints = {
    }
}
