<%--
  Created by: dai
  Date: 2021/8/28 - 17:22
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <%--request.getContextPath()拿到的是你的web项目的根路径，就是webapp
    web路径：
    不以/开始的相对路径，找资源，以当前资源的路径为基准，经常容易出问题。
    以/开始的相对路径，找资源，以服务器的路径为标准(http://localhost:3306)；需要加上项目名
	http://localhost:3306/crud
    --%>
    <%pageContext.setAttribute("base", request.getContextPath());%>
    <script src="${base}/static/js/jquery-3.5.1.min.js"></script>
    <script src="${base}/static/bootstrap-3.4.1-dist/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${base}/static/bootstrap-3.4.1-dist/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <%--    标题--%>
    <div class="row">
        <div class="col-md-4 col-lg-offset-4">
            <h1>SSM-CRUD</h1>
        </div>
    </div>
    <%--按钮--%>
    <div class="row">
        <div class="col-md-4 col-md-offset-8">
            <button class="btn btn-primary">添加</button>
            <button class="btn btn-danger">删除</button>
        </div>
    </div>
    <%--表格--%>
    <div class="row">
        <div class="col-md-12">
            <table class="table table-hover">
                <tr>
                    <th>ID</th>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>邮箱</th>
                    <th>部门</th>
                    <th>操作</th>
                </tr>
                <c:forEach items="${pageInfo.list}" var="emp">
                    <tr>
                        <td>${emp.empId }</td>
                        <td>${emp.empName }</td>
                        <td>${emp.gender }</td>
                        <td>${emp.email }</td>
                        <td>${emp.department.deptName}</td>
                        <td>
                            <button class="btn btn-primary btn-sm">
                                <span class="glyphicon glyphicon-pencil"></span>编辑
                            </button>

                            <button class="btn btn-danger btn-sm">
                                <span class="glyphicon glyphicon-trash"></span>删除
                            </button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
    <%--分页部分--%>
    <div class="row">
        <%--分页信息--%>
        <div class="col-md-4">
            当前第${pageInfo.pageNum}页，总${pageInfo.pages}页，共${pageInfo.total}条记录
        </div>
        <%--分页条--%>
        <div class="col-md-8 col-md-offset-4">
            <nav aria-label="Page navigation">
                <ul class="pagination">
                    <%--如果有上一页才显示首页和上一页--%>
                    <c:if test="${pageInfo.hasPreviousPage}">
                        <li>
                        <li><a href="${base}/listAll?pageNum=1">首页</a></li>
                        <%--上一页：当前页-1 --%>
                        <Li>
                            <a href="${base}/listAll?pageNum=${pageInfo.pageNum-1}"
                               aria-label="Previous">
                                <span aria-hidden="true">&laquo;</span>
                            </a>
                        </li>
                    </c:if>
                    <%--连续显示的页码--%>
                    <c:forEach items="${pageInfo.navigatepageNums}" var="page_num">
                        <%--当前页高亮显示--%>
                        <c:if test="${page_num==pageInfo.pageNum}">
                            <li class="active"><a href="#">${page_num}</a></li>
                        </c:if>
                        <%--普通页码发送请求就能点击跳转--%>
                        <c:if test="${page_num!=pageInfo.pageNum}">
                            <li><a href="${base}/listAll?pageNum=${page_num}">${page_num}</a></li>
                        </c:if>
                    </c:forEach>
                    <%--同理：有下一页才显示下一页和末页--%>
                    <c:if test="${pageInfo.hasNextPage}">
                        <li>
                                <%--下一页：当前页+1 --%>
                            <a href="${base}/listAll?pageNum=${pageInfo.pageNum+1}"
                               aria-label="Next">
                                <span aria-hidden="true">&raquo;</span>
                            </a>
                        </li>
                        <%--末页就是来到总页码处--%>
                        <li><a href="${base}/listAll?pageNum=${pageInfo.pages}">末页</a></li>
                    </c:if>

                </ul>
            </nav>
        </div>
    </div>

</div>
</body>
</html>
