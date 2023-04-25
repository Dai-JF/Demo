package com.dai.controller;

import com.dai.entity.Employee;
import com.dai.service.EmployeeService;
import com.dai.uitl.ReturnJson;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description:
 * @author: dai.jf
 * @date: 2021/8/28-17:21
 */

@Controller
public class EmployeeController {

  @Autowired
  EmployeeService empService;
  //
  ///**
  // * @description: 查询员工（分页查询） index页面发请求时没带：pageNum，我们可以设置默认值
  // * @author dai.jf
  // * @date 2021/8/28
  // * @return: java.lang.String
  // */
  //@RequestMapping("/listAll")
  //public String listAll(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
  //    Model model) {
  //  //引入pageHelper,传入页码以及每页的数量
  //  PageHelper.startPage(pageNum, 5);
  //  //startPage后紧跟的查询就是分页查询
  //  List<Employee> listAll = empService.listAll();
  //  //用PageInfo对结果进行包装,只需要将PageInfo交给页面就行了，里面封装了许多比如：前一页，总页数，当前页等
  //  //5：页面显示连续的5页
  //  PageInfo page = new PageInfo(listAll, 5);
  //  //将分页信息传给页面
  //  model.addAttribute("pageInfo", page);
  //  return "list";
  //}


  /**
   * @description: [改良] index页面发送ajax请求进行员工分页数据的查询  2、服务器将查出的数据，以json字符串返回给浏览器
   * @author dai.jf
   * @date 2021/8/31
   * @param: pageNum
   * @param: model
   * @return: com.github.pagehelper.PageInfo
   */
  @RequestMapping("/listAll")
  @ResponseBody
  public ReturnJson listAll(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
    PageHelper.startPage(pageNum, 5);
    List<Employee> listAll = empService.listAll();
    PageInfo page = new PageInfo(listAll, 5);
    return ReturnJson.success().add("pageInfo", page);
  }


  /**
   * Description: Valid标识的参数表示他需要校验,BindingResult:封装校验的结果
   *
   * @auther: dai.jf
   * @date: 2021/9/1 - 4:48
   * @param: employee
   * @return: com.dai.uitl.ReturnJson
   */
  @RequestMapping(value = "/emp", method = RequestMethod.POST)
  @ResponseBody
  public ReturnJson add(@Valid Employee employee, BindingResult result) {
    if (result.hasErrors()) {
      HashMap<String, Object> errorMap = new HashMap<>();
      //校验失败,在模态框内显示错误信息
      List<FieldError> errors = result.getFieldErrors();
      for (FieldError error : errors) {
        System.out.println("错误的字段名" + error.getField());
        System.out.println("错误信息" + error.getDefaultMessage());
        errorMap.put(error.getField(), error.getDefaultMessage());
      }
      return ReturnJson.fail().add("errorField", errorMap);
    } else {
      empService.add(employee);
      return ReturnJson.success();
    }
  }

  /**
   * Description:检验用户名是否可用
   *
   * @auther: dai.jf
   * @date: 2021/9/1 - 14:52
   * @param: username
   * @return: ReturnJson
   */
  @RequestMapping("/check")
  @ResponseBody
  public ReturnJson checkUser(@RequestParam("empName") String username) {
    //判断用户名是否符合正则
    String regx = "^[a-zA-Z0-9_-]{3,16}$";
    if (!username.matches(regx)) {
      return ReturnJson.fail().add("va_msg", "用户名必须是3-16位的英文或中英组合");
    }
    boolean b = empService.checkUser(username);
    if (b) {
      return ReturnJson.success();
    } else {
      return ReturnJson.fail().add("va_msg", "用户名不可用");
    }
  }

  /**
   * Description: 根据id查找员工
   *
   * @auther: dai.jf
   * @date: 2021/9/1 - 17:44
   * @param: id 从路径中取出id
   * @return: ReturnJson
   */
  @RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
  @ResponseBody
  public ReturnJson getEmp(@PathVariable("id") Integer id) {
    Employee employee = empService.getEmp(id);
    return ReturnJson.success().add("emp", employee);
  }

  /**
   * Description:更新员工
   *
   * @auther: dai.jf
   * @date: 2021/9/2 - 3:03
   * @param: employee
   * @return: ReturnJson
   */
  @RequestMapping(value = "/emp/{empId}", method = RequestMethod.PUT)
  @ResponseBody
  public ReturnJson updateEmp(Employee employee) {
    System.out.println("更新的数据：" + employee);
    empService.updateEmp(employee);
    return ReturnJson.success();
  }

  /**
   * Description: 删除员工
   *
   * @auther: dai.jf
   * @date: 2021/9/2 - 3:03
   * @param: ids
   * @return: ReturnJson
   */
  @RequestMapping(value = "/emp/{ids}", method = RequestMethod.DELETE)
  @ResponseBody
  public ReturnJson deleteEmp(@PathVariable("ids") String ids) {
    if (ids.contains("-")) {
      //批量删除
      List<Integer> deleteID = new ArrayList<>();
      String[] strIDS = ids.split("-");
      //组装id的集合
      for (String id : strIDS) {
        deleteID.add(Integer.parseInt(id));
      }
      empService.deleteBatch(deleteID);
    } else {
      //单个删除
      Integer id = Integer.parseInt(ids);
      empService.delete(id);
    }
    return ReturnJson.success();
  }

  @RequestMapping("/search")
  @ResponseBody
  public ReturnJson searchEmp(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
      String username) {
    List<Employee> emp = empService.search(username);
    PageInfo part = new PageInfo(emp);
    return ReturnJson.success().add("part", emp);
  }

}
