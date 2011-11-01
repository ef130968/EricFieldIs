package ericfieldis.entity.user

import org.springframework.web.multipart.MultipartHttpServletRequest
import org.springframework.web.multipart.commons.CommonsMultipartFile
import me.ericfieldis.servlet.CookieManagement
import me.ericfieldis.servlet.ImageManagement
import ericfieldis.entity.user.command.UpdateUserCommand
import org.weceem.controllers.WcmContentController
import ericfieldis.entity.citizen.Citizen

@Mixin(CookieManagement)
@Mixin(ImageManagement)
class UserController {

    def springSecurityService
    def rememberMeServices

    static allowedMethods = [save: "POST", updateByUser: "POST", updateByAdmin: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def login = {
        createCookie("ThisIsMe.userCookie", java.util.UUID.randomUUID().toString(), "/" + grailsApplication.metadata.'app.name')
        redirect(controller: "profile", action: "me")
    }

    def logout = {
        deleteCookie("ThisIsMe.userCookie", "/" + grailsApplication.metadata.'app.name')
        redirect(controller: "profile", action: "me")
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
            [userInstance: userInstance]
        }
    }

    def getAvatarImageThumbnail = {
        retrieveImageThumbnailFromDB(params.id, 190)
    }

    def getAvatarImage = {
        retrieveImageFromDB(params.id)
    }

    def updateByUser = {UpdateUserCommand userCommand ->
        if(request instanceof MultipartHttpServletRequest) {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request
            params.multipartFile = (CommonsMultipartFile) multipartRequest.getFile("avatarFile")
            if(userCommand.validate()) {
                if(userCommand.execute(params)) {
                    flash.message = "${message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), params.userId])}"
                    redirect(controller: "profile", action: "me")
                    return
                }
            }
            Citizen citizen = Citizen.get(userCommand.citizenId)
            request[WcmContentController.REQUEST_ATTRIBUTE_PREPARED_MODEL] = [userCommand: userCommand, citizen: citizen]
            def uri = '/me/settings'
            params.clear()
            params.uri = uri
            forward(controller: 'wcmContent', action: 'show', params: params)
        }
    }

    def updateByAdmin = {
        User userInstance = User.get(params.id)
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
                redirect(action: "show", id: userInstance.id)
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

    def changeUsername = {
        def userInstance = User.get(params.id)
        if (userInstance) {
            userInstance.setUsername(params.value)
            if(!userInstance.hasErrors() && userInstance.save(flush: true))
            {
                springSecurityService.reauthenticate(params.value)
                rememberMeServices.onLoginSuccess(request, response, springSecurityService.authentication)
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])}"
            }
        }
    }

    def changeCitizenFirstName = {
        def userInstance = User.get(params.id)
        if (userInstance) {
            userInstance.citizen.setFirstName(params.value)
            if(!userInstance.hasErrors() && userInstance.save(flush: true))
            {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])}"
            }
        }
    }
}
