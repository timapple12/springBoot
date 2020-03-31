<#import "parts/pattern.ftlh" as c>
<#import "parts/login_pattern.ftlh" as l>

<@c.page>
    <div class="mb-1">Add new user</div>
    ${name?ifExists}
    <@l.login "/registration" true />
</@c.page>