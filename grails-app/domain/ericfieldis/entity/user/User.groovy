package ericfieldis.entity.user

import ericfieldis.entity.citizen.Citizen

class User {

    static belongsTo = [citizen: Citizen]

    transient springSecurityService

    String email
    String username
    String password
    boolean enabled = true
    boolean accountExpired = false
    boolean accountLocked = false
    boolean passwordExpired = false
    //String avatarFile
    byte[] avatarImage
    String avatarMimeType

    static constraints = {
        username blank: false, unique: true
        password blank: false
        //avatarFile nullable: true, blank: true, unique: true
        avatarImage nullable: true, maxSize: 1073741824 // max 4GB file
        avatarMimeType nullable: true, blank: true
    }

    static mapping = {
        //password column: 'password'
        //avatarImage sqlType: 'longblob'
    }

    Set<Role> getAuthorities() {
        UserRole.findAllByUser(this).collect { it.role } as Set
    }

    def beforeInsert() {
        encodePassword()
    }

    def beforeUpdate() {
        if (isDirty('password')) {
            encodePassword()
        }
    }

    protected void encodePassword() {
        password = springSecurityService.encodePassword(password)
    }

    static User generate(String email, String username, String password) {
        new User(email: email, username: username, password: password)
    }

    User update(Long citizenId, String email, String username, String password, boolean enabled, boolean accountExpired, boolean accountLocked, boolean passwordExpired, byte[] avatarImage, String avatarMimeType) {
        citizen = Citizen.get(citizenId)
        this.email = email
        this.username = username
        this.password = password
        this.enabled = enabled
        this.accountExpired = accountExpired
        this.accountLocked = accountLocked
        this.passwordExpired = passwordExpired
        //this.avatarFile = avatarFile
        this.avatarImage = avatarImage
        this.avatarMimeType = avatarMimeType
        this
    }
}
