package ericfieldis.entity

import ericfieldis.entity.citizen.Citizen
import ericfieldis.entity.business.Business

class Entity {

    Citizen citizen;
    Business business;

    static constraints = {
        citizen(nullable: true)
        business(nullable: true)
    }

    static Entity generateCitizen(String firstName, String middleInitial, String lastName) {
        Entity entity = new Entity()
        entity.citizen = new Citizen(firstName: firstName, middleInitial: middleInitial, lastName: lastName)
        entity
    }

    static Entity generateBusiness(String name) {
        Entity entity = new Entity()
        entity.business = new Business(name: name)
        entity
    }
}
