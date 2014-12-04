class UrlMappings {

	static mappings = {
		"/movies(.$format)?"(controller: 'movieRip') {
    		action = [GET: 'index', POST: 'save']
    	}

    	"/movies/${id}(.$format)?"(controller: 'movieRip', action: 'show')

    	"/"(view:"/index")
        "500"(view:'/error')
   	}
}
