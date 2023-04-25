<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>编辑图书</title>
    <%@include file="/pages/common/style.jsp" %>
    <style type="text/css">
        h1 {
            text-align: center;
            margin-top: 200px;
        }

        h1 a {
            color: red;
        }

        input {
            text-align: center;
        }
    </style>
</head>
<body>
<div id="header">
    <img class="logo_img" alt="" src="../../static/img/logo.gif">
    <span class="wel_word">编辑图书</span>
    <%@include file="/pages/common/manger_menu.jsp" %>
</div>

<div id="main">
    <form action="manager/BookServlet" method="get">
        <%--存在问题：book_edit页面既要实现修改，又要完成添加。但该处隐藏域是固定的add
        那需要解决：让隐藏域的value是可变的--%>
        <input type="hidden" name="action" value="${param.method}">
        <%--图书信息是根据id来修改的，因此要加上下面这句，给servlet发一个id，不然修改不成功--%>
        <input type="hidden" name="id" value="${requestScope.book.id}">
        <%--添加图书时将总页数发送过来--%>
        <input type="hidden" name="pageCurrent" value="${param.pageCurrent}">
        <table>
            <tr>
                <td>名称</td>
                <td>价格</td>
                <td>作者</td>
                <td>销量</td>
                <td>库存</td>
                <td colspan="2">操作</td>
            </tr>
            <tr>
                <td><input name="name" type="text" value="${requestScope.book.name}"/></td>
                <td><input name="price" type="text" value="${requestScope.book.price}"/></td>
                <td><input name="author" type="text" value="${requestScope.book.author}"/></td>
                <td><input name="sales" type="text" value="${requestScope.book.sales}"/></td>
                <td><input name="stock" type="text" value="${requestScope.book.stock}"/></td>
                <td><input type="submit" value="提交"/></td>
            </tr>
        </table>
    </form>

</div>

<%@include file="/pages/common/footer.jsp" %>
</body>
</html>