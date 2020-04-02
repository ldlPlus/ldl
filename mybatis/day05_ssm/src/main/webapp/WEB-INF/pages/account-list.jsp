<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ldl
  Date: 2020/3/24
  Time: 12:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>account-list</title>
</head>
<body>
<table cellpadding="3" cellspacing="3" style="border: 1px solid yellow" border="1px" align="center">
    <tr align="center">
        <td width="50px">ID</td>
        <td width="150px">姓名</td>
        <td width="150px">金额</td>
    </tr>
    <c:forEach items="${accounts}" var="account">
        <tr align="center">
            <td>${account.id}</td>
            <td>${account.name}</td>
            <td>${account.balance}</td>
        </tr>
    </c:forEach>
    <tr align="center">
        <td colspan="3" style="text-align: center"><input type="button" value="添加"
                                                          onclick="location.href='${pageContext.request.contextPath}/index.jsp'">
        </td>
    </tr>
</table>
</body>
</html>
