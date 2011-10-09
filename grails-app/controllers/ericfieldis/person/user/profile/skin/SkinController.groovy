package ericfieldis.person.user.profile.skin

class SkinController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [skinInstanceList: Skin.list(params), skinInstanceTotal: Skin.count()]
    }

    def create = {
        def skinInstance = new Skin()
        skinInstance.properties = params
        return [skinInstance: skinInstance]
    }

    def save = {
        def skinInstance = new Skin(params)
        if (skinInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'skin.label', default: 'Skin'), skinInstance.id])}"
            redirect(action: "show", id: skinInstance.id)
        }
        else {
            render(view: "create", model: [skinInstance: skinInstance])
        }
    }

    def show = {
        def skinInstance = Skin.get(params.id)
        if (!skinInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'skin.label', default: 'Skin'), params.id])}"
            redirect(action: "list")
        }
        else {
            [skinInstance: skinInstance]
        }
    }

    def edit = {
        def skinInstance = Skin.get(params.id)
        if (!skinInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'skin.label', default: 'Skin'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [skinInstance: skinInstance]
        }
    }

    def update = {
        def skinInstance = Skin.get(params.id)
        if (skinInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (skinInstance.version > version) {

                    skinInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'skin.label', default: 'Skin')] as Object[], "Another user has updated this Skin while you were editing")
                    render(view: "edit", model: [skinInstance: skinInstance])
                    return
                }
            }
            skinInstance.properties = params
            if (!skinInstance.hasErrors() && skinInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'skin.label', default: 'Skin'), skinInstance.id])}"
                redirect(action: "show", id: skinInstance.id)
            }
            else {
                render(view: "edit", model: [skinInstance: skinInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'skin.label', default: 'Skin'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def skinInstance = Skin.get(params.id)
        if (skinInstance) {
            try {
                skinInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'skin.label', default: 'Skin'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'skin.label', default: 'Skin'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'skin.label', default: 'Skin'), params.id])}"
            redirect(action: "list")
        }
    }
}
