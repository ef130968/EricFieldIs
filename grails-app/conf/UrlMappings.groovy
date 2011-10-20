import grails.plugins.springsecurity.Secured

class UrlMappings {

	static mappings = {
        "500" (view:'/error')
		"/$controller/$action?/$id?" {
			constraints {
				// apply constraints here
			}
		}
        "/" (controller: "profile", action: "me")

        def adminURI = "/${WeceemPluginUrlMappings.ADMIN_PREFIX}"
        invokeMethod(adminURI, {
            controller = "profile"
            action = "admin"
        })
    }

}
