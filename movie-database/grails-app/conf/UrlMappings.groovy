class UrlMappings {

	static mappings = {
        "/movies(.$format)?"(controller: 'movieRip', action: 'show', method: 'GET')
        "/movies(.$format)?"(controller: 'movieRip', action: 'save', method: 'POST')

        "500"(view:'/error')
	}
}
