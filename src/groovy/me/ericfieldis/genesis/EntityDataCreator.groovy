package me.ericfieldis.genesis

import grails.plugins.springsecurity.SpringSecurityService
import groovy.util.slurpersupport.NodeChild
import org.springframework.stereotype.Component
import me.ericfieldis.framework.plugin.bootstrap.database.creator.AbstractXMLParsingDataCreator
import ericfieldis.entity.Entity

import ericfieldis.entity.user.User
import ericfieldis.entity.user.UserRole
import ericfieldis.entity.user.Role

@Component
class EntityDataCreator extends AbstractXMLParsingDataCreator<Entity> {

    SpringSecurityService springSecurityService

    @Override
    void execute(NodeChild slurp) {
        slurp.entity.each {theEntity ->
            theEntity.citizen.each {theCitizen ->
                Entity newEntity = Entity.generateCitizen(theCitizen.@firstName.text(), theCitizen.@middleInitial.text(), theCitizen.@lastName.text())
                theCitizen.user.each {theUser ->
                    User newUser = User.generate(theUser.@email.text(), theUser.@username.text(), theUser.@password.text())
                    newEntity.citizen.user = newUser
                    if (!newEntity.save()) {
                        newEntity.errors.each {
                            println it
                        }
                        throw new IllegalStateException("Entity could not be created. Consult logs")
                    }
                    theUser.role.each {theRole ->
                        UserRole.create(newUser, createRole(theRole.text()))
                    }
                }
            }
            theEntity.business.each {theBusiness ->
            }
        }
    }

    Role createRole (String roleName) {
        Role role = Role.findByAuthority(roleName)
        if (!role) {
            role = new Role(authority: roleName).save()
        }
        return role
    }

    @Override
    InputStream xmlStream() {
        return me.ericfieldis.genesis.EntityDataCreator.getClassLoader().getResourceAsStream("me/ericfieldis/genesis/xml/Entities.xml")
    }
}
