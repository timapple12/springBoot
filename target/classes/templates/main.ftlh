<#import "parts/pattern.ftlh" as c>
<@c.page>

    <div> <a href="/user">Users</a></div>
    <a class="btn btn-primary"data-toggle="collapse" href="#collapse"role="button"aria-expanded="false">
        Enter a new message
    </a>
    <div class="collapse <#if message??>show</#if>" id="collapse">
        <div class="form-group my-3">
            <form method="post" enctype="multipart/form-data">
                <div class="form-group">
                    <input type="message" class="form-control ${(messageError??)?string('is-invalid',"")}"
                           value="<#if message??>${message.message}</#if>" name="text" placeholder="Enter a message"/>
                    <#if messageError??>
                    <div class="invalid-feedback">
                        ${messageError}
                    </div>
                    </#if>
                </div>

                <div class="form-group">
                    <input type="message" class="form-control" name="tag" placeholder="Tag"/>
                </div>
                <div class="form-group">
                    <div class="custom-file">
                        <input type="file" name="file" id="customFile" class="form-control">
                        <label class="custom-file-label"for="customFile">Choose file</label>
                    </div>
                </div>
                <input type="hidden"
                       name="_csrf"
                       value="_csrf.token"/>
                <div class="form-group">
                    <button class="btn btn-primary" type="submit">Send message</button>
                </div>
            </form>
        </div>
    </div>
    <div>List of massages</div>
    <form method="post" action="filter">
        <input type="text" name="filter" value="${filter!""}"/>
        <button type="submit">Find</button>
    </form>

    <#list messages as m>
        <div class="card my-3" style="width: 15rem;">
            <#if m.filename??>
                <img class="card-img-top" src="img\${m.filename}">
            </#if>
            <b>${m.message}</b>
            <b>${m.tag}</b>
            <div class="card-footer text-muted"><strong>${m.authorName}</strong></div>
        </div>

    <#else>
        No message
    </#list>

</@c.page>