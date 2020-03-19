<#import "parts/pattern.ftlh" as c>
<@c.page>
    Users:
    <table>
        <thead>
        <tr>
            <th>User</th>
            <th>Role</th>
        </tr>
        </thead>

        <tbody>
        <#list user as usr>
            <tr>
                <td>${usr.username}</td>
                <td>
                    <#list usr.roles as roll>
                        ${roll}<#sep> ,</#list>
                </td>
                <td><a href="/user/${usr.id}">Edit</a></td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>