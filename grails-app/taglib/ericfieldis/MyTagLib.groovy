package ericfieldis

/**
 * Created by IntelliJ IDEA.
 * User: efield
 * Date: 08/10/11
 * Time: 8:44 PM
 * To change this template use File | Settings | File Templates.
 */

class MyTagLib {
    static namespace = "my"

    def message = { attrs ->
        if (!attrs.default) {
            attrs.default = attrs.code
        }

        out << g.message(attrs)
    }
}
