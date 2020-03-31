<#assign
    known=Session.SPRING_SECURITY_CONTEXT??
>
<#if known>
    <#assign
        aUser=Session.SPRING_SECURITY_CONTEXT.authentication.principal
        name=aUser.getUsername()
        isAdministrator=aUser.getAuthorities()?seq_contains('ADMIN')
    >
<#else>
    <#assign
        name="unknown"
        isAdministrator=false
    >
</#if>