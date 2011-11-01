package ericfieldis.entity.user.command

import ericfieldis.entity.user.User
import me.ericfieldis.servlet.ImageManagement

/**
 * Created by IntelliJ IDEA.
 * User: efield
 * Date: 29/10/11
 * Time: 1:18 PM
 * To change this template use File | Settings | File Templates.
 */

@Mixin(ImageManagement)
class UpdateUserCommand {
    Long userId
    Long userVersion
    Long citizenId

    String email
    String username
    String password
    String passwordConfirmation
    boolean enabled = true
    boolean accountExpired = false
    boolean accountLocked = false
    boolean passwordExpired = false
    byte[] avatarImage
    String avatarMimeType

    static constraints = {
        username blank: false
        password blank: true
        passwordConfirmation blank: true, validator: {passwordConfirmation, obj ->
         def password = obj.properties['password']
         if(passwordConfirmation == null) return true // skip matching password validation (only important when setting/resetting pass)
         passwordConfirmation == password ? true : ['user.settings.error.invalidPasswordConfirmationValue']}
        avatarImage nullable: true, maxSize: 1073741824 // max 4GB file
        avatarMimeType nullable: true, blank: true
    }

    UpdateUserCommand loadUser() {
        User userInstance = User.get(userId)
        citizenId = userInstance.citizen.id
        email = userInstance.email
        username = userInstance.username
        password = userInstance.password
        passwordConfirmation = password
        enabled = userInstance.enabled
        accountExpired = userInstance.accountExpired
        accountLocked = userInstance.accountLocked
        passwordExpired = userInstance.passwordExpired
        avatarImage = userInstance.avatarImage
        avatarMimeType = userInstance.avatarMimeType
        userVersion = userInstance.version
        this
    }

    boolean execute(def params) {
        User userInstance = User.get(userId)
        if(userVersion > userInstance.version) {
            return false
        }
        String thePassword = userInstance.password
        userInstance.properties = params
        if(password.isEmpty()) userInstance.password = thePassword
        if(params.multipartFile.size) {
            userInstance.avatarMimeType = using(params.multipartFile).getMimeType()
            userInstance.avatarImage = using(params.multipartFile).getImageBytes()
        }
        if(!userInstance.validate()) {
            this.errors = userInstance.errors
            return false
        }
        userInstance.save(flush: true)
        return true
    }


}
