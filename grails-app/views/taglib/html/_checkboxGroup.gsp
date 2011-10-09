<div class="form-element clearfix">
    %{--<label gebid="${gebid}-label" for="${groupName}">${label}</label>--}%

    <div class="form-input input">
        <g:each in="${from}" var="item">
            <div>
                <input type="checkbox" id="${groupName + '_' + item[key]}" name="${groupName}" value="${item[key]}" <g:if test="${selected.find { it == item[key] }}">checked="checked"</g:if>/> ${item[value].encodeAsHTML()}
            </div>
        </g:each>
    </div>
</div>
