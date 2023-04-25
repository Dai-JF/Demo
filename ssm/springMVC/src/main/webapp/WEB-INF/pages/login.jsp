<%--
  Created by Dai
  Date: 2021/8/24 4:26
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <title>Title</title>
</head>

<%
    pageContext.setAttribute("base", request.getContextPath());
%>
<body>
<h1>
    <fmt:message key="welcomeInfo"/>
</h1>
<form action="">
    <fmt:message key="username"/> ： <input type="text"><br>
    <fmt:message key="password"/>： <input type="text"><br>
    <input type="submit" value="<fmt:message key="btn"/>">
</form>
<!-- 如果点击超链接切换国际化 -->
<a href="topage?locale=zh_CN">中文</a>|<a href="topage?locale=en_US">English</a>
</body>
</html>
