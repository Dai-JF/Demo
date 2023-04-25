<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>购物车</title>
    <%@include file="/pages/common/style.jsp" %>
    <script>
        $(function () {
            //删除按钮绑定单击事件
            $("a.deleteBtn").click(function () {
                return confirm("确认删除【" + $(this).parent().parent().find("td:first").text() + "】吗？")
            })

            //清空购物车按钮绑定单击事件
            $("#clear").click(function () {
                return confirm("确认清空购物车吗？")
            })

            //给输入框绑定onchange事件
            $(".updateCount").change(function () {
                //var name = $(this).parent().parent().find("td:first").text();
                var count = this.value;
                var id = $(this).attr('myId');
                location.href = "http://localhost:8080/book/CarServlet?action=updateCount&count=" + count + "&id=" + id

                <%--if (confirm("确认将【" + name + "】数量修改为[" + count + "]吗？")) {--%>
                <%--    //向服务器发起请求，保存修改的数据--%>
                <%--    location.href = "${requestScope.bathPath}CarServlet?action=updateCount&count=" + count + "&id=" + id;--%>
                <%--} else {--%>
                <%--    //defaultValue是表单对象的属性--%>
                <%--    this.value = this.defaultValue;--%>
                <%--}--%>
            })
        })
    </script>
</head>
<body>

<div id="header">
    <img class="logo_img" alt="" src="static/img/logo.gif">
    <span class="wel_word">购物车</span>
    <%@include file="/pages/common/login_success_menu.jsp" %>
</div>

<div id="main">
    <table>
        <tr>
            <td>商品名称</td>
            <td>数量</td>
            <td>单价</td>
            <td>金额</td>
            <td>操作</td>
        </tr>
        <c:if test="${empty sessionScope.cart.items}">
            <%--购物车为空--%>
            <tr>
                <td colspan="5"><a href="index.jsp">亲，快去浏览商品吧！</a></td>
            </tr>
        </c:if>

        <c:if test="${not empty sessionScope.cart.items}">
            <%--购物车非空--%>
            <c:forEach items="${sessionScope.cart.items}" var="items">
                <tr>
                    <td>${items.value.name}</td>
                    <td>
                        <input class="updateCount" style="width: 50px;"
                            <%--自定义属性--%>
                               myId="${items.value.id}"
                               type="number" value="${items.value.count}">
                    </td>
                    <td>${items.value.price}</td>
                    <td>${items.value.totalPrice}</td>
                    <td><a class="deleteBtn" href="CarServlet?action=deleteItem&id=${items.value.id}">删除</a></td>
                </tr>
            </c:forEach>
        </c:if>
    </table>

    <c:if test="${ not empty sessionScope.cart.items}">
        <div class="cart_info">
            <span class="cart_span">购物车中共有<span class="b_count">${sessionScope.cart.totalCount}</span>件商品</span>
            <span class="cart_span">总金额<span class="b_price">${sessionScope.cart.totalPrice}</span>元</span>
            <span class="cart_span"><a id="clear" href="CarServlet?action=clearItems">清空购物车</a></span>
            <span class="cart_span"><a href="OrderServlet?action=createOrder">去结账</a></span>
        </div>
    </c:if>
</div>

<%@include file="/pages/common/footer.jsp" %>
</body>
</html>