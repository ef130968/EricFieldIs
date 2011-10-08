package ericfieldis

import me.ericfieldis.datatypes.enums.ProfileType

class Profile {
    static belongsTo = [person: Person]
    static hasMany = [skins: Skin]

    String name
    String description

    ProfileType profileType

    static constraints = {
        name(blank: false, maxSize: 100)
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
}

class JobProvider extends Profile {
    static hasMany = [
        talents: Talent // which are required
    ]

    static hasOne = [jobs: Job] // which is offered to a JobSeeker

    int requiredReferences
}

class HelpWanted extends Profile {
    static hasMany = [
        talents: Talent // which someone else is offering
    ]

    Money money // offered for a service
}

class HelpOffered extends Profile {
    static hasMany = [
        talents: Talent // which someone else wants
    ]

    Money money // wanted for a service
}

class Social extends Profile {
    static hasMany = [
        talents: Talent, // to share with others
        opinions: Opinion, // to share with others
        friends: Friend // to share with others
    ]
}
