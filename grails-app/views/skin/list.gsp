
<%@ page import="ericfieldis.Skin" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'skin.label', default: 'Skin')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <div class="list">
                <table>
                    <thead>
                        <tr>
                        
                            <g:sortableColumn property="id" title="${message(code: 'skin.id.label', default: 'Id')}" />
                        
                            <g:sortableColumn property="name" title="${message(code: 'skin.name.label', default: 'Name')}" />
                        
                            <th><g:message code="skin.profile.label" default="Profile" /></th>
                        
                        </tr>
                    </thead>
                    <tbody>
                    <g:each in="${skinInstanceList}" status="i" var="skinInstance">
                        <tr class="${(i % 2) == 0 ? 'odd' : 'even'}">
                        
                            <td><g:link action="show" id="${skinInstance.id}">${fieldValue(bean: skinInstance, field: "id")}</g:link></td>
                        
                            <td>${fieldValue(bean: skinInstance, field: "name")}</td>
                        
                            <td>${fieldValue(bean: skinInstance, field: "profile")}</td>
                        
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div class="paginateButtons">
                <g:paginate total="${skinInstanceTotal}" />
            </div>
        </div>
    </body>
</html>
