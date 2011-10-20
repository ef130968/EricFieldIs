

<%@ page import="ericfieldis.entity" %>
<%@ page import="ericfieldis.profile.Profile" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'person.label', default: 'Person')}" />
        <title><g:message code="default.edit.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
            <span class="menuButton"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.edit.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${personInstance}">
            <div class="errors">
                <g:renderErrors bean="${personInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form method="post" >
                <g:hiddenField name="id" value="${personInstance?.id}" />
                <g:hiddenField name="version" value="${personInstance?.version}" />
                <div class="dialog">
                    <table>
                        <tbody>
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="firstName"><g:message code="person.firstName.label" default="First Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: personInstance, field: 'firstName', 'errors')}">
                                    <g:textField name="firstName" maxlength="20" value="${personInstance?.firstName}" />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="middleInitial"><g:message code="person.middleInitial.label" default="Middle Initial" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: personInstance, field: 'middleInitial', 'errors')}">
                                    <g:textField name="middleInitial" maxlength="1" value="${personInstance?.middleInitial}" />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label for="lastName"><g:message code="person.lastName.label" default="Last Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: personInstance, field: 'lastName', 'errors')}">
                                    <g:textField name="lastName" maxlength="20" value="${personInstance?.lastName}" />
                                </td>
                            </tr>
                            <tr class="prop">
                                <td valign="top" class="name">
                                  <label><g:message code="person.profiles.label" default="Profiles" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: personInstance, field: 'profiles', 'errors')}">
                                    <ul><g:each in="${personInstance?.profiles?}" var="p">
                                        <li><g:link controller="profile" action="show" id="${p.id}">${p?.name?.encodeAsHTML()}</g:link></li>
                                    </g:each></ul>

                                    <g:select name="profileId" id="profileId" from="${ericfieldis.profile.Profile.list()}"
                                              optionKey="id" optionValue="${ {it?.name} }"
                                              noSelection="${['null]':'']}"
                                              value="${it?.id}"/>
                                    <g:link controller="person" action="addProfile" params="['profile.id': 1]">Add</g:link>

                                    $("form").find("select", name: "profileId").value()

                                    %{--<g:link controller="profile" action="create" params="['person.id': personInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'profile.label', default: 'Profile')])}</g:link>--}%
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" /></span>
                    <span class="button"><g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
