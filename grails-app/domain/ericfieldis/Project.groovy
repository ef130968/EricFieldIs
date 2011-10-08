package ericfieldis

class Project {

    String title
    String description

    static belongsTo = [profile: Profile]

    static constraints = {
        title(blank: false, maxSize: 100)
        description(blank: false)
    }
}
