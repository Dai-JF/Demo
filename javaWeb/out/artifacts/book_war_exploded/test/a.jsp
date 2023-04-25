<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="pojo.User" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by Dai
  Date: 2021/8/7 13:43
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%--<%
    session.setAttribute("key",12);
%>--%>
<c:set scope="session" var="key" value="12"/>
${sessionScope.key}
<hr>

<c:if test="${12==12}">
    正确
</c:if>


<c:choose>
    <c:when test="${key>12}">
        大啦
    </c:when>
    <c:when test="${key<12}">
        小啦
    </c:when>
    <c:when test="${key==12}">
        对啦
    </c:when>

    <c:otherwise>
        再想想
    </c:otherwise>
</c:choose>
<hr>

<table>

    <c:forEach begin="1" end="10" var="i">
        <tr>
            <td>${i}</td>
        </tr>

    </c:forEach>
</table>
<hr>

<%--遍历object数组--%>
<%
    request.setAttribute("arr", new Integer[]{123, 456, 789});
%>

<c:forEach items="${requestScope.arr}" var="item">
    ${item}<br>
</c:forEach>
<hr>

<%--遍历map集合--%>
<%
    HashMap<Object, Object> map = new HashMap<>();

    map.put("key1", 1);
    map.put("key2", 2);
    map.put("key3", 3);
    map.put("key4", 4);
//    for (Map.Entry entry : map.entrySet()){
//        System.out.println(map);
//    }

    request.setAttribute("map", map);
%>

<c:forEach var="entry" items="${map}">

    ${entry}<br><br>
    ${entry.key}=${entry.value}

</c:forEach>

<br>
<hr>
<%--集合遍历user类--%>

<%
    ArrayList<User> list = new ArrayList<>();
    for (int i = 1; i < 10; i++) {
        list.add(new User(i, "name" + i, "pass" + i, "email" + i));
    }

    request.setAttribute("list", list);
%>

<table border="1" width="300px">
    <tr>
        <th>id</th>
        <th>用户名</th>
        <th>密码</th>
        <th>邮箱</th>
    </tr>

    <c:forEach begin="2" end="9" step="2" items="${requestScope.list}" var="stuList">
        <tr>
            <td>${stuList.id}</td>
            <td>${stuList.username}</td>
            <td>${stuList.password}</td>
            <td>${stuList.email}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
