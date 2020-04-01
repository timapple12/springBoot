<#import "parts/pattern.ftlh" as c>
<#import "parts/login_pattern.ftlh" as l>

<@c.page>
    <div CLASS="mb-4"><h1>WELCOME!</h1></div>
    <@l.login "/login" false/>
</@c.page>