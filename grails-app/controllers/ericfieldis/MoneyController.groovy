package ericfieldis

class MoneyController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [moneyInstanceList: Money.list(params), moneyInstanceTotal: Money.count()]
    }

    def create = {
        def moneyInstance = new Money()
        moneyInstance.properties = params
        return [moneyInstance: moneyInstance]
    }

    def save = {
        def moneyInstance = new Money(params)
        if (moneyInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'money.label', default: 'Money'), moneyInstance.id])}"
            redirect(action: "show", id: moneyInstance.id)
        }
        else {
            render(view: "create", model: [moneyInstance: moneyInstance])
        }
    }

    def show = {
        def moneyInstance = Money.get(params.id)
        if (!moneyInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'money.label', default: 'Money'), params.id])}"
            redirect(action: "list")
        }
        else {
            [moneyInstance: moneyInstance]
        }
    }

    def edit = {
        def moneyInstance = Money.get(params.id)
        if (!moneyInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'money.label', default: 'Money'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [moneyInstance: moneyInstance]
        }
    }

    def update = {
        def moneyInstance = Money.get(params.id)
        if (moneyInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (moneyInstance.version > version) {

                    moneyInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'money.label', default: 'Money')] as Object[], "Another user has updated this Money while you were editing")
                    render(view: "edit", model: [moneyInstance: moneyInstance])
                    return
                }
            }
            moneyInstance.properties = params
            if (!moneyInstance.hasErrors() && moneyInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'money.label', default: 'Money'), moneyInstance.id])}"
                redirect(action: "show", id: moneyInstance.id)
            }
            else {
                render(view: "edit", model: [moneyInstance: moneyInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'money.label', default: 'Money'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def moneyInstance = Money.get(params.id)
        if (moneyInstance) {
            try {
                moneyInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'money.label', default: 'Money'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'money.label', default: 'Money'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'money.label', default: 'Money'), params.id])}"
            redirect(action: "list")
        }
    }
}
