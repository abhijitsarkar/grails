class UrlMappings {

	static mappings = {
        "/movies(.(*))"(controller: 'movieRip', action: 'index', method: 'GET')

        "500"(view:'/error')
	}
}
