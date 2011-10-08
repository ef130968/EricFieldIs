package ericfieldis

class Skin {
    static belongsTo = [profile: Profile]

    String name

    static constraints = {
        name(blank: false, maxSize: 100)
    }
}
