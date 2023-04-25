package com.dai.service;

import com.dai.entity.Employee;
import com.dai.entity.EmployeeExample;
import com.dai.entity.EmployeeExample.Criteria;
import com.dai.mapper.EmployeeMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: dai.jf
 * @date: 2021/8/28-17:30
 */
@Service
public class EmployeeService {

  @Autowired
  EmployeeMapper employeeMapper;

  /**
   * Description:
   *
   * @auther: dai.jf
   * @date: 2021/9/1 - 4:57
   * @param:
   * @return: List<Employee>
   */
  public List<Employee> listAll() {
    EmployeeExample example = new EmployeeExample();
    example.setOrderByClause("emp_id");
    return employeeMapper.selectByExampleWithDept(example);
  }

  /**
   * Description: 员工增加方法
   *
   * @auther: dai.jf
   * @date: 2021/9/1 - 5:08
   * @param:
   * @return: void
   */
  public void add(Employee e) {
    employeeMapper.insertSelective(e);
  }

  /**
   * Description: 检验用户名是否可用 countByExample:按条件统计符合条件的记录数
   *
   * @auther: dai.jf
   * @date: 2021/9/1 - 14:50
   * @param: username
   * @return: boolean true 可用
   */
  public boolean checkUser(String username) {
    EmployeeExample example = new EmployeeExample();
    Criteria criteria = example.createCriteria();
    criteria.andEmpNameEqualTo(username);
    long count = employeeMapper.countByExample(example);
    return count == 0;
  }

  /**
   * Description: 根据id找到员工
   *
   * @auther: dai.jf
   * @date: 2021/9/2 - 2:53
   * @param: id
   * @return: Employee
   */
  public Employee getEmp(Integer id) {
    return employeeMapper.selectByPrimaryKey(id);
  }

  /**
   * Description: 更新员工信息
   *
   * @auther: dai.jf
   * @date: 2021/9/2 - 2:54
   * @param: employee
   * @return: void
   */
  public void updateEmp(Employee employee) {
    employeeMapper.updateByPrimaryKeySelective(employee);
  }

  /**
   * Description: 根据员工id进行删除
   *
   * @auther: dai.jf
   * @date: 2021/9/2 - 2:54
   * @param: id
   * @return: void
   */
  public void delete(Integer id) {
    employeeMapper.deleteByPrimaryKey(id);
  }

  /**
   * Description: 批量删除员工
   *
   * @auther: dai.jf
   * @date: 2021/9/2 - 2:54
   * @param: ids
   * @return: void
   */
  public void deleteBatch(List<Integer> ids) {
    EmployeeExample example = new EmployeeExample();
    Criteria criteria = example.createCriteria();
    criteria.andEmpIdIn(ids);
    //delete from ***where ids in(***);
    employeeMapper.deleteByExample(example);
  }

  public List<Employee> search(String username) {
    EmployeeExample example = new EmployeeExample();
    Criteria criteria = example.createCriteria();
    criteria.andEmpNameEqualTo(username);
    return employeeMapper.selectByExample(example);
  }
}
