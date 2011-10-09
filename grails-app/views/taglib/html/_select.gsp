<div class="form-element clearfix">
    %{--<label gebid="${gebid}-label" for="${name}">${label}</label>--}%
    <label for="${name}">${label}</label>
    <div class="form-input input">
        %{--TODO: MAKE THIS INTELLIGENT WITH HTMl5.whateverAttributes()--}%
        <g:if test="${multiple}">
            %{--<g:select gebid="${gebid}" id="${name}" name="${name}" from="${from}" value="${selected}" optionKey="${optionKey}" optionValue="${optionValue}" noSelection="${noSelection}" multiple="${multiple}"/>--}%
            <g:select id="${name}" name="${name}" from="${from}" value="${selected}" optionKey="${optionKey}" optionValue="${optionValue}" noSelection="${noSelection}" multiple="${multiple}"/>
        </g:if>
        <g:else>
            %{--<g:select gebid="${gebid}" id="${name}" name="${name}" from="${from}" value="${selected}" optionKey="${optionKey}" valueMessagePrefix="${valueMessagePrefix}" optionValue="${optionValue}" noSelection="${noSelection}"/>--}%
            <g:select id="${name}" name="${name}" from="${from}" value="${selected}" optionKey="${optionKey}" valueMessagePrefix="${valueMessagePrefix}" optionValue="${optionValue}" noSelection="${noSelection}"/>
        </g:else>
    </div>
</div>
