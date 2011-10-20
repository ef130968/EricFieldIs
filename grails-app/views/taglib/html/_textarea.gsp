<%@ page import="me.ericfieldis.framework.plugin.taglib.HTML5" %>
<div class="form-element clearfix">
    <label gebid="${attrs.gebid}-label" for="${attrs.name}">${label}</label>

    <div gebid="${attrs.gebid}" class="form-input input">
        <textarea ${HTML5.textAreaAttributes(attrs)}>${value}</textarea>
    </div>
</div>
