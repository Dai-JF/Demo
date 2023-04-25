<%--
  Created by: dai
  Date: 2021/8/31 - 18:43
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<script>
  function build_page_nav(result) {
    //page_nav_area
    $("#page_nav_area").empty();
    var ul = $("<ul></ul>").addClass("pagination");

    //构建元素
    var firstPageLi = $("<li></li>").append($("<a></a>").append("首页").attr("href", "#"));
    var prePageLi = $("<li></li>").append($("<a></a>").append("&laquo;"));
    if (result.extend.pageInfo.hasPreviousPage == false) {
      firstPageLi.addClass("disabled");
      prePageLi.addClass("disabled");
    } else {
      //为元素添加点击翻页的事件
      firstPageLi.click(function () {
        to_page(1);
      });
      prePageLi.click(function () {
        to_page(result.extend.pageInfo.pageNum - 1);
      });
    }

    var nextPageLi = $("<li></li>").append($("<a></a>").append("&raquo;"));
    var lastPageLi = $("<li></li>").append($("<a></a>").append("末页").attr("href", "#"));
    if (result.extend.pageInfo.hasNextPage == false) {
      nextPageLi.addClass("disabled");
      lastPageLi.addClass("disabled");
    } else {
      nextPageLi.click(function () {
        to_page(result.extend.pageInfo.pageNum + 1);
      });
      lastPageLi.click(function () {
        to_page(result.extend.pageInfo.pages);
      });
    }

    //添加首页和前一页 的提示
    ul.append(firstPageLi).append(prePageLi);
    //1,2，3遍历给ul中添加页码提示
    $.each(result.extend.pageInfo.navigatepageNums, function (index, item) {

      var numLi = $("<li></li>").append($("<a></a>").append(item));
      if (result.extend.pageInfo.pageNum == item) {
        numLi.addClass("active");
      }
      numLi.click(function () {
        to_page(item);
      });
      ul.append(numLi);
    });
    //添加下一页和末页 的提示
    ul.append(nextPageLi).append(lastPageLi);

    //把ul加入到nav
    var navEle = $("<nav></nav>").append(ul);
    navEle.appendTo("#page_nav_area");
  }
</script>
</body>
</html>
