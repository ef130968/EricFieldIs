<%@ page import="me.ericfieldis.framework.plugin.taglib.HTML5" %>

<div class="form-element clearfix">
    %{--<label gebid="${attrs.gebid}-label" for="${attrs.name}">${label}</label>--}%
    <label for="${attrs.name}">${label}</label>
    <div class="form-input input">
        <input ${me.ericfieldis.framework.plugin.taglib.HTML5.inputAttributes(attrs)}/>
    </div>
</div>

