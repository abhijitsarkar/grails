
<%@ page import="name.abhijitsarkar.moviedatabase.domain.MovieRip" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'movieRip.label', default: 'MovieRip')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-movieRip" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			</ul>
		</div>
		<div id="list-movieRip" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<th><g:message code="movieRip.title.label" default="Title" /></th>
					
						<g:sortableColumn property="parent" title="${message(code: 'movieRip.parent.label', default: 'Parent')}" />
					
						<g:sortableColumn property="fileSize" title="${message(code: 'movieRip.fileSize.label', default: 'File Size')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${movieRips}" status="i" var="movieRip">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link class="show" uri="/movies/${fieldValue(bean: movieRip, field: 'id')}">${fieldValue(bean: movieRip, field: "title")}</g:link></td>
					
						<td>${fieldValue(bean: movieRip, field: "parent")}</td>
					
						<td>${fieldValue(bean: movieRip, field: "fileSize")}</td>
					
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
