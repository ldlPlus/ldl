<%--
  Created by IntelliJ IDEA.
  User: ldl
  Date: 2020/3/24
  Time: 12:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>save</title>
</head>
<body>
<form method="post" action="${pageContext.request.contextPath}/account/save">
    <table cellpadding="3" cellspacing="3" style="border: 1px solid yellow;font-size: 30px" border="1px" align="center">
        <tr>
            <td>姓名：</td>
            <td><input name="name"></td>
        </tr>
        <tr>
            <td>金额：</td>
            <td><input name="balance"></td>
        </tr>
        <tr>
            <td colspan="2" align="center"><input type="submit" value="保存"></td>
        </tr>
        <tr>
            <td colspan="2" align="center"> ${msg}</td>
        </tr>
    </table>

</form>
</body>
</html>
