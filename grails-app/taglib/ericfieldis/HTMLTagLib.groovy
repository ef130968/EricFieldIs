package ericfieldis

import me.ericfieldis.framework.plugin.taglib.FormValidationUtils
//import me.ericfieldis.framework.test.geb.GebUtils

class HTMLTagLib {
    static namespace = "html"

    def securedLink = {

    }

    def form = { attrs, body ->
        attrs.novalidate = "true" // (SL) disable default HTML5 form validation
        out << g.form(attrs, body)
    }

    def hiddenField = { attrs ->
        out << g.hiddenField(attrs)
    }

    def text = { attrs, body ->
        def bean = attrs.remove('bean')

        attrs.type ?: 'text'
        attrs.gebid = com.ppc.framework.test.geb.GebUtils.generateId(controllerName, actionName, attrs.name)

        if (attrs.type != 'checkbox') attrs.value = attrs.value ?: bean?."${attrs.name}"?.encodeAsHTML()

        if (com.ppc.framework.plugin.taglib.FormValidationUtils.isFieldRequired(bean, attrs.name)) attrs.required = 'required="required"'

        out << g.render(template: '/taglib/html/input', model: [attrs: attrs, label: g.message(code: attrs.remove('label'))])
    }

    def email = {attrs, body ->
        attrs.type = 'email'

        out << text(attrs, body)
    }

    def password = { attrs, body ->
        attrs.type = 'password'

        out << text(attrs, body)
    }

    def checkbox = { attrs, body ->
        def bean = attrs.bean

        attrs.type = 'checkbox'
        attrs.remove('value')
        if (bean?."${attrs.name}" && Boolean.valueOf(bean?."${attrs.name}")) attrs.checked = 'checked="checked"'

        out << text(attrs, body)
    }

    def checkboxGroup = { attrs ->
        attrs.label = g.message(code: attrs.label)
        attrs.gebid = com.ppc.framework.test.geb.GebUtils.generateId(controllerName, actionName, attrs.name)

        out << g.render(template: '/taglib/html/checkboxGroup', model: attrs)
    }

    def radioGroup = { attrs ->
        def bean = attrs.bean

        attrs.selected = attrs.selected ?: bean?."${attrs.name}"?.encodeAsHTML()
        attrs.label = g.message(code: attrs.label)
        attrs.gebid = com.ppc.framework.test.geb.GebUtils.generateId(controllerName, actionName, attrs.name)

        out << g.render(template: '/taglib/html/radioGroup', model: attrs)
    }

    def select = { attrs, body ->
        def bean = attrs.remove('bean')
        if (bean) {
            attrs.required = com.ppc.framework.plugin.taglib.FormValidationUtils.isFieldRequired(bean, attrs.name) ? 'required' : ''
        }

        attrs.label = g.message(code: attrs.label)
        attrs.required = com.ppc.framework.plugin.taglib.FormValidationUtils.isFieldRequired(bean, attrs.name) ? 'required' : ''
        attrs.gebid = com.ppc.framework.test.geb.GebUtils.generateId(controllerName, actionName, attrs.name)

        if (attrs.noSelection) attrs.noSelection = ['': "${g.message(code: attrs.noSelection)}"]

        out << g.render(template: '/taglib/html/select', model: attrs)
    }

    def textArea = { attrs ->
        def bean = attrs.remove('bean')
        attrs.gebid = com.ppc.framework.test.geb.GebUtils.generateId(controllerName, actionName, attrs.name)

        out << g.render(template: '/taglib/html/textarea', model: [attrs: attrs, label: g.message(code: attrs.remove('label')), value: bean?."${attrs.name}"?.encodeAsHTML()])
    }

    def displayText = { attrs, body ->
        if (body() != "" & attrs.value == null) {
            attrs.value = body()
        }
        if (attrs.label) {
            attrs.label = g.message(code: attrs.label)
        }
        if (attrs.encodeAsHTML == "true") {
            attrs.value = attrs.value.encodeAsHTML()
        }
        attrs.gebid = com.ppc.framework.test.geb.GebUtils.generateId(controllerName, actionName, attrs.name)

        out << g.render(template: '/taglib/html/displayText', model: attrs)
    }

    def title = { attrs ->
        def value = attrs.value

        if (!attrs.encodeAsHTML || attrs.encodeAsHTML == "true") {
            value = value.encodeAsHTML()
        }

        out << "<div ${attrs.class ? ' class=\"' + attrs.class + '\"' : ''} >$value</div>"
    }

    def createLink = { attrs ->
        out << g.createLink(attrs)
    }

    def link = { attrs, body ->
        attrs.gebid = com.ppc.framework.test.geb.GebUtils.generateId(controllerName, actionName, attrs.name)

        out << g.link(attrs, body)
    }

    def sortableColumn = { attrs ->
        attrs.gebid = com.ppc.framework.test.geb.GebUtils.generateId(controllerName, actionName, attrs.name + "-title")

        out << g.sortableColumn(attrs)
    }
}
