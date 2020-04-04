<#macro login path isRegisterForm>
    <form action="${path}" method="post">
        <#if isRegisterForm>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">An e-mail:</label>
                <div class="col-sm-5">
                    <input type="email" name="email" class="form-control" placeholder="E-mail" value=""/>
                </div>
            </div>
        </#if>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">User Name :</label>
            <div class="col-sm-5">
                <input type="text" name="username" class="form-control" placeholder="User name" value=""/>
            </div>
        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Password:</label>
            <div class="col-sm-5">
                <input type="password" name="password" class="form-control" placeholder="Password" value=""/>
            </div>
        </div>
        <input type="hidden"
               name="_csrf"
               value="_csrf.token"/>

        <div class="button"><button class="btn btn-primary" type="submit"style="width:150px;height:45px"><#if isRegisterForm>Create<#else>Sign In</#if></button></div>
    </form>
    <#if !isRegisterForm><div class="button"><button class="btn btn-primary" onclick="document.location='registration'" style="width:150px;height:45px" >Sign up</button></div></#if>
    <#if isRegisterForm><div class="button"><button class="btn btn-primary" onclick="document.location='login'" style="width:150px;height:45px">Return to login</button></div></#if>
</#macro>

<#macro logout >
    <form action="/logout" method="post">
        <input type="hidden"
               name="_csrf"
               value="_csrf.token"/>
        <button class="btn btn-outline-danger" type="submit">Sign Out</button>
    </form>
</#macro>