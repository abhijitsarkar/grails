
<%@ page import="name.abhijitsarkar.moviedatabase.domain.MovieRip" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'movieRip.label', default: 'Movie rip')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-movieRip" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index">Movie rips List</g:link></li>
			</ul>
		</div>
		<div id="show-movieRip" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list movieRip">
			
				<g:if test="${movieRip?.director}">
				<li class="fieldcontain">
					<span id="director-label" class="property-label"><g:message code="movieRip.director.label" default="Director" /></span>
						<span class="property-value" aria-labelledby="director-label">${movieRip?.director?.name?.encodeAsHTML()}</span>
				</li>
				</g:if>
			
				<g:if test="${movieRip?.imdbRating}">
				<li class="fieldcontain">
					<span id="imdbRating-label" class="property-label"><g:message code="movieRip.imdbRating.label" default="Imdb Rating" /></span>
						<span class="property-value" aria-labelledby="imdbRating-label"><g:fieldValue bean="${movieRip}" field="imdbRating"/></span>
				</li>
				</g:if>
			
				<g:if test="${movieRip?.imdbURL}">
				<li class="fieldcontain">
					<span id="imdbURL-label" class="property-label"><g:message code="movieRip.imdbURL.label" default="Imdb URL" /></span>
						<span class="property-value" aria-labelledby="imdbURL-label"><g:fieldValue bean="${movieRip}" field="imdbURL"/></span>
				</li>
				</g:if>
			
				<g:if test="${movieRip?.stars}">
				<li class="fieldcontain">
					<span id="stars-label" class="property-label"><g:message code="movieRip.stars.label" default="Stars" /></span>
						<span class="property-value" aria-labelledby="stars-label">${movieRip?.stars?.name?.join(', ')}</span>
				</li>
				</g:if>
			
				<g:if test="${movieRip?.parent}">
				<li class="fieldcontain">
					<span id="parent-label" class="property-label"><g:message code="movieRip.parent.label" default="Parent" /></span>
						<span class="property-value" aria-labelledby="parent-label"><g:fieldValue bean="${movieRip}" field="parent"/></span>
				</li>
				</g:if>
			
				<g:if test="${movieRip?.fileExtension}">
				<li class="fieldcontain">
					<span id="fileExtension-label" class="property-label"><g:message code="movieRip.fileExtension.label" default="File Extension" /></span>
						<span class="property-value" aria-labelledby="fileExtension-label"><g:fieldValue bean="${movieRip}" field="fileExtension"/></span>
				</li>
				</g:if>
			
				<g:if test="${movieRip?.fileSize}">
				<li class="fieldcontain">
					<span id="fileSize-label" class="property-label"><g:message code="movieRip.fileSize.label" default="File Size" /></span>
						<span class="property-value" aria-labelledby="fileSize-label"><g:fieldValue bean="${movieRip}" field="fileSize"/></span>
				</li>
				</g:if>
			
				<g:if test="${movieRip?.genres}">
				<li class="fieldcontain">
					<span id="genres-label" class="property-label"><g:message code="movieRip.genres.label" default="Genres" /></span>
						<span class="property-value" aria-labelledby="genres-label">${movieRip?.genres?.join(', ')}</span>
				</li>
				</g:if>
			
				<g:if test="${movieRip?.releaseYear}">
				<li class="fieldcontain">
					<span id="releaseYear-label" class="property-label"><g:message code="movieRip.releaseYear.label" default="Release Year" /></span>
						<span class="property-value" aria-labelledby="releaseYear-label">${movieRip?.releaseYear}</span>
				</li>
				</g:if>
			
				<g:if test="${movieRip?.title}">
				<li class="fieldcontain">
					<span id="title-label" class="property-label"><g:message code="movieRip.title.label" default="Title" /></span>
						<span class="property-value" aria-labelledby="title-label"><g:fieldValue bean="${movieRip}" field="title"/></span>
				</li>
				</g:if>
			
			</ol>
		</div>
	</body>
</html>
