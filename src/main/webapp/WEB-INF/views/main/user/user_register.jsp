<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">
            注册
        </h3>
    </div>
    <div class="panel-body">
        <form action="${pageContext.request.contextPath}/registerUser"
              method="post" class="form-horizontal">
            <div class="form-group">
                <label class="control-label col-sm-2"
                       for="userName">用户名：</label>
                <div class="col-sm-10">
                    <input class="form-control" type="text" name="userName"
                           id="userName"/>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="userPass">密码：</label>
                <div class="col-sm-10">
                    <input class="form-control" type="password" name="userPass"
                           id="userPass"/>
                </div>
            </div>
            <div class="col-sm-6 col-sm-offset-2">
                <button class="btn btn-primary" type="submit">注册</button>
                <button class="btn btn-default" type="reset">重置</button>
            </div>
        </form>
    </div>
</div>
