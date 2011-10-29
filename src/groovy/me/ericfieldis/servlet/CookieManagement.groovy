package me.ericfieldis.servlet

import javax.servlet.http.Cookie
import org.codehaus.groovy.grails.commons.ApplicationHolder
import javax.servlet.http.HttpServletResponse

/**
 * Created by IntelliJ IDEA.
 * User: efield
 * Date: 27/10/11
 * Time: 7:40 PM
 * To change this template use File | Settings | File Templates.
 */

public class CookieManagement {

    void createCookie(String name, String value, String path) {
        def userCookie = new Cookie(name, value)
        userCookie.path = path
        response.addCookie(userCookie)
    }

    void deleteCookie(String name, String path) {
        Cookie userCookie = findCookie(name)
        if(userCookie) {
            userCookie.setMaxAge(0)
            userCookie.path = path
            response.addCookie(userCookie)
            println "Killed the cookie monster!"
        }
    }

    Cookie findCookie(String name) {
        Cookie[] userCookies = request.getCookies()
        if(userCookies) {
            for(int cookieIndex = 0; cookieIndex < userCookies.length; cookieIndex++) {
                Cookie userCookie = userCookies[cookieIndex];
                if (userCookie.getName() == name) {
                    return userCookie
                }
            }
        }
        null
    }
}

