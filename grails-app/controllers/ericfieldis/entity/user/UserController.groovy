package ericfieldis.entity.user

import ericfieldis.FileUploadService
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.Authentication

class UserController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [userInstanceList: User.list(params), userInstanceTotal: User.count()]
    }

    def create = {
        def userInstance = new User()
        userInstance.properties = params
        return [userInstance: userInstance]
    }

    def save = {
        def userInstance = new User(params)
        if (userInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])}"
            redirect(action: "show", id: userInstance.id)
        }
        else {
            render(view: "create", model: [userInstance: userInstance])
        }
    }

    def show = {
        def userInstance = User.get(params.id)
        if (!userInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
            redirect(action: "list")
        }
        else {
            [userInstance: userInstance]
        }
    }

    def edit = {
        def userInstance = User.get(params.id)
        if (!userInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [userInstance: userInstance]
        }
    }

    def update = {
        User userInstance

        if(params.subaction == 'uploadAvatarFile') {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication()
            userInstance = User.findByUsername(authentication.principal.username)
            def avatarImage = request.getFile('avatarFile')
            FileUploadService fileUploadService = new FileUploadService()
            params.avatar = fileUploadService.uploadFile(avatarImage, "${userInstance.username}.png", "WeceemFiles/me/avatars")
            //params.avatar = fileUploadService.uploadFile(avatarImage, "${userInstance.username}.png", "avatars")
        } else {
            userInstance = User.get(params.id)
        }

        if (userInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (userInstance.version > version) {

                    userInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'user.label', default: 'User')] as Object[], "Another user has updated this User while you were editing")
                    render(view: "edit", model: [userInstance: userInstance])
                    return
                }
            }

            userInstance.properties = params
            if (!userInstance.hasErrors() && userInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])}"
                if(params.subaction == 'uploadAvatarFile') {
                    redirect(controller: "me", action: "settings")
                } else {
                    redirect(action: "show", id: userInstance.id)
                }
            }
            else {
                render(view: "edit", model: [userInstance: userInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def userInstance = User.get(params.id)
        if (userInstance) {
            try {
                userInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
            redirect(action: "list")
        }
    }
}
