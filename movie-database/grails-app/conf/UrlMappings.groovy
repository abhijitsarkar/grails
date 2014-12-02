class UrlMappings {

	static mappings = {
		"/movies(.$format)?"(controller: 'movieRip') {
    		action = [GET: 'show', POST: 'save']
    	}
   	}
}
