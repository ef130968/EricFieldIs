package me.ericfieldis.datatypes.enums

/**
 * Created by IntelliJ IDEA.
 * User: efield
 * Date: 08/10/11
 * Time: 4:08 AM
 * To change this template use File | Settings | File Templates.
 */

public enum ProfileType {
    JOB_SEEKER(1, 'Job Seeker'),
    JOB_PROVIDER(2, 'Job Provider')

    int id
    String name

    ProfileType(int id, String name) {
        this.id = id
        this.name = name
    }

    static list() {
        [JOB_SEEKER, JOB_PROVIDER]
    }

    String profileTypeName() {
        this.name
    }

    int profileTypeId() {
        this.id
    }
}

/*
public enum ProfileType {
    JOB_SEEKER(0L, 'Job Seeker'),
    JOB_PROVIDER(1L, 'Job Provider')

    Long id
    String name

    ProfileType(Long id, String name) {
        this.id = id
        this.name = name
    }

    static list() {
        [JOB_SEEKER, JOB_PROVIDER]
    }

    String profileTypeName() {
        this.name
    }
}
*/
