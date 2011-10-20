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

    static constraints = {
        username blank: false, unique: true
        password blank: false
    }

    static mapping = {
        password column: '`password`'
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
}
