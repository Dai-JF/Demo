<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div id="page_nav">
    <%--如果是首页则不显示上一页，首页--%>
    <c:if test="${requestScope.page.pageCurrent > 1 }">
        <a href="${requestScope.page.url}&pageCurrent=1">首页</a>
        <a href="${requestScope.page.url}&pageCurrent=${requestScope.page.pageCurrent-1}">上一页</a>
    </c:if>

    <%--页码输出开始--%>
    <c:choose>
        <%--情况一 ：总页码数 <= 5,页码的范围是 1-5;--%>
        <c:when test="${ requestScope.page.pageTotal <=5 }">
            <c:set var="begin" value="1"/>
            <c:set var="end" value="${requestScope.page.pageTotal}"/>
        </c:when>
        <%--情况二：总页数 > 5--%>
        <c:when test="${requestScope.page.pageTotal >5 }">
            <c:choose>
                <%--小情况1：当前页码为前三个：1 2 3 的情况，页码范围是 1-5 --%>
                <c:when test="${requestScope.page.pageCurrent <= 3 }">
                    <c:set var="begin" value="1"/>
                    <c:set var="end" value="5"/>
                </c:when>
                <%--小情况2：当前页码为后三个：8 9 10 的情况，页码范围是 总页码减4--总页码  --%>
                <c:when test="${requestScope.page.pageCurrent > requestScope.page.pageTotal-3 }">
                    <c:set var="begin" value="${requestScope.page.pageTotal-4}"/>
                    <c:set var="end" value="${requestScope.page.pageTotal}"/>
                </c:when>
                <%--小情况3：剩余的中间部分， 4 5 6 7 ，页码范围是 当前页-2 --当前页+2  --%>
                <c:otherwise>
                    <c:set var="begin" value="${requestScope.page.pageCurrent-2}"/>
                    <c:set var="end" value="${requestScope.page.pageCurrent+2}"/>
                </c:otherwise>
            </c:choose>
        </c:when>
    </c:choose>

    <c:forEach begin="${begin}" end="${end}" var="i">
        <c:if test="${i==requestScope.page.pageCurrent}">
            【${i}】
        </c:if>
        <c:if test="${i!=requestScope.page.pageCurrent}">
            <a href="${requestScope.page.url}&pageCurrent=${i}">${i}</a>
        </c:if>
    </c:forEach>
    <%--页码输出结束--%>

    <%--如果是尾页则不显示下一页，末页--%>
    <c:if test="${requestScope.page.pageCurrent < requestScope.page.pageTotal }">
        <a href="${requestScope.page.url}&pageCurrent=${requestScope.page.pageCurrent+1}">下一页</a>
        <a href="${requestScope.page.url}&pageCurrent=${requestScope.page.pageTotal}">末页</a>
    </c:if>
    共${requestScope.page.pageTotal}页，${requestScope.page.pageTotalCount}条记录
    到第<input value="${param.pageCurrent}" name="pn" id="pn_input"/>页

    <input id="jumpBtn" type="button" value="确定">
</div>

<script>
    $(function () {
        //跳转到指定页码
        $("#jumpBtn").click(function () {
            let pageCurrent = $("#pn_input").val();
            //location对象：有个href属性可以获取浏览器地址栏的地址
            location.href = "${requestScope.bathPath}${requestScope.page.url}&pageCurrent=" + pageCurrent
        })
    })
</script>
<%--分页条的结束--%>


