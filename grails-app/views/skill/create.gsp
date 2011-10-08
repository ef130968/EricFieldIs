

<%@ page import="ericfieldis.Skill" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'skill.label', default: 'Skill')}" />
        <title><g:message code="default.create.label" args="[entityName]" /></title>
    </head>
    <body>
        <div class="nav">
            <span class="menuButton"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></span>
            <span class="menuButton"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></span>
        </div>
        <div class="body">
            <h1><g:message code="default.create.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
            <div class="message">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${skillInstance}">
            <div class="errors">
                <g:renderErrors bean="${skillInstance}" as="list" />
            </div>
            </g:hasErrors>
            <g:form action="save" >
                <div class="dialog">
                    <table>
                        <tbody>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="name"><g:message code="skill.name.label" default="Name" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: skillInstance, field: 'name', 'errors')}">
                                    <g:textField name="name" maxlength="100" value="${skillInstance?.name}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="description"><g:message code="skill.description.label" default="Description" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: skillInstance, field: 'description', 'errors')}">
                                    <g:textField name="description" value="${skillInstance?.description}" />
                                </td>
                            </tr>
                        
                            <tr class="prop">
                                <td valign="top" class="name">
                                    <label for="talent"><g:message code="skill.talent.label" default="Talent" /></label>
                                </td>
                                <td valign="top" class="value ${hasErrors(bean: skillInstance, field: 'talent', 'errors')}">
                                    <g:select name="talent.id" from="${ericfieldis.Talent.list()}" optionKey="id" value="${skillInstance?.talent?.id}"  />
                                </td>
                            </tr>
                        
                        </tbody>
                    </table>
                </div>
                <div class="buttons">
                    <span class="button"><g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" /></span>
                </div>
            </g:form>
        </div>
    </body>
</html>
