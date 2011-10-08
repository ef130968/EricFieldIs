package ericfieldis.profile.components

class TraitController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [traitInstanceList: Trait.list(params), traitInstanceTotal: Trait.count()]
    }

    def create = {
        def traitInstance = new Trait()
        traitInstance.properties = params
        return [traitInstance: traitInstance]
    }

    def save = {
        def traitInstance = new Trait(params)
        if (traitInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'trait.label', default: 'Trait'), traitInstance.id])}"
            redirect(action: "show", id: traitInstance.id)
        }
        else {
            render(view: "create", model: [traitInstance: traitInstance])
        }
    }

    def show = {
        def traitInstance = Trait.get(params.id)
        if (!traitInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'trait.label', default: 'Trait'), params.id])}"
            redirect(action: "list")
        }
        else {
            [traitInstance: traitInstance]
        }
    }

    def edit = {
        def traitInstance = Trait.get(params.id)
        if (!traitInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'trait.label', default: 'Trait'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [traitInstance: traitInstance]
        }
    }

    def update = {
        def traitInstance = Trait.get(params.id)
        if (traitInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (traitInstance.version > version) {
                    
                    traitInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'trait.label', default: 'Trait')] as Object[], "Another user has updated this Trait while you were editing")
                    render(view: "edit", model: [traitInstance: traitInstance])
                    return
                }
            }
            traitInstance.properties = params
            if (!traitInstance.hasErrors() && traitInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'trait.label', default: 'Trait'), traitInstance.id])}"
                redirect(action: "show", id: traitInstance.id)
            }
            else {
                render(view: "edit", model: [traitInstance: traitInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'trait.label', default: 'Trait'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def traitInstance = Trait.get(params.id)
        if (traitInstance) {
            try {
                traitInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'trait.label', default: 'Trait'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'trait.label', default: 'Trait'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'trait.label', default: 'Trait'), params.id])}"
            redirect(action: "list")
        }
    }
}
