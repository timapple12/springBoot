<#import "parts/pattern.ftlh" as c>
<@c.page>
<csrf disabled="true"/>
        <form method="post">
            <input type="hidden"
                   name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
            <input type ="message"name="message" placeholder="Enter a message"/>
            <input type ="message"name="tag" placeholder="Tag"/>
            <button type="submit">Add</button>
        </form>
    </div>
    <div>List of massages</div>
    <form method="post" action="filter">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <input type="text"name="filter">
        <button type="submit">Find</button>
    </form>
    {{#message}}
        <div>
            <b>{{id}}</b>
             <span>{{message}}</span>
            <i>{{tag}}</i>
        </div>
    {{/message}}
</@c.page>