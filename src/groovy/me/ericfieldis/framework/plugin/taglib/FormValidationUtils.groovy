package me.ericfieldis.framework.plugin.taglib

/**
 * Created by vincentgiguere 
 * 11-06-22 - 10:14 PM
 * Copyright © 2011 publicprivatecareer.com
 */

class FormValidationUtils {

    public static isFieldRequired(def bean, def field) {
        boolean required = false
        if (bean.constraints?."${field}"?.blank != null) {
            if (bean.constraints."${field}".blank == false) {
                required = true
            }
        }
        if (bean.constraints?."${field}"?.nullable != null) {
            if (bean.constraints."${field}".nullable == false) {
                required = true
            }
        }

        return required
    }
}
