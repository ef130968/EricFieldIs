<%@ page import="ericfieldis.profile.components.Value" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'value.label', default: 'Value')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
    </span>
    <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label"
                                                                           args="[entityName]"/></g:link></span>
</div>

<div class="body">
    <h1><g:message code="default.create.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${valueInstance}">
        <div class="errors">
            <g:renderErrors bean="${valueInstance}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form action="save">
        <div class="dialog">
            <table>
                <tbody>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="title"><g:message code="value.title.label" default="Title"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: valueInstance, field: 'title', 'errors')}">
                        <g:textField name="title" maxlength="100" value="${valueInstance?.title}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="description"><g:message code="value.description.label"
                                                            default="Description"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: valueInstance, field: 'description', 'errors')}">
                        <g:textField name="description" value="${valueInstance?.description}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="profile"><g:message code="value.profile.label" default="Profile"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: valueInstance, field: 'profile', 'errors')}">
                        <g:select name="profile.id" from="${ericfieldis.profile.Profile.list()}" optionKey="id"
                                  value="${valueInstance?.profile?.id}"/>
                    </td>
                </tr>

                </tbody>
            </table>
        </div>

        <div class="buttons">
            <span class="button"><g:submitButton name="create" class="save"
                                                 value="${message(code: 'default.button.create.label', default: 'Create')}"/></span>
        </div>
    </g:form>
</div>
</body>
</html>
