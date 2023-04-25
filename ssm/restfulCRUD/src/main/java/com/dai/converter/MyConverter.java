package com.dai.converter;

import com.dai.dao.DepartmentDao;
import com.dai.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

/**
 * @author: dai.jf
 * @date: 2021/8/23 15:33
 * @description: 实现Converter接口，完成自定义类型转换器【string--->employees】
 */
public class MyConverter implements Converter<String, Employee> {
  @Autowired DepartmentDao departmentDao;

  /**
   * 自定义转换规则
   * @param source
   * @return
   */
  @Override
  public Employee convert(String source) {
    System.out.println("页面提交的将要转换字符串" + source);
    Employee employee = new Employee();
    if (source.contains("-")) {
      String[] split = source.split("-");
      employee.setLastName(split[0]);
      employee.setEmail(split[1]);
      employee.setGender(Integer.parseInt(split[2]));
      employee.setDepartment(departmentDao.getDepartment(Integer.parseInt(split[3])));
    }
    return employee;
  }
}
