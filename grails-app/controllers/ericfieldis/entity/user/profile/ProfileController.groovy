package ericfieldis.entity.user.profile

import ericfieldis.entity.user.User
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.Authentication
import org.weceem.controllers.WcmContentController
import me.ericfieldis.servlet.CookieManagement
import ericfieldis.entity.user.command.UpdateUserCommand
import ericfieldis.entity.citizen.Citizen

@Mixin(CookieManagement)
class ProfileController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def springSecurityUtils
    def CacheHeadersService

    def me = {
        boolean rememberMeCookieExists = findCookie("grails_remember_me") != null
        boolean userCookieExists = findCookie("ThisIsMe.userCookie") != null
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication()
        if(authentication.principal != "anonymousUser") {
            User userInstance = User.findByUsername(authentication.principal.username)
            if (userInstance) {
                if(!userCookieExists) {
                    if(rememberMeCookieExists) {
                        redirect(controller: "login")
                        return
                    }
                    redirect(controller: "logout")
                    return
                }
                UpdateUserCommand userCommand = new UpdateUserCommand(userId: userInstance.id).loadUser()
                //userCommand.errors.rejectValue('passwordConfirmation', 'user.settings.error.invalidPasswordConfirmationValue')
                Citizen citizen = Citizen.get(userCommand.citizenId)
                request[WcmContentController.REQUEST_ATTRIBUTE_PREPARED_MODEL] = [userCommand: userCommand, citizen: citizen]
                def uri = params.uri ?: '/me/welcome'
                params.clear()
                params.uri = uri
                forward(controller: 'wcmContent', action: 'show', params: params)
            }
        }
        else {
            if(userCookieExists) {
                redirect(controller: "logout")
                return
            }
            redirect(controller: "me", action: "index")
        }
    }

    def admin = {
        redirect(uri: "/wcm/admin/repository")
    }

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        [profileInstanceList: Profile.list(params), profileInstanceTotal: Profile.count()]
    }

    def create = {
        def profileInstance = new Profile()
        profileInstance.properties = params
        return [profileInstance: profileInstance]
    }

    def save = {
        def profileInstance = new Profile(params)
        if (profileInstance.save(flush: true)) {
            flash.message = "${message(code: 'default.created.message', args: [message(code: 'profile.label', default: 'Profile'), profileInstance.id])}"
            redirect(action: "show", id: profileInstance.id)
        }
        else {
            render(view: "create", model: [profileInstance: profileInstance])
        }
    }

    def show = {
        def profileInstance = Profile.get(params.id)
        if (!profileInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'profile.label', default: 'Profile'), params.id])}"
            redirect(action: "list")
        }
        else {
            [profileInstance: profileInstance]
        }
    }

    def edit = {
        def profileInstance = Profile.get(params.id)
        if (!profileInstance) {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'profile.label', default: 'Profile'), params.id])}"
            redirect(action: "list")
        }
        else {
            return [profileInstance: profileInstance]
        }
    }

    def update = {
        def profileInstance = Profile.get(params.id)
        if (profileInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (profileInstance.version > version) {

                    profileInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'profile.label', default: 'Profile')] as Object[], "Another user has updated this Profile while you were editing")
                    render(view: "edit", model: [profileInstance: profileInstance])
                    return
                }
            }
            profileInstance.properties = params
            if (!profileInstance.hasErrors() && profileInstance.save(flush: true)) {
                flash.message = "${message(code: 'default.updated.message', args: [message(code: 'profile.label', default: 'Profile'), profileInstance.id])}"
                redirect(action: "show", id: profileInstance.id)
            }
            else {
                render(view: "edit", model: [profileInstance: profileInstance])
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'profile.label', default: 'Profile'), params.id])}"
            redirect(action: "list")
        }
    }

    def delete = {
        def profileInstance = Profile.get(params.id)
        if (profileInstance) {
            try {
                profileInstance.delete(flush: true)
                flash.message = "${message(code: 'default.deleted.message', args: [message(code: 'profile.label', default: 'Profile'), params.id])}"
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = "${message(code: 'default.not.deleted.message', args: [message(code: 'profile.label', default: 'Profile'), params.id])}"
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'profile.label', default: 'Profile'), params.id])}"
            redirect(action: "list")
        }
    }
}
