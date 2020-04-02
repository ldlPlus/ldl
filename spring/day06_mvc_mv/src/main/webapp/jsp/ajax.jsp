<%--
  Created by IntelliJ IDEA.
  User: ldl
  Date: 2020/3/15
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="application/javascript" src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
    <script>

        function sendAjax() {
            var userList = [{username: "zhagnsan", age: 18}, {username: "lisi", age: 20}];
            $.ajax({
                url: "${pageContext.request.contextPath}/user/test8",
                type: "POST",
                data: JSON.stringify(userList),
                contentType: "application/json;charset=utf-8"
            })
        }

        $(function () {
            var users = [
                {
                    "userList[0].username": "zhagnsan",
                    "userList[0].age": 18
                }, {
                    "userList[1].username": "lisi",
                    "userList[1].age": 20
                }];
            $.post("${pageContext.request.contextPath}/user/test9", JSON.stringify(users), function (data) {
                console.log(data)
            }, "json")
        })

    </script>
</head>
<body>

<h3>ajax请求</h3>
<a href="javascript:sendAjax();">发送异步请求</a>
</body>
</html>
