<script>
    $(document).ready(function () {
        var isEmailCodeError = $("#emailCodeError").val();
        if (isEmailCodeError == "true") {
            var codeError = document.getElementById("userEmailCodeError");
            codeError.innerHTML = "请输入正确的验证码";
        }
    });

    function sendEmailCode() {
        var email = $("#userEmail").val();
        $.ajax({
            url: "${pageContext.request.contextPath}/user/sendEmailCode",
            type: "POST",
            dataType: "json",
            data: {
                userEmail: email
            },
            success: function (data) {
                alert(data.message);
            },
            error: function () {
                alert("发生未知错误!");
            }
        });
    }
</script>
<div class="panel panel-default">
    <div class="panel-heading">
        <h3 class="panel-title">
            个人信息
        </h3>
    </div>
    <div class="panel-body">

        <form:form class="form-inline" role="form"
                   modelAttribute="userControllerFrom" method="post"
                   action="${pageContext.request.contextPath}/user/updateUserInfo">
            <div class="form-group">
                <form:label path="userName" for="userName">用户名：</form:label>

                    <form:input path="userName" readonly="true"
                                class="form-control"/>
                    <form:errors path="userName"
                                 cssStyle="background-color: red"/>

            </div>
            <div class="form-group">
                <form:label path="userEmail" for="userEmail">邮箱：</form:label>

                    <form:input path="userEmail" class="form-control" placeholder="请设置您的邮箱!"/>
                    <form:errors path="userEmail"
                                 cssStyle="background-color: red"/>
                    <button onclick="sendEmailCode();return false;" class="btn"
                            style="float:right">发送验证码
                    </button>

            </div>
            <div class="form-group" id="email_code_group">
                <form:label path="userEmailCode" for="userEmailCode">验证码：</form:label>

                    <form:input path="userEmailCode" class="form-control" placeholder="请输入您收到的验证码!" maxlength="6"/>
                    <form:errors path="userEmailCode"
                                 cssStyle="background-color: red"/>
                    <label id="userEmailCodeError"
                           style="background-color: red"></label>

            </div>
            <button class="btn btn-primary" style="float:right">更新个人信息</button>
            <form:hidden path="emailCodeError"/>
        </form:form>

    </div>
</div>
