<%@ page import="ericfieldis.entity.Entity" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'entity.label', default: 'Entity')}"/>
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

                <g:sortableColumn property="id" title="${message(code: 'entity.id.label', default: 'Id')}"/>

                <th><g:message code="entity.citizen.label" default="Citizen"/></th>

                <th><g:message code="entity.business.label" default="Business"/></th>

            </tr>
            </thead>
            <tbody>
            <g:each in="${entityInstanceList}" status="i" var="entityInstance">
                <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">

                    <td><g:link action="show"
                                id="${entityInstance.id}">${fieldValue(bean: entityInstance, field: "id")}</g:link></td>

                    <td>${fieldValue(bean: entityInstance, field: "citizen")}</td>

                    <td>${fieldValue(bean: entityInstance, field: "business")}</td>

                </tr>
            </g:each>
            </tbody>
        </table>
    </div>

    <div class="paginateButtons">
        <g:paginate total="${entityInstanceTotal}"/>
    </div>
</div>
</body>
</html>
