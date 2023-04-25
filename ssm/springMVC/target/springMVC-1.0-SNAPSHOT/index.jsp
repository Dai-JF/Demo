<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script src="scripts/jquery-1.9.1.min.js"></script>
<html>
<body>
<%
    pageContext.setAttribute("base", request.getContextPath());
%>
<%--发起图书的crud操作==[rest风格的url地址]

请求url       请求方式              表示含义
/book/1         GET             查询 1号图书
/book/1         DELETE          删除 1号图书
/book/1         PUT             更新 1号图书
/book/          POST            添加图书
--%>

<%--a标签为get请求

如何发起delete put请求?
    1.配置filter:springMVC中有一个filter可以把post请求转为指定请求

    2.
        1)创建post类型表单
        2)表单项携带一个"_method"参数
        3)这个"_method"就是delete put

  注:tomcat8以上版本不兼容
  解决:在目标视图前面加一个isErrorPage=“true”
--%>
<a href="${base }/topage">去登录页</a>
<a id="ajaxTest" href="${base }/ajax1">ajax获取员工信息</a>
<div></div>

<a id="send" href="${base }/ajax2">ajax发送json请求</a>
<form action="book" method="post">
    <input type="submit" value="添加图书">
</form>

<form action="book/1" method="post">
    <input name="_method" value="delete">
    <input type="submit" value="删除1号图书">
</form>

<form action="book/1" method="post">
    <input name="_method" value="put">
    <input type="submit" value="更新1号图书">
</form>

<a href="book/1">查询1号图书</a><br>
<br><br><br>

<a href="param?username=user">参数传递</a><br>
<a href="header">请求头中浏览器信息</a>
<br><br><br>

<form action="addUser">
    用户名:<input name="username"/><br>
    密 码:<input name="password"/><br>
    <%--级联封装--%>
    地 址:<input name="address.province"/><br>
    <input type="submit" value="提交">
</form>


<a href="toPage">去page页面</a>
<a href="custom">自定义视图解析器</a>


</body>
<script>

    $("#ajaxTest").click(function () {
        //发送ajax请求获取员工信息

        //1.ajax请求
        $.ajax({
            url: "${base }/ajax1",
            type: "GET",
            error: function (data) {
                alert("请求失败" + data)
            },
            success: function (data) {
                $.each(data, function () {
                    var info = this.lastName + "---" + this.birth;
                    $("div").append(info + "<br>")
                })
            }
        })
        //2 ajax--post请求
        //$.post({
        // })
        //3  ajax--get请求

        //4 getjson请求


        //禁用默认行为【是a标签，默认行为就是跳转】
        return false;
    })

    $("#send").click(function () {
        //点击发送ajax请求，请求带的数据是json

        //这是js对象
        var str = {
            lastName: "dd",
            email: "ad@qq.com",
            gender: 0
        };
        //转为json数据
        var jsonStr = JSON.stringify(str);
        $.ajax({
            url: "${base }/ajax2",
            type: "POST",
            data: jsonStr,
            contentType: "application/json",
            success: function (data) {
                alert(data)
            },
            error: function (data) {
                alert("error")
            }
        })
        return false;
    })
</script>
</html>
