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
            <input title="Enter username" type="text" value="${user.username}">
            <#list rolles as roll>
                <div>
                    <input type="checkbox" name="${roll}">${roll}
                </div>
            </#list>
            <input type="hidden"
                   name="_csrf"
                   value="_csrf.token"/>
            <input title="User" type="text" value="${user.id}">

        </div>
        <div>
            <button type="submit"name="Submit" style="align-content: center"></button>
        </div>
    </form>
</@c.page>