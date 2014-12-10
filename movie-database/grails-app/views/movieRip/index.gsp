
<%@ page import="name.abhijitsarkar.moviedatabase.domain.MovieRip" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'movieRip.label', default: 'Movie rips')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-movieRip" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<g:form relativeUri="movies" method="get">
			<fieldset class="form">
				<div class="fieldcontain">
					<label for="fieldName">
						<g:message code="movieRips.fieldName.label" default="Field Name" />
					</label>
					<g:textField name="fieldName" value="${command?.fieldName}" required="false"/>
				</div>
				<div class="fieldcontain">
					<label for="fieldValue">
						<g:message code="movieRips.fieldValue.label" default="Field Value" />
					</label>
					<g:textField name="fieldValue" value="${command?.fieldValue}" required="false"/>
				</div>
			</fieldset>
			<fieldset class="buttons">
				<g:submitButton name="search" class="search" value="${message(code: 'default.button.search.label', default: 'Search')}" />
			</fieldset>
		</g:form>
		<div id="list-movieRip" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
						<th><g:message code="movieRip.title.label" default="Title" /></th>
						<g:sortableColumn property="genres" title="${message(code: 'movieRip.genres.label', default: 'Genres')}" />
					</tr>
				</thead>
				<tbody>
				<g:each in="${command?.movieRips}" status="i" var="movieRip">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
						<td><g:link class="show" uri="/movies/${fieldValue(bean: movieRip, field: 'id')}">${fieldValue(bean: movieRip, field: "title")}</g:link></td>
						<td>${movieRip.genres.join(', ')}</td>
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${count ?: 0}" />
			</div>
		</div>
	</body>
</html>
