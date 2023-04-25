<%--
  Created by Dai
  Date: 2021/8/7 23:54
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    //http://192.168.01.04:8080/book/
    String batsPath = request.getScheme()
            + "://" + request.getServerName()
            + ":" + request.getServerPort()
            + request.getContextPath() + "/";
    pageContext.setAttribute("bathPath", batsPath);//页码跳转用
%>
<!-- 写 base标签，永远固定相对路径跳转的结果-->
<base href="<%=batsPath%>>">
<%--公共的css,jquery--%>
<link type="text/css" rel="stylesheet" href="static/css/style.css">
<script type="text/javascript" src="static/script/jquery-1.7.2.js"></script>
