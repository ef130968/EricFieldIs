package ericfieldis.entity.user

import ericfieldis.entity.citizen.Citizen

class User2 {

    //List<Profile> profiles = []
    //static hasMany = [profiles: Profile]
    static belongsTo = [citizen: Citizen]

    String email // Email will never be shown to other users and will only be used for registering and login into system.

    static constraints = {
        email(blank: false, unique: true, email: true)
    }

    static User2 generate(String email) {
        new User2(email: email)
    }
}
