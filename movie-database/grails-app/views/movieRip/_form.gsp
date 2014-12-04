<%@ page import="name.abhijitsarkar.moviedatabase.domain.MovieRip" %>



<div class="fieldcontain ${hasErrors(bean: movieRipInstance, field: 'director', 'error')} ">
	<label for="director">
		<g:message code="movieRip.director.label" default="Director" />
		
	</label>
	<g:select id="director" name="director.id" from="${name.abhijitsarkar.moviedatabase.domain.CastAndCrew.list()}" optionKey="id" value="${movieRipInstance?.director?.id}" class="many-to-one" noSelection="['null': '']"/>

</div>

<div class="fieldcontain ${hasErrors(bean: movieRipInstance, field: 'imdbRating', 'error')} ">
	<label for="imdbRating">
		<g:message code="movieRip.imdbRating.label" default="Imdb Rating" />
		
	</label>
	<g:field name="imdbRating" value="${fieldValue(bean: movieRipInstance, field: 'imdbRating')}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: movieRipInstance, field: 'imdbURL', 'error')} ">
	<label for="imdbURL">
		<g:message code="movieRip.imdbURL.label" default="Imdb URL" />
		
	</label>
	<g:field type="url" name="imdbURL" value="${movieRipInstance?.imdbURL}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: movieRipInstance, field: 'stars', 'error')} ">
	<label for="stars">
		<g:message code="movieRip.stars.label" default="Stars" />
		
	</label>
	<g:select name="stars" from="${name.abhijitsarkar.moviedatabase.domain.CastAndCrew.list()}" multiple="multiple" optionKey="id" size="5" value="${movieRipInstance?.stars*.id}" class="many-to-many"/>

</div>

<div class="fieldcontain ${hasErrors(bean: movieRipInstance, field: 'parent', 'error')} ">
	<label for="parent">
		<g:message code="movieRip.parent.label" default="Parent" />
		
	</label>
	<g:textField name="parent" value="${movieRipInstance?.parent}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: movieRipInstance, field: 'fileExtension', 'error')} required">
	<label for="fileExtension">
		<g:message code="movieRip.fileExtension.label" default="File Extension" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="fileExtension" required="" value="${movieRipInstance?.fileExtension}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: movieRipInstance, field: 'fileSize', 'error')} required">
	<label for="fileSize">
		<g:message code="movieRip.fileSize.label" default="File Size" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="fileSize" type="number" value="${movieRipInstance.fileSize}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: movieRipInstance, field: 'genres', 'error')} ">
	<label for="genres">
		<g:message code="movieRip.genres.label" default="Genres" />
		
	</label>
	

</div>

<div class="fieldcontain ${hasErrors(bean: movieRipInstance, field: 'releaseYear', 'error')} required">
	<label for="releaseYear">
		<g:message code="movieRip.releaseYear.label" default="Release Year" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="releaseYear" type="number" value="${movieRipInstance.releaseYear}" required=""/>

</div>

<div class="fieldcontain ${hasErrors(bean: movieRipInstance, field: 'title', 'error')} required">
	<label for="title">
		<g:message code="movieRip.title.label" default="Title" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="title" required="" value="${movieRipInstance?.title}"/>

</div>

