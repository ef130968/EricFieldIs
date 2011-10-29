package ericfieldis.entity.user.command

import ericfieldis.entity.user.User

/**
 * Created by IntelliJ IDEA.
 * User: efield
 * Date: 29/10/11
 * Time: 1:18 PM
 * To change this template use File | Settings | File Templates.
 */
class UpdateUserCommand {
    Long id
    Long version
    Long citizenId

    String email
    String username
    String password
    boolean enabled = true
    boolean accountExpired = false
    boolean accountLocked = false
    boolean passwordExpired = false
    String avatarFile
    byte[] avatarImage
    String avatarMimeType

    static constraints = {
        username blank: false, unique: true
        password blank: false
        avatarFile nullable: true, blank: true, unique: true
        avatarImage nullable: true, maxSize: 1073741824 // max 4GB file
        avatarMimeType nullable: true, blank: true
    }

    UpdateUserCommand loadUser() {
        User userInstance = User.get(id)
        citizenId = userInstance.citizen.id
        email = userInstance.email
        username = userInstance.username
        password = userInstance.password
        enabled = userInstance.enabled
        accountExpired = userInstance.accountExpired
        accountLocked = userInstance.accountLocked
        passwordExpired = userInstance.passwordExpired
        avatarFile = userInstance.avatarFile
        avatarImage = userInstance.avatarImage
        avatarMimeType = userInstance.avatarMimeType
        version = userInstance.version
        this
    }

    boolean execute() {
        if(!validate()) {
            return false
        }
        User userInstance = update()
        if(!userInstance.optimisticSave(version)) {
            return true
        }
    }

    private User update() {
        User.get(id).update(citizenId, email, username, password, enabled, accountExpired, accountLocked, passwordExpired, avatarFile, avatarImage, avatarMimeType)
    }
}
