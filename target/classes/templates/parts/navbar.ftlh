<#import "login_pattern.ftlh" as l>
<#include "security.ftlh">

<#assign
    notAuth=Session.SPRING_SECURITY_CONTEXT??
>

<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="/">SpringTest</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="/main">Main</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/user">List of users(Admin only)</a>
            </li>
        </ul>
        <div class="navbar-text mr-3">${name}</div>
        <#if notAuth>
            <div class="pull-down"><@l.logout /></div>
        </#if>
    </div>
</nav>
