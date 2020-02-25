<#import "parts/pattern.ftlh" as c>
<@c.page>
<csrf disabled="true"/>
    <div>
        <form action="/logout" method="post">
            <input type="submit" value="Sign out"/>
        </form>
    </div>
    <div>
        <form method="post">
            <input type ="message"name="message" placeholder="Enter a message"/>
            <input type ="message"name="tag" placeholder="Tag"/>
            <button type="submit">Add</button>
        </form>
    </div>
    <div>List of massages</div>
    <form method="post" action="filter">

        <input type="text"name="filter">
        <button type="submit">Find</button>
    </form>
<#list message as m>
    <div>
            <b>${m.id}</b>
            <b>${m.message}</b>
             <b>${m.tag}</b>
    </div>
    <#else>
    No message
</#list>
</@c.page>