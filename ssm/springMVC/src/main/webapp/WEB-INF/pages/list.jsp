<%--
  Created by Dai
  Date: 2021/8/22 11:32
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<table border="1" cellpadding="8" cellspacing="0">
    <tr align="center">
        <th>ID</th>
        <th>lastName</th>
        <th>email</th>
        <th>gender</th>
        <th> departmentName</th>
        <th>EDIT</th>
        <th>DELETE</th>
    </tr>
    <c:forEach items="${emps}" var="emp">
        <tr align="center">
            <td>${emp.id}</td>
            <td>${emp.lastName}</td>
            <td>${emp.email}</td>
            <td>${emp.gender==0?"女":"男"}</td>
            <td>${emp.department.departmentName}</td>
            <td><a href="edit">EDIT</a></td>
            <td><a href="DELETE">DELETE</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
