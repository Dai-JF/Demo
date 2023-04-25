<%--
  Created by: dai
  Date: 2021/8/31 - 11:14
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <%pageContext.setAttribute("base", request.getContextPath());%>
    <script src="${base}/static/js/jquery-3.5.1.min.js"></script>
    <script src="${base}/static/bootstrap-3.4.1-dist/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="${base}/static/bootstrap-3.4.1-dist/css/bootstrap.min.css">
</head>
<body>
<div class="container">
    <%-----------------------------标题-----------------------%>
    <div class="row">
        <div class="col-md-4 col-lg-offset-4"><h1>SSM-CRUD</h1></div>
    </div>
    <%----------------------------搜索框--------------------------%>
    <div class="row">
        <div class="col-md-4">
            <div class="input-group">
                <input type="text" class="form-control" placeholder="请输入员工姓名"/>
                <span class="input-group-btn">
                    <button class="btn btn-primary glyphicon glyphicon-search" id="btn_search">搜索
                    </button>
                </span>
            </div>
        </div>
    </div>
    <%--------------------------按钮-------------------------------------%>
    <div class="row">
        <div class="col-md-4 col-md-offset-8">
            <button class="btn btn-primary" id="btn_add_modal">添加</button>
            <button class="btn btn-danger" id="btn_delete_all">删除</button>
        </div>
    </div>
    <%----------------------------表格---------------------------------%>
    <div class="row">
        <div class="col-md-12">
            <table class="table table-hover" id="emp_table">
                <thead>
                <tr>
                    <th>
                        <input type="checkbox" id="check_all"/>
                    </th>
                    <th>ID</th>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>邮箱</th>
                    <th>部门</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                </tbody>
            </table>
        </div>
    </div>
    <%-------------------------------分页部分--------------------------------%>
    <div class="row">
        <%--分页信息--%>
        <div class="col-md-4" id="page_info"></div>
        <%--分页条--%>
        <div class="col-md-8 col-md-offset-4" id="page_nav"></div>
    </div>
</div>

<!----------------------------------员工添加模态框-------------------------->
<div class="modal fade" id="modal_add" tabindex="-1">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">员工添加</h4>
            </div>
            <div class="modal-body">
                <%--springMVC自动为封装javaBean对象
                唯一要求：表单的name值和javaBean的属性值相同--%>
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">用户名</label>
                        <div class="col-sm-10">
                            <input type="text" name="empName" class="form-control" id="input_name"
                                   placeholder="username">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">邮箱</label>
                        <div class="col-sm-10">
                            <input type="text" name="email" class="form-control" id="input_email"
                                   placeholder="dai.jf@mail.com">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">性别</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="input_gender1"
                                <%--value就是要提交的数据--%>
                                       value="男" checked="checked"> 男
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="input_gender2"
                                       value="女"> 女
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">部门信息</label>
                        <div class="col-sm-4">
                            <%--根据部门id从数据库中查找遍历并显示--%>
                            <select class="form-control" name="dId">
                            </select>
                        </div>
                    </div>
                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="btn_save">保存</button>
            </div>
        </div>
    </div>
</div>

<!------------------------------员工修改模态框 ---------------------------------->
<div class="modal fade" id="modal_update" tabindex="-1">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                        aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">员工修改</h4>
            </div>
            <div class="modal-body">
                <%--springMVC自动为封装javaBean对象
                唯一要求：表单的name值和javaBean的属性值相同
                --%>
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">用户名</label>
                        <div class="col-sm-10">
                            <p class="form-control-static" id="static_name"></p>
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">邮箱</label>
                        <div class="col-sm-10">
                            <input type="text" name="email" class="form-control" id="update_email"
                                   placeholder="dai.jf@mail.com">
                            <span class="help-block"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">性别</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="update_gender1"
                                <%--value就是要提交的数据--%>
                                       value="男" checked="checked"> 男
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="gender" id="update_gender2"
                                       value="女"> 女
                            </label>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">部门信息</label>
                        <div class="col-sm-4">
                            <%--根据部门id从数据库中查找遍历并显示--%>
                            <select class="form-control" name="dId">
                            </select>
                        </div>
                    </div>
                </form>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="btn_update">更新</button>
            </div>
        </div>
    </div>
</div>
</body>

<script>
  //用于关闭模态框时，页面跳转到当前页
  var totalRecord, current;
  //页面加载完成，获取 第一页的员工数据和分页信息
  $(function () {
    to_page(1);
  })

  //0 点击页码发送ajax请求，获取不同页的信息
  function to_page(pageNum) {
    $.ajax({
      url: "${base}/listAll",
      data: "pageNum=" + pageNum,
      type: "GET",
      success: function (result) {
        //1 解析员工参数
        build_table(result);
        //2 解析分页参数
        build_page_info(result);
        //3 解析分页条参数
        build_page_nav(result);
      }
    })
  }

  //1 解析map中的员工参数
  function build_table(result) {
    // 在构建之前先进行清空，不然每次点击跳转都会append数据
    $("#emp_table tbody").empty();
    //取出map中的员工信息
    var all = result.extendedMap.pageInfo.list;
    //遍历员工信息
    $.each(all, function (index, emp) {
      var check_one = $("<td><input type='checkbox' class='check_one'></td>")
      var emp_id = $("<td></td>").append(emp.empId);
      var emp_name = $("<td></td>").append(emp.empName);
      var emp_gender = $("<td></td>").append(emp.gender);
      var emp_email = $("<td></td>").append(emp.email);
      var emp_dept = $("<td></td>").append(emp.department.deptName);

      var btn_edit = $("<button></button>").addClass("btn btn-primary btn-sm btn_edit").append(
          "<span></span>").addClass("glyphicon glyphicon-pencil").append("编辑");

      //为编辑按钮添加自定义属性，来表示当前员工的id
      btn_edit.attr("edit-id", emp.empId);

      var btn_delete = $("<button></button>").addClass("btn btn-danger btn-sm btn_delete").append(
          "<span></span>").addClass("glyphicon glyphicon-trash").append("删除");

      //为删除按钮添加自定义属性，来表示当前员工的id
      btn_delete.attr("delete-id", emp.empId);

      var btn = $("<td></td>").append(btn_edit).append(" ").append(btn_delete);

      //追加到表格上
      //可以一直链式append是因为append方法执行完成后返回的是原来的元素[tr.append 他还会返回一个tr]
      $("<tr></tr>").append(check_one).append(emp_id).append(emp_name).append(emp_gender).append(
          emp_email).append(emp_dept).append(btn).appendTo("#emp_table tbody");
    })
  }

  //2 解析map中的分页参数
  function build_page_info(result) {
    // 在构建之前先进行清空，不然每次点击跳转都会append数据
    $("#page_info").empty()
    var p_num = result.extendedMap.pageInfo.pageNum;
    var p_total = result.extendedMap.pageInfo.pages;
    var total = result.extendedMap.pageInfo.total;
    $("#page_info").append("当前第" + p_num + " 页，总" + p_total + "页，共" + total + "条记录")
    totalRecord = result.extendedMap.pageInfo.total;
    current = result.extendedMap.pageInfo.pageNum;
  }

  //3 解析map中的分页条参数
  function build_page_nav(result) {
    // 在构建之前先进行清空，不然每次点击跳转都会append数据
    $("#page_nav").empty()
    //nav
    var nav_area = $("<nav></nav>")
    //ul
    var ul_area = $("<ul></ul>").addClass("pagination");
    //首页和前一页
    //attr("href","#"):a标签的禁止默认行为
    var first_page = $("<li></li>").append($("<a></a>").append("首页").attr("href", "#"))
    var pre_page = $("<li></li>").append($("<a></a>").append("&laquo;"));
    if (result.extendedMap.pageInfo.hasPreviousPage === false) {
      first_page.addClass("disabled");
      pre_page.addClass("disabled");
    } else {
      //为元素添加点击翻页的事件
      first_page.click(function () {
        to_page(1)
      })
      pre_page.click(function () {
        to_page(result.extendedMap.pageInfo.pageNum - 1)
      })
    }
    ul_area.append(first_page).append(pre_page);

    //遍历出每一个数字页[1,2,3,4,5]
    $.each(result.extendedMap.pageInfo.navigatepageNums, function (index, item) {
      var num = $("<li></li>").append($("<a></a>").append(item));
      if (result.extendedMap.pageInfo.pageNum === item) {
        num.addClass("active")
      }
      num.click(function () {
        to_page(item)
      })
      ul_area.append(num);
    });

    //下一页和末页
    var next_page = $("<li></li>").append($("<a></a>").append("&raquo;"));
    var last_page = $("<li></li>").append($("<a></a>").append("末页").attr("href", "#"))

    if (result.extendedMap.pageInfo.hasNextPage === false) {
      next_page.addClass("disabled");
      last_page.addClass("disabled");
    } else {
      last_page.click(function () {
        to_page(result.extendedMap.pageInfo.pages)
      })

      next_page.click(function () {
        to_page(result.extendedMap.pageInfo.pageNum + 1)
      })
    }

    ul_area.append(next_page).append(last_page)

    nav_area.append(ul_area)
    nav_area.appendTo("#page_nav")
  }

  //清除表单数据[包括提示信息]
  function rest_form(ele) {
    $(ele)[0].reset();
    //清空表单样式
    $(ele).find("*").removeClass("has-success has-error")
    $(ele).find(".help-block").text("");
  }

  //点击添加，弹出模态框
  $("#btn_add_modal").click(function () {
    //清除表单数据[包括提示信息][jquery没有rest方法,先将其转为dom对象]
    //$("#modal_add form")[0].reset()
    rest_form("#modal_add form");
    //从数据库中查找部门信息遍历并显示在下拉列表
    getAllDept("#modal_add select");
    //弹出模态框
    $("#modal_add").modal({
      //指定不单击时不关闭模式的背景
      backdrop: true
    })
  })

  //发送请求获得部门信息遍历并显示在下拉列表
  function getAllDept(ele) {
    //清空之前的下拉列表的值
    $(ele).empty();
    $.ajax({
      url: "${base}/getAllDept",
      type: "GET",
      success: function (result) {
        //console.log(result)
        //{"code":100,"msg":"处理成功","extendedMap":{"allDept":[{"deptId":1,"deptName":"开发部"},
        // {"deptId":2,"deptName":"销售部"},{"deptId":3,"deptName":"经理部"}]}}

        //$("#modal_add select").append
        $.each(result.extendedMap.allDept, function () {
          //这里可以传参也可以不,不传参就用this代替当前被遍历的对象allDept
          //this.deptName:一对尖括号内显示的内容
          //attr("value", this.deptId):赋一个真正提交的id值
          var select_dept = $("<option></option>").append(this.deptName).attr("value", this.deptId);
          select_dept.appendTo(ele);
        })
      }
    })
  }

  //前端校验
  function regCheck() {
    var empName = $("#input_name").val();
    var regName = /^[a-zA-Z0-9_-]{3,16}$/
    if (!regName.test(empName)) {
      show_msg("#input_name", "error", "用户名长度为3-16");
      return false
    } else {
      show_msg("#input_name", "success", "");
    }

    var empEmail = $("#input_email").val();
    var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/
    if (!regEmail.test(empEmail)) {
      show_msg("#input_email", "error", "邮箱格式不正确");
      return false
    } else {
      show_msg("#input_email", "success", "");
    }
    return true;
  }

  // 提示信息
  function show_msg(ele, status, msg) {
    //清除当前元素校验状态
    $(ele).parent().removeClass("has-success has-error")
    $(ele).next("span").text("")
    if (status === "error") {
      $(ele).parent().addClass("has-error");
      $(ele).next("span").text(msg)
    } else if (status === "success") {
      $(ele).parent().addClass("has-success");
      $(ele).next("span").text("")
    }
  }

  //在提交之前,用于校验用户名是否重复
  $("#input_name").blur(function () {
    var empName = this.value;
    $.ajax({
      url: "${base}/check",
      data: "empName=" + empName,
      type: "POST",
      success: function (result) {
        if (result.code == 100) {
          show_msg("#input_name", "success", "用户名可用");
          $("#btn_save").attr("ajax-sta", "success")
        } else {
          show_msg("#input_name", "error", result.extendedMap.va_msg);
          $("#btn_save").attr("ajax-sta", "error")
        }
      }
    })
  })

  //为模态框的保存按钮添加事件
  $("#btn_save").click(function () {
    //提交前先校验数据格式是否正确
    if (!regCheck()) {
      return false;
    }
    //若用户名不可用就禁用保存
    if ($(this).attr("ajax-sta") == "error") {
      return false;
    }
    //将表单填写的数据交给服务器进行保存
    $.ajax({
      url: "${base}/emp",
      type: "POST",
      //serialize():序列表表格内容为字符串
      data: $("#modal_add form").serialize(),
      success: function (result) {
        if (result.code == 100) {
          //1 处理成功关闭模态框
          $("#modal_add").modal('hide')
          //2 发送ajax请求来到最后一页
          //pageHelper插件可以让查询大于总页码的记录来到最后一页，
          //我们就可以直接让他跳转去总记录数页，那样肯定比总页数多，就必定来到最后一页
          to_page(totalRecord);
        } else {
          //哪个字段不正确,就显示错误
          if (result.extendedMap.errorField.email != undefined) {
            show_msg("#inpu_temail", "error", result.extendedMap.errorField.email)
          }
          if (result.extendedMap.errorField.empName != undefined) {
            show_msg("#input_name", "error", result.extendedMap.errorField.empName)
          }
        }
      }
    })
  })

  // 编辑绑定单击事件这样不行,因为编辑按钮都是后来动态生成的，删除按钮同理
  // $(".btn_edit").click(function () {
  //   alert("edit");
  // })
  $(document).on("click", ".btn_edit", function () {
    //回显员工和部门信息
    getAllDept("#modal_update select");
    //根据传递给编辑按钮上的id值查询员工
    getEmp($(this).attr("edit-id"));

    //弹出模态框把编辑按钮上的员工id传给更新按钮
    //attr:返回被选元素的属性值。这里就是选中edit-id，并返回他的值
    $("#btn_update").attr("edit-id", $(this).attr("edit-id"))
    $("#modal_update").modal({
      backdrop: true
    })
  })

  //查询员工信息并回显
  function getEmp(id) {
    $.ajax({
      url: "${base}/emp/" + id,
      type: "GET",
      success: function (result) {
        var empData = result.extendedMap.emp;
        $("#static_name").text(empData.empName)
        $("#update_email").val(empData.email)
        $("#modal_update input[name=gender]").val([empData.gender])
        $("#modal_update select").val([empData.dId])

      }
    })
  }

  //点击更新，更新数据
  $("#btn_update").click(function () {
    //验证邮箱
    var empEmail = $("#update_email").val();
    var regEmail = /^([a-z0-9_\.-]+)@([\da-z\.-]+)\.([a-z\.]{2,6})$/
    if (!regEmail.test(empEmail)) {
      show_msg("#update_email", "error", "邮箱格式不正确");
      return false
    } else {
      show_msg("#update_email", "success", "");
    }

    //保存更新的信息
    $.ajax({
      url: "${base}/emp/" + $(this).attr("edit-id"),
      type: "PUT",
      data: $("#modal_update form").serialize(),
      success: function (result) {
        $("#modal_update").modal('hide')
        to_page(current)
      }
    })
  })

  //删除按钮绑定事件
  $(document).on("click", ".btn_delete", function () {
    //选择tr的第3个td
    var confirmInfo = $(this).parents("tr").find("td:eq(2)").text();
    //$(this):当前操作的对象【也就是删除按钮】，把id传到删除按钮上
    var emp_id = $(this).attr("delete-id");
    if (confirm("你确认删除【" + confirmInfo + "】吗？")) {
      //确认删除，才发送请求
      $.ajax({
        url: "${base}/emp/" + emp_id,
        type: "DELETE",
        success: function (result) {
          to_page(current)
        }
      })
    }
  })

  //全选和全不选的点击
  $("#check_all").click(function () {
    /* 用prop获取原生dom属性的值，
     而attr可以用来获取自定义属性的值*/
    $(".check_one").prop("checked", $(this).prop("checked"))
  })

  //check_one点击事件
  $(document).on("click", ".check_one", function () {
    //判断全选后，当前选中的个数是不是当前页的左右chek_one
    var flag = $(".check_one:checked").length === $(".check_one").length
    $("#check_all").prop("checked", flag)
  })

  //删除所有的点击事件
  $("#btn_delete_all").click(function () {
    var empNames = "";
    var del_strID = "";
    $.each($(".check_one:checked"), function () {
      //拼接员工姓名
      empNames += $(this).parents("tr").find("td:eq(2)").text() + ","
      //拼接员工id
      del_strID += $(this).parents("tr").find("td:eq(1)").text() + "-"
    })
    //去除最后一个，
    empNames = empNames.substring(0, empNames.length - 1)
    del_strID = del_strID.substring(0, del_strID.length - 1)
    if (confirm("确认删除【" + empNames + "】吗？")) {
      //发送删除所有的ajax请求
      $.ajax({
        url: "${base}/emp/" + del_strID,
        type: "DELETE",
        success: function (result) {
          to_page(current)
        }
      })
    }
  })

  $("#search").click(function () {

  })
</script>
</html>
