<#import "parts/pattern.ftlh" as c>
<@c.page>
    <div class="title">
        <h1>
            UserEditor1.0
        </h1>
    </div>
    <style type="text/css">
        .title{

            text-align: center;
            vertical-align: middle;
        }
    </style>
    <form action="/user" method="post">
        <div>
            <input title="Enter username" type="text" value="${user.username}" name="username">
            <#list roles as role>
                <div>
                    <input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("Checked","")}>${role}
                </div>
            </#list>
            <input title="User" type="hidden" name="user_id" value="${user.id}">
            <input type="hidden"
                   name="_csrf"
                   value="_csrf.token"/>

        </div>
        <div>
            <button type="submit" >Submit</button>
        </div>
    </form>
</@c.page>