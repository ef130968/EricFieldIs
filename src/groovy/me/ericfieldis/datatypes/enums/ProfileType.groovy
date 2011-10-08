package me.ericfieldis.datatypes.enums

/**
 * Created by IntelliJ IDEA.
 * User: efield
 * Date: 08/10/11
 * Time: 4:08 AM
 * To change this template use File | Settings | File Templates.
 */

public enum ProfileType {
    JOB_SEEKER('Job Seeker'),
    JOB_PROVIDER('Job Provider')

    String name

    ProfileType(String name) {
        this.name = name
    }

    static list() {
        [JOB_SEEKER, JOB_PROVIDER]
    }
}
