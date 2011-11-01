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
    byte[] avatarImage
    String avatarMimeType

    static constraints = {
        username blank: false, unique: true
        password blank: false
        avatarImage nullable: true, maxSize: 1073741824 // max 4GB file
        avatarMimeType nullable: true, blank: true
    }

    static mapping = {
        //password column: 'password'
        //avatarImage sqlType: 'longblob'
        //citizen lazy: false
        //citizen cascade:"all"
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

    User update(String firstName, String middleInitial, String lastName, String email, String username, String password, boolean enabled, boolean accountExpired, boolean accountLocked, boolean passwordExpired, byte[] avatarImage, String avatarMimeType) {
        this.citizen.firstName = firstName
        this.citizen.middleInitial = middleInitial
        this.citizen.lastName = lastName
        this.email = email
        this.username = username
        this.password = password
        this.enabled = enabled
        this.accountExpired = accountExpired
        this.accountLocked = accountLocked
        this.passwordExpired = passwordExpired
        this.avatarImage = avatarImage
        this.avatarMimeType = avatarMimeType
        this
    }
}
