package ericfieldis.entity.user

import ericfieldis.FileUploadService
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.Authentication
import org.springframework.web.multipart.MultipartHttpServletRequest
import org.springframework.web.multipart.commons.CommonsMultipartFile
import javax.servlet.http.Cookie

class UserController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def login = {
        def userCookie = new Cookie("ThisIsMe.userCookie", java.util.UUID.randomUUID().toString())
        userCookie.path = "/" + grailsApplication.metadata.'app.name'
        response.addCookie(userCookie)
        redirect(controller: "profile", action: "me")
    }

    def logout = {
        Cookie[] userCookies = request.getCookies()
        for(int cookieIndex=0; cookieIndex < userCookies.length; cookieIndex++) {
            Cookie userCookie = userCookies[cookieIndex];
            if (userCookie.getName() == "ThisIsMe.userCookie") {
                println "Found the cookie monster!"
                userCookie.setMaxAge(0)
                userCookie.path = "/" + grailsApplication.metadata.'app.name'
                response.addCookie(userCookie)
            }
        }
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

//    def getAvatar = {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication()
//
//        if(authentication.principal != "anonymousUser") {
//            User userInstance = User.findByUsername(authentication.principal.username)
//
//            //response.contentLength = userInstance.avatar.length
//            //response.contentType = "binary/octet-stream"
//            //response.sendRedirect(response.encodeRedirectUrl("/EricFieldIs/me/index"))
//
//            response.getOutputStream().write(userInstance.avatar)
//            response.getOutputStream().flush()
//
//
//            } else {
//                byte[] avatar = new byte[40000]
//                try {
//                    javax.servlet.ServletOutputStream out = response.getOutputStream()
//                    java.io.FileInputStream fileIn = new java.io.FileInputStream("/EricFieldIs/images/anonymous.jpg")
//                    try {
//                        int read = fileIn.read(avatar)
//                        while(read > 0) {
//                            out.write(avatar, 0, read)
//                        }
//                    } finally {
//                        fileIn.close()
//                    }
//                    out.flush()
//                    out.close()
//                } catch(java.io.IOException ioe){
//                    ioe.printStackTrace()
//                } catch(Exception e){
//                    e.printStackTrace()
//                }
//                response.getOutputStream().write(avatar)
//                response.getOutputStream().flush()
//                response.sendRedirect(response.encodeRedirectURL("/profile/me"))
//            }
//        }
//    }

    def updateByUser = {
        if(request instanceof MultipartHttpServletRequest) {
            MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication()
            User userInstance = User.findByUsername(authentication.principal.username)

            if (userInstance) {
                CommonsMultipartFile avatarFile = (CommonsMultipartFile) multipartRequest.getFile("avatarFile");
                FileUploadService fileUploadService = new FileUploadService()
                String filename = java.util.UUID.randomUUID()
                userInstance.avatarFile = fileUploadService.uploadFileToDisk(avatarFile, "${filename}.png", "WeceemFiles/me/avatars")
/*
                params.avatar = fileUploadService.uploadFileToDisk(avatarFile, "${userInstance.username}.png", "WeceemFiles/me/avatars")
                fileUploadService.uploadFileToDB(avatarFile, userInstance)
                redirect(controller: "me", action: "settings")
*/
/*
                if (params.version) {
                    def version = params.version.toLong()
                    if (userInstance.version > version) {
                        userInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'user.label', default: 'User')] as Object[], "Another user has updated this User while you were editing")
                        render(view: "edit", model: [userInstance: userInstance])
                        return
                    }
                }
*/
                if (!userInstance.hasErrors() && userInstance.save(flush: true)) {
                    flash.message = "${message(code: 'default.updated.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])}"
                    //redirect(action: "show", id: userInstance.id)
                }
/*
                else {
                    render(view: "edit", model: [userInstance: userInstance])
                }
*/
            }
            else {
                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
            }
            redirect(controller: "me", action: "index")
        }
    }

    def update = {
        User userInstance = User.get(params.id)

//        if(params.subaction == 'uploadAvatarFile') {
//            if(request instanceof MultipartHttpServletRequest) {
//                MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
//                Authentication authentication = SecurityContextHolder.getContext().getAuthentication()
//                userInstance = User.findByUsername(authentication.principal.username)
//                CommonsMultipartFile avatarFile = (CommonsMultipartFile) multiRequest.getFile("avatarFile");
//                FileUploadService fileUploadService = new FileUploadService()
//                String filename = java.util.UUID.randomUUID()
//                userInstance.avatarFile = fileUploadService.uploadFileToDisk(avatarFile, "${filename}.png", "WeceemFiles/me/avatars")
//                //params.avatar = fileUploadService.uploadFileToDisk(avatarFile, "${userInstance.username}.png", "WeceemFiles/me/avatars")
//                //fileUploadService.uploadFileToDB(avatarFile, userInstance)
//                //redirect(controller: "me", action: "settings")
//            }
//            else {
//                flash.message = "${message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])}"
//            }
//        }
//        else {
//            userInstance = User.get(params.id)
//        }

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
}
