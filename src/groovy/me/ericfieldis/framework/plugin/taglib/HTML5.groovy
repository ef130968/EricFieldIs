package me.ericfieldis.framework.plugin.taglib

import static org.apache.commons.collections.CollectionUtils.subtract

/**
 * Created by jf
 * 30-06-2011 - 1:49 PM
 * Copyright © 2011 publicprivatecareer.com
 */
class HTML5 {

    static final List<String> html5AutoRepeatAttributes = ['autofocus', 'readonly', 'required', 'disabled', 'checked', 'multiple']
    static final List<String> html5TextAreaAttributes = ['name', 'cols', 'rows', 'dirname', 'form', 'maxlength', 'placeholder', 'wrap']
    static final List<String> html5InputAttributes = ['accept', 'alt', 'autocomplete', 'off', 'form', 'formaction', 'formenctype', 'formmethod', 'formnovalidate', 'formtarget', 'framename', 'height', 'list', 'max', 'maxlength', 'min', 'multiple', 'name', 'pattern', 'placeholder', 'size', 'src', 'step', 'type', 'value', 'width']

    static String textAreaAttributes(Map attrs) {
        outputAttributes(attrs, html5TextAreaAttributes)
    }

    static String inputAttributes(Map attrs) {
        outputAttributes(attrs, html5InputAttributes)
    }

    static String outputAttributes(Map attrs, List<String> htmlElementAttributes) {
        String output = ""

        output += "id=\"${attrs.id ?: attrs.name}\" "

        html5AutoRepeatAttributes.each {currentAttribute ->
            if (attrs[currentAttribute]) {
                output += "$currentAttribute=\"$currentAttribute\" "
            }
        }

        htmlElementAttributes.each { currentAttribute ->
            if (attrs[currentAttribute]) {
                output += "${currentAttribute}=\"${attrs[currentAttribute]}\" "
            }
        }

        extractCustomAttributes(attrs, htmlElementAttributes).each { customAttribute ->
            output += "$customAttribute=\"${attrs[customAttribute]}\" "
        }

        output
    }

    static List<String> extractCustomAttributes(Map attrs, List<String> htmlElementAttributes) {
        subtract(subtract(attrs.keySet(), html5AutoRepeatAttributes), htmlElementAttributes)
    }
}
