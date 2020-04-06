<#import "parts/pattern.ftlh" as c>
<#import "parts/login_pattern.ftlh" as l>

<@c.page>
    <div CLASS="mb-4"><h1>WELCOME!</h1></div>
    <span style="color: #ff3d35; ">${user?if_exists}</span>
    <#if Session?? && Session.SPRING_SECURITY_LAST_EXCEPTION??>
        <div class="alert alert-danger" role="alert">
            ${SPRING_SECURITY_LAST_EXCEPTION.message}
        </div>
    </#if>
    <@l.login "/login" false/>
</@c.page>