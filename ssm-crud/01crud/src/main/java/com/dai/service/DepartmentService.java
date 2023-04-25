package com.dai.service;

import com.dai.entity.Department;
import com.dai.mapper.DepartmentMapper;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @author: dai.jf
 * @date: 2021/9/1-2:55
 */
@Service
public class DepartmentService {

  @Autowired
  DepartmentMapper deptMapper;

  public List<Department> getAll() {
    return deptMapper.selectByExample(null);
  }
}
