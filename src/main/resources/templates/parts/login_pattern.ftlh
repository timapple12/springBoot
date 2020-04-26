<#macro login path isRegisterForm>
    <form action="${path}" method="post">
        <#if isRegisterForm>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">An e-mail:</label>
                <div class="col-sm-5">
                    <input type="email" name="email" class="form-control ${(emailError??)?string('is-invalid',"")}"
                           placeholder="E-mail" value=""/>
                    <#if emailError??>
                        <div class="invalid-feedback">
                            ${emailError}
                        </div>
                    </#if>
                </div>
            </div>
        </#if>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">User Name :</label>
            <div class="col-sm-5">
                <input type="text" name="username" class="form-control ${(usernameError??)?string('is-invalid',"")}"
                       placeholder="User name" value=""/>
                <#if usernameError??>
                    <div class="invalid-feedback">
                        ${usernameError}
                    </div>
                </#if>
            </div>

        </div>
        <div class="form-group row">
            <label class="col-sm-2 col-form-label">Password:</label>
            <div class="col-sm-5">
                <input type="password" name="password"
                       class="form-control ${(passwordError??)?string('is-invalid',"")}" placeholder="Password"
                       value=""/>
                <#if passwordError??>
                    <div class="invalid-feedback">
                        ${passwordError}
                    </div>
                </#if>
            </div>
        </div>
        <#if isRegisterForm>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Re-enter the password:</label>
                <div class="col-sm-5">
                    <input type="password" name="secondPassword"
                           class="form-control ${(passwordError??)?string('is-invalid',"")}"
                           placeholder="Repeat password" value=""/>
                    <#if passwordError??>
                        <div class="invalid-feedback">
                            ${passwordError}
                        </div>
                    </#if>
                </div>
            </div>
        </#if>

        <div>
            <div class="g-recaptcha" data-sitekey="6Lfdje4UAAAAAGdzFIml-wqI_NhWzkKoBgZat0B7"></div>
            <#if captchaError??>
                <div class="alert alert-danger" role="alert">
                    ${captchaError}
                </div>

            </#if>
        </div>
        <input type="hidden"
               name="_csrf"
               value="_csrf.token"/>

        <div class="button">
            <button class="btn btn-primary" type="submit"
                    style="width:150px;height:45px"><#if isRegisterForm>Create<#else>Sign In</#if></button>
        </div>
    </form>
    <#if !isRegisterForm>
        <div class="button">
            <button class="btn btn-primary" onclick="document.location='registration'" style="width:150px;height:45px">
                Sign up
            </button>
        </div></#if>
    <#if isRegisterForm>
        <div class="button">
            <button class="btn btn-primary" onclick="document.location='login'" style="width:150px;height:45px">Return
                to login
            </button>
        </div></#if>
</#macro>

<#macro logout >
    <form action="/logout" method="post">
        <input type="hidden"
               name="_csrf"
               value="_csrf.token"/>
        <button class="btn btn-outline-danger" type="submit">Sign Out</button>
    </form>
</#macro>