package ericfieldis.profile

import me.ericfieldis.datatypes.enums.ProfileType
import ericfieldis.Person
import ericfieldis.Skin
import ericfieldis.Money
import ericfieldis.profile.components.Friend
import ericfieldis.profile.components.Opinion
import ericfieldis.profile.components.Job
import ericfieldis.profile.components.Reference
import ericfieldis.profile.components.talent.Talent
import ericfieldis.profile.components.Trait
import ericfieldis.profile.components.Value

class Profile {
    static belongsTo = [person: Person]
    static hasMany = [skins: Skin]

    int id

    String name
    String description

    ProfileType profileType

    static constraints = {
        name(unique: true, blank: false, maxSize: 100)
        description(blank: false)
        profileType(nullable: false)
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

class LookingForSomeone extends Profile {
    static hasMany = [
        traits: Trait, // someone else must have
        values: Value // someone else must have
    ]

    static constraints = {
    }
}
