<%@ page import="ericfieldis.profile.Profile" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'profile.label', default: 'Profile')}"/>
    <title><g:message code="default.edit.label" args="[entityName]"/></title>
</head>

<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
    <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]"/></g:link></span>
    <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]"/></g:link></span>
</div>

<div class="body">
    <h1><g:message code="default.edit.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${profileInstance}">
        <div class="errors">
            <g:renderErrors bean="${profileInstance}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form method="post">
        <g:hiddenField name="id" value="${profileInstance?.id}"/>
        <g:hiddenField name="version" value="${profileInstance?.version}"/>
        <div class="dialog">
            <table>
                <tbody>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="name"><g:message code="profile.name.label" default="Name"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: profileInstance, field: 'name', 'errors')}">
                        <g:textField name="name" maxlength="100" value="${profileInstance?.name}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="description"><g:message code="profile.description.label" default="Description"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: profileInstance, field: 'description', 'errors')}">
                        <g:textField name="description" value="${profileInstance?.description}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="profileType"><g:message code="profile.profileType.label" default="Profile Type"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: profileInstance, field: 'profileType', 'errors')}">
                        <g:select name="profileType" from="${me.ericfieldis.datatypes.enums.ProfileType?.values()}"
                                  keys="${me.ericfieldis.datatypes.enums.ProfileType?.values()*.name()}"
                                  optionValue="${ {it?.profileTypeName()} }"
                                  value="${profileInstance?.profileType?.name()}"/>
                    </td>
                </tr>
                <tr class="prop">
                    <td valign="top" class="name">
                        <label><g:message code="profile.person.label" default="Person"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: profileInstance, field: 'person', 'errors')}">
                        <g:select name="person.id" from="${ericfieldis.Person.list()}"
                                  optionKey="id" optionValue="${ {it?.personName()} }"
                                  value="${profileInstance?.person?.id}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label><g:message code="profile.skins.label" default="Skins"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: profileInstance, field: 'skins', 'errors')}">
                        <ul>
                            <g:each in="${profileInstance?.skins?}" var="s">
                                <li><g:link controller="skin" action="show"
                                            id="${s.id}">${s?.encodeAsHTML()}</g:link></li>
                            </g:each>
                        </ul>
                        <g:link controller="skin" action="create"
                                params="['profile.id': profileInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'skin.label', default: 'Skin')])}</g:link>
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
