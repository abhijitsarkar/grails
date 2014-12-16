import name.abhijitsarkar.moviedatabase.service.ServiceHelper
import name.abhijitsarkar.moviedatabase.service.MovieRipIndexService
 
beans = {
	serviceHelper(ServiceHelper)

    movieRipIndexService(MovieRipIndexService) {
    	genres = '#{serviceHelper.genres}'
    	includes = '#{serviceHelper.includes}'
    }

    xmlns aop:'http://www.springframework.org/schema/aop'
    aop{
        config('proxy-target-class': true)
    }
}