package ericfieldis

class FriendController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [friendInstanceList: Friend.list(params), friendInstanceTotal: Friend.count()]
    }

    def create = {
        def friendInstance = new Friend()
        friendInstance.properties = params
        return [friendInstance: friendInstance]
    }

    def save = {
        def friendInstance = new Friend(params)
        if (friendInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'friend.label', default: 'Friend'), friendInstance.id])}"
            redirect(action: "show", id: friendInstance.id)
        }
        else {
            render(view: "create", model: [friendInstance: friendInstance])
        }
    }

    def show = {
        def friendInstance = Friend.get(params.id)
        if (!friendInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'friend.label', default: 'Friend'), params.id])}"
            redirect(action: "list")
        }
        else {
            [friendInstance: friendInstance]
        }
    }

    def edit = {
        def friendInstance = Friend.get(params.id)
        if (!friendInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'friend.label', default: 'Friend'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [friendInstance: friendInstance]
        }
    }

    def update = {
        def friendInstance = Friend.get(params.id)
        if (friendInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (friendInstance.version > version) {

                    friendInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'friend.label', default: 'Friend')] as Object[], "Another user has updated this Friend while you were editing")
                    render(view: "edit", model: [friendInstance: friendInstance])
                    return
                }
            }
            friendInstance.properties = params
            if (!friendInstance.hasErrors() && friendInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'friend.label', default: 'Friend'), friendInstance.id])}"
                redirect(action: "show", id: friendInstance.id)
            }
            else {
                render(view: "edit", model: [friendInstance: friendInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'friend.label', default: 'Friend'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def friendInstance = Friend.get(params.id)
        if (friendInstance) {
            try {
                friendInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'friend.label', default: 'Friend'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'friend.label', default: 'Friend'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'friend.label', default: 'Friend'), params.id])}"
            redirect(action: "list")
        }
    }
}
