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
        slurp.entity.each {entity ->
            entity.citizen.each {citizen ->
                Entity newEntity = Entity.generateCitizen(citizen.@firstName.text(), citizen.@middleInitial.text(), citizen.@lastName.text())
                entity.citizen.user.each {user ->
                    User newUser = User.generate(user.@email.text(), user.@username.text(), user.@password.text())
                    newEntity.citizen.user = newUser
                    if (!newEntity.save()) {
                        newEntity.errors.each {
                            println it
                        }
                        throw new IllegalStateException("Entity could not be created. Consult logs")
                    }

                    user.role.each { role ->
                        UserRole.create(newUser, createRole(role.text()))
                    }
                }
            }
            entity.business.each {business ->
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
