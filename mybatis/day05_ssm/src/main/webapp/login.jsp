<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登陆</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/user/login" method="post">
    <table cellpadding="3" cellspacing="3" style="border: 1px solid yellow;font-size: 30px" border="1px" align="center">

        <tr>
            <td>用户名:</td>
            <td><input type="text" name="username"/></td>
        </tr>
        <tr>
            <td>密码:</td>
            <td><input type="password" name="password"></td>
        </tr>
        <tr>
            <td colspan="2" align="center"><input type="submit" value="登陆"></td>
        </tr>
        <tr>
            <td colspan="2" align="center"> ${msg}</td>
        </tr>
    </table>
</form>


</body>
</html>
