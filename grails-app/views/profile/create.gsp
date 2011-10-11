<%@ page import="ericfieldis.person.user.profile.Profile" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="layout" content="main"/>
    <g:set var="entityName" value="${message(code: 'profile.label', default: 'Profile')}"/>
    <title><g:message code="default.create.label" args="[entityName]"/></title>
</head>

<body>
<div class="nav">
    <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
    <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]"/></g:link></span>
</div>
<div class="body">
    <g:hasErrors bean="${cmd}">
        <div class="errors">
            <g:renderErrors bean="${cmd}" as="list"/>
        </div>
    </g:hasErrors>
    <html:form name="createForm" action="save">
        <html:text bean="${cmd}" name="name" label="profile.create.label.name"/>
        <html:text bean="${cmd}" name="description" label="profile.create.label.description"/>
        <html:select from="${persons}" optionKey="id" optionValue="${ {it.personName()} }" bean="${cmd}" name="personSelection" label="profile.create.label.person" noSelection="profile.create.label.person.noSelection"/>
        <html:select from="${profileTypes}" selected="${cmd.profileTypeSelection}" optionKey="id" optionValue="${ {it.profileTypeName()} }" bean="${cmd}" name="profileTypeSelection" label="profile.create.label.profileType" noSelection="profile.create.label.profileType.noSelection"/>
        <div>
            <g:submitButton name="createFormButton" value="${my.message(code: 'default.button.create.label')}"/>
        </div>
    </html:form>
</div>
%{--
<div class="body">
    <h1><g:message code="default.create.label" args="[entityName]"/></h1>
    <g:if test="${flash.message}">
        <div class="message">${flash.message}</div>
    </g:if>
    <g:hasErrors bean="${cmd}">
        <div class="errors">
            <g:renderErrors bean="${cmd}" as="list"/>
        </div>
    </g:hasErrors>
    <g:form action="save">
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
                        <label for="description"><g:message code="profile.description.label"
                                                            default="Description"/></label>
                    </td>
                    <td valign="top" class="value ${hasErrors(bean: profileInstance, field: 'description', 'errors')}">
                        <g:textField name="description" value="${profileInstance?.description}"/>
                    </td>
                </tr>

                <tr class="prop">
                    <td valign="top" class="name">
                        <label for="profileType"><g:message code="profile.profileType.label"
                                                            default="Profile Type"/></label>
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

                </tbody>
            </table>
        </div>

        <div class="buttons">
            <span class="button"><g:submitButton name="create" class="save"
                                                 value="${message(code: 'default.button.create.label', default: 'Create')}"/></span>
        </div>
    </g:form>
</div>
--}%
</body>
</html>
