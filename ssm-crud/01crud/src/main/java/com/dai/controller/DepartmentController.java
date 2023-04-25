package com.dai.controller;

import com.dai.entity.Department;
import com.dai.service.DepartmentService;
import com.dai.uitl.ReturnJson;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @description:
 * @author: dai.jf
 * @date: 2021/9/1-2:54
 */
@Controller
public class DepartmentController {

  @Autowired
  DepartmentService deptService;

  @RequestMapping("/getAllDept")
  @ResponseBody
  public ReturnJson getDepartment() {
    List<Department> allDept = deptService.getAll();
    return ReturnJson.success().add("allDept", allDept);
  }

}
