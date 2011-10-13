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
</body>
</html>
