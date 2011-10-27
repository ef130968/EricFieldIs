<%@ page import="ericfieldis.entity.Entity" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'entity.label', default: 'Entity')}"/>
    <title><g:message code="default.edit.label" args="[entityName]"/></title>
</head>

<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
    </span>
    <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label"
                                                                           args="[entityName]"/></g:link></span>
    <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label"
                                                                               args="[entityName]"/></g:link></span>
</div>

<div class="body">
    <h1><g:message code="default.edit.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${entityInstance}">
        <div class="errors">
            <g:renderErrors bean="${entityInstance}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form method="post">
        <g:hiddenField name="id" value="${entityInstance?.id}"/>
        <g:hiddenField name="version" value="${entityInstance?.version}"/>
        <div class="dialog">
            <table>
                <tbody>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="citizen"><g:message code="entity.citizen.label" default="Citizen"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: entityInstance, field: 'citizen', 'errors')}">
                        <g:select name="citizen.id" from="${ericfieldis.entity.citizen.Citizen.list()}" optionKey="id"
                                  value="${entityInstance?.citizen?.id}" noSelection="['null': '']"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="business"><g:message code="entity.business.label" default="Business"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: entityInstance, field: 'business', 'errors')}">
                        <g:select name="business.id" from="${ericfieldis.entity.business.Business.list()}"
                                  optionKey="id" value="${entityInstance?.business?.id}" noSelection="['null': '']"/>
                    </td>
                </tr>

                </tbody>
            </table>
        </div>

        <div class="buttons">
            <span class="button"><g:actionSubmit class="save" action="update"
                                                 value="${message(code: 'default.button.update.label', default: 'Update')}"/></span>
            <span class="button"><g:actionSubmit class="delete" action="delete"
                                                 value="${message(code: 'default.button.delete.label', default: 'Delete')}"
                                                 onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"/></span>
        </div>
    </g:form>
</div>
</body>
</html>
