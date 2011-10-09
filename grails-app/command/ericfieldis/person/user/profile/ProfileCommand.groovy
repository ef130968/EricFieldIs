package ericfieldis.person.user.profile

import me.ericfieldis.datatypes.enums.ProfileType

import ericfieldis.person.Person

/**
 * Created by IntelliJ IDEA.
 * User: efield
 * Date: 08/10/11
 * Time: 7:48 PM
 * To change this template use File | Settings | File Templates.
 */

class ProfileCommand {
//    Long id
//    Long version

    Long personSelection
    Long skinSelection

    String name
    String description

    Long profileTypeSelection = 0L

    static constraints = {
        personSelection(nullable: true)
        skinSelection(nullable: true)
        name(blank: false, maxSize: 100)
        description(blank: false)
        profileTypeSelection(nullable: false)
    }

}
