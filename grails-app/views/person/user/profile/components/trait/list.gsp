<%@ page import="ericfieldis.profile.components.Trait" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'trait.label', default: 'Trait')}"/>
    <title><g:message code="default.list.label" args="[entityName]"/></title>
</head>

<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
    </span>
    <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label"
                                                                               args="[entityName]"/></g:link></span>
</div>

<div class="body">
    <h1><g:message code="default.list.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <div class="list">
        <table>
            <thead>
            <tr>

                <g:sortableColumn property="id" title="${message(code: 'trait.id.label', default: 'Id')}"/>

                <g:sortableColumn property="title" title="${message(code: 'trait.title.label', default: 'Title')}"/>

                <g:sortableColumn property="description"
                                  title="${message(code: 'trait.description.label', default: 'Description')}"/>

                <th><g:message code="trait.profile.label" default="Profile"/></th>

            </tr>
            </thead>
            <tbody>
            <g:each in="${traitInstanceList}" status="i" var="traitInstance">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                    <td><g:link action="show"
                                id="${traitInstance.id}">${fieldValue(bean: traitInstance, field: "id")}</g:link></td>

                    <td>${fieldValue(bean: traitInstance, field: "title")}</td>

                    <td>${fieldValue(bean: traitInstance, field: "description")}</td>

                    <td>${fieldValue(bean: traitInstance, field: "profile")}</td>

                </tr>
            </g:each>
            </tbody>
        </table>
    </div>

    <div class="paginateButtons">
        <g:paginate total="${traitInstanceTotal}"/>
    </div>
</div>
</body>
</html>
