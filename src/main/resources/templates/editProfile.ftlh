<#import "parts/pattern.ftlh" as c>

<@c.page>
    <span style="color: #ff3d35;" > ${user?if_exists}</span>
    <span style="color: #ff3d35;" >${message?if_exists}</span>
<form method="post">
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Change the e-mail:</label>
        <div class="col-sm-5">
            <input type="email" name="email" class="form-control" placeholder="E-mail" value=${email?if_exists}>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Change username :</label>
        <div class="col-sm-5">
            <input type="text" name="username" class="form-control" placeholder="User name" value=${username?if_exists}>
        </div>
    </div>
    <div class="form-group row">
        <label class="col-sm-2 col-form-label">Confirm the password:</label>
        <div class="col-sm-5">
            <input type="text" name="password" class="form-control" placeholder="password" value="">
        </div>
    </div>
    <div class="button"><button class="btn btn-primary" type="submit"style="width:150px;height:45px">Submit</button></div>
</form>
</@c.page>