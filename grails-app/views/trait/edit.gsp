<%@ page import="ericfieldis.profile.components.Trait" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'trait.label', default: 'Trait')}"/>
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
    <g:hasErrors bean="${traitInstance}">
        <div class="errors">
            <g:renderErrors bean="${traitInstance}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form method="post">
        <g:hiddenField name="id" value="${traitInstance?.id}"/>
        <g:hiddenField name="version" value="${traitInstance?.version}"/>
        <div class="dialog">
            <table>
                <tbody>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="title"><g:message code="trait.title.label" default="Title"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: traitInstance, field: 'title', 'errors')}">
                        <g:textField name="title" maxlength="100" value="${traitInstance?.title}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="description"><g:message code="trait.description.label"
                                                            default="Description"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: traitInstance, field: 'description', 'errors')}">
                        <g:textField name="description" value="${traitInstance?.description}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="profile"><g:message code="trait.profile.label" default="Profile"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: traitInstance, field: 'profile', 'errors')}">
                        <g:select name="profile.id" from="${ericfieldis.profile.Profile.list()}" optionKey="id"
                                  value="${traitInstance?.profile?.id}"/>
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
