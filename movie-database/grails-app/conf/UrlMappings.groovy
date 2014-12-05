class UrlMappings {

	static mappings = {
		"/movies(.$format)?"(controller: 'movieRip') {
    		action = [GET: 'index', POST: 'save']
    	}

    	"/movies/${id}(.$format)?"(controller: 'movieRip', action: 'show')
    	"/movies/create(.$format)?"(controller: 'movieRip', action: 'create')

    	"/"(view:"/index")
        "500"(view:'/error')
   	}
}
