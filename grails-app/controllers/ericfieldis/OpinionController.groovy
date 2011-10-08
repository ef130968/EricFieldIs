package ericfieldis

import ericfieldis.profile.components.Opinion

class OpinionController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [opinionInstanceList: Opinion.list(params), opinionInstanceTotal: Opinion.count()]
    }

    def create = {
        def opinionInstance = new Opinion()
        opinionInstance.properties = params
        return [opinionInstance: opinionInstance]
    }

    def save = {
        def opinionInstance = new Opinion(params)
        if (opinionInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'opinion.label', default: 'Opinion'), opinionInstance.id])}"
            redirect(action: "show", id: opinionInstance.id)
        }
        else {
            render(view: "create", model: [opinionInstance: opinionInstance])
        }
    }

    def show = {
        def opinionInstance = Opinion.get(params.id)
        if (!opinionInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'opinion.label', default: 'Opinion'), params.id])}"
            redirect(action: "list")
        }
        else {
            [opinionInstance: opinionInstance]
        }
    }

    def edit = {
        def opinionInstance = Opinion.get(params.id)
        if (!opinionInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'opinion.label', default: 'Opinion'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [opinionInstance: opinionInstance]
        }
    }

    def update = {
        def opinionInstance = Opinion.get(params.id)
        if (opinionInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (opinionInstance.version > version) {

                    opinionInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'opinion.label', default: 'Opinion')] as Object[], "Another user has updated this Opinion while you were editing")
                    render(view: "edit", model: [opinionInstance: opinionInstance])
                    return
                }
            }
            opinionInstance.properties = params
            if (!opinionInstance.hasErrors() && opinionInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'opinion.label', default: 'Opinion'), opinionInstance.id])}"
                redirect(action: "show", id: opinionInstance.id)
            }
            else {
                render(view: "edit", model: [opinionInstance: opinionInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'opinion.label', default: 'Opinion'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def opinionInstance = Opinion.get(params.id)
        if (opinionInstance) {
            try {
                opinionInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'opinion.label', default: 'Opinion'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'opinion.label', default: 'Opinion'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'opinion.label', default: 'Opinion'), params.id])}"
            redirect(action: "list")
        }
    }
}
