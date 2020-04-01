<#import "parts/pattern.ftlh" as c>
<#import "parts/login_pattern.ftlh" as l>

<@c.page>
    <div class="mb-4"><h1>Add new user</h1></div>
    <span style="color: #ff3d35; ">${user?if_exists}</span>
    <@l.login "/registration" true />


    </style>
</@c.page>