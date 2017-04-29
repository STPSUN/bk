<%--
  Created by IntelliJ IDEA.
  User: gao
  Date: 2017/4/25
  Time: 18:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8" />
  <title></title>
  <script src="jquery-1.8.3.min.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
<div class="login">
  <p>用户名：<input type="text" name="" id="userName"></p>
  <p>密码：<input type="password" id="password"></p>
  <input type="submit" value="提交" id="submit">
</div>
<script>
    $("#submit").click(function (){
        console.log('hasClick');
        $.ajax({
            type:"post",
            url:"users?action=login",
            dataType:"json",
            data:{
                "userName":$("#userName").val(),
                "password":$("#password").val()
            },
            success:function(msgString){
                alert("into fun" + msgString.msg)
                if(msgString.code == '200'){
                    console.log(msgString.data.userName+','+msgString.data.password);
                }
                else {
                    console.log(msgString.msg);
                }
            },
            error:function (msgString) {
                console.log('error:'+msgString.msg);
            }
        });
    })

</script>
</body>
</html>
