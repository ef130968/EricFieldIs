<div class="form-element clearfix">

    <label gebid="${gebid}-label" for="${groupName}">${label}</label>

    <div class="form-input input" id="${groupName}">
        <g:each in="${from}" var="item">
            <div>
                <g:radio value="${item[radioKey]}" checked="${selected == item[radioKey] ? true : false}" id="${groupName}_${item[radioKey]}" name="${groupName}"/><g:if test="${i18nValuePrefix}"><inca:message code="$i18nValuePrefix${item[radioValue]}"/></g:if><g:else>${item[radioValue].encodeAsHTML()}</g:else>
            </div>
        </g:each>
    </div>
</div>
