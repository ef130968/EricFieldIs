package ericfieldis.profile.components

class ValueController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [valueInstanceList: Value.list(params), valueInstanceTotal: Value.count()]
    }

    def create = {
        def valueInstance = new Value()
        valueInstance.properties = params
        return [valueInstance: valueInstance]
    }

    def save = {
        def valueInstance = new Value(params)
        if (valueInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'value.label', default: 'Value'), valueInstance.id])}"
            redirect(action: "show", id: valueInstance.id)
        }
        else {
            render(view: "create", model: [valueInstance: valueInstance])
        }
    }

    def show = {
        def valueInstance = Value.get(params.id)
        if (!valueInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'value.label', default: 'Value'), params.id])}"
            redirect(action: "list")
        }
        else {
            [valueInstance: valueInstance]
        }
    }

    def edit = {
        def valueInstance = Value.get(params.id)
        if (!valueInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'value.label', default: 'Value'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [valueInstance: valueInstance]
        }
    }

    def update = {
        def valueInstance = Value.get(params.id)
        if (valueInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (valueInstance.version > version) {

                    valueInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'value.label', default: 'Value')] as Object[], "Another user has updated this Value while you were editing")
                    render(view: "edit", model: [valueInstance: valueInstance])
                    return
                }
            }
            valueInstance.properties = params
            if (!valueInstance.hasErrors() && valueInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'value.label', default: 'Value'), valueInstance.id])}"
                redirect(action: "show", id: valueInstance.id)
            }
            else {
                render(view: "edit", model: [valueInstance: valueInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'value.label', default: 'Value'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def valueInstance = Value.get(params.id)
        if (valueInstance) {
            try {
                valueInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'value.label', default: 'Value'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'value.label', default: 'Value'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'value.label', default: 'Value'), params.id])}"
            redirect(action: "list")
        }
    }
}
