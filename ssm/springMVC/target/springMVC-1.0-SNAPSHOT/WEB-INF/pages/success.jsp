<%--
  Created by Dai
  Date: 2021/8/19 23:21
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" isELIgnored="false" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>成功！</h1>
${sessionScope.sessionKey}
<br>
${requestScope.requestKey}

${requestScope.msg}
</body>
</html>
