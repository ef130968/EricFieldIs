<div class="form-element clearfix">
    <g:if test="${label}">
        <label gebid="${gebid}-label" for="${name}" <g:if test="${labelClass}">class="${labelClass}"</g:if>>${label}</label>
    </g:if>
    <div gebid="${gebid}" class="form-input input">
        <g:if test="${i18nValuePrefix}"><inca:message code="$i18nValuePrefix${value}"/></g:if><g:else>${value}</g:else>
    </div>
</div>
