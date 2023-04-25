package com.dai.RestfulCRUD.controller;

import com.dai.RestfulCRUD.dao.EmployeeDao;
import com.dai.RestfulCRUD.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

/**
 * @author: dai.jf
 * @date: 2021/8/22 11:31
 * @description:
 */
@Controller
public class EmpController {

  @Autowired EmployeeDao empDao;

  @RequestMapping("/emps")
  public String listEmps(Model model) {
    Collection<Employee> all = empDao.getAll();
    model.addAttribute("emps", all);
    return "list";
  }
}
