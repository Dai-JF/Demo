package com.dai.controller;

import com.dai.dao.DepartmentDao;
import com.dai.dao.EmployeeDao;
import com.dai.entity.Department;
import com.dai.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

/** @author Dai */
@Controller
public class EmployeeController {

  @Autowired EmployeeDao employeeDao;

  @Autowired DepartmentDao departmentDao;

  /**
   * 访问index，直接发送/emps请求，控制器接收/emps请求，查询所有员工信息放在request域中并转发到list页面进行展示
   *
   * @param model 将查询到的数据放在request域中
   */
  @RequestMapping("/emps")
  public String getEmps(Model model) {
    Collection<Employee> all = employeeDao.getAll();
    model.addAttribute("emps", all);
    return "list";
  }

  /** 在list页面点击“添加员工”---查询所有部门信息---来到添加页面---输入员工信息---点击提交---处理器接收保存员工请求--- */
  @RequestMapping("/toaddpage")
  public String toAddPage(Model model) {
    model.addAttribute("employee", new Employee());
    return "add";
  }

  /**
   * @param employee 数据一提交，直接封装成employee对象
   * @return 不能直接转发到列表页 //return "list"--->没数据
   *     <p>保存完成后还是来到list页面
   *     <p>employee【紧跟】 BindingResult ，用于查看校验结果
   *     <p>@Valid 在SpringMVC封装对象的时候，告诉SpringMVC这个javaBean需要校验
   */
  @RequestMapping(value = "/emp", method = RequestMethod.POST)
  public String addEmp(@Valid Employee employee, BindingResult result, Model model) {
    System.out.println("要添加的员工：" + employee);
    boolean hasErrors = result.hasErrors();
    HashMap<String, Object> map = new HashMap<>();
    if (hasErrors) {
      List<FieldError> fieldErrors = result.getFieldErrors();
      for (FieldError error : fieldErrors) {
        System.out.println("错误提示：" + error.getDefaultMessage());
        System.out.println("错误的字段" + error.getField());
        map.put(error.getField(), error.getDefaultMessage());
      }
      model.addAttribute("errorInfo", map);
      return "add";
    } else {
      employeeDao.save(employee);
      // 返回列表页面；重定向到查询所有员工的请求
      return "redirect:/emps";
    }
  }

  /** 自定义类型转换器快速添加员工 */
  @RequestMapping(value = "/quickadd")
  public String quickAdd(@RequestParam("empinfo") Employee employee) {
    System.out.println("封装：" + employee);
    employeeDao.save(employee);
    // 返回列表页面；重定向到查询所有员工的请求
    return "redirect:/emps";
  }

  /**
   * 删除员工
   *
   * @param id
   * @return
   */
  @RequestMapping(value = "/emp/{id}", method = RequestMethod.DELETE)
  public String deleteEmp(@PathVariable("id") Integer id) {
    employeeDao.delete(id);
    return "redirect:/emps";
  }

  /**
   * 点击编辑----查出要修改的员工信息放在request域中，来到修改页面进行修改----->edit页面
   *
   * @param model 存放 要修改的员工信息 放在request域内 用于回显
   * @param id 根据id修改
   * @return
   */
  @RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
  public String getEmp(@PathVariable("id") Integer id, Model model) {
    // 1、查出员工信息
    Employee employee = employeeDao.get(id);
    // 2、放在请求域中
    model.addAttribute("employee", employee);
    // 3、继续查出部门信息放在隐含模型中
    Collection<Department> departments = departmentDao.getDepartments();
    model.addAttribute("depts", departments);
    return "edit";
  }

  /**
   * 修改员工信息
   *
   * @param employee
   * @return
   */
  @RequestMapping(value = "/emp/{id}", method = RequestMethod.PUT)
  public String updateEmp(
      @ModelAttribute("employee") Employee employee /* ,@PathVariable("id")Integer id */) {
    System.out.println("要修改的员工：" + employee);
    // xxxx 更新保存二合一；
    employeeDao.save(employee);
    return "redirect:/emps";
  }

  /**
   * ModelAttribute提前运行根据id找到员工和部门
   *
   * <p>@ModelAttribute注释的方法会在此controller每个方法执行前被执行
   *
   * @param id
   * @param model
   */
  @ModelAttribute
  public void myModelAttribute(
      @RequestParam(value = "id", required = false) Integer id, Model model) {
    if (id != null) {
      Employee employee = employeeDao.get(id);
      model.addAttribute("employee", employee);
    }
    System.out.println("got!");
    // 1、先查出所有部门
    Collection<Department> departments = departmentDao.getDepartments();
    // 2、放在请求域中
    model.addAttribute("depts", departments);
  }
}
