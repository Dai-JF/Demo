package com.dai.entity;

import javax.validation.constraints.Pattern;

/**
 * @author dai
 */
public class Employee {

  private Integer empId;

  @Pattern(regexp = "^[a-zA-Z0-9_-]{3,16}$", message = "用户名必须是3-16位的英文或中英组合")
  private String empName;

  private String gender;

  @Pattern(regexp = "^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$", message = "邮箱格式不正确")
  private String email;

  private Integer dId;

  /**
   * 根据员工查找部门
   */
  private Department department;

  public Employee() {
  }

  public Employee(Integer empId, String empName, String gender, String email, Integer dId) {
    this.empId = empId;
    this.empName = empName;
    this.gender = gender;
    this.email = email;
    this.dId = dId;
  }

  public Integer getEmpId() {
    return empId;
  }

  public void setEmpId(Integer empId) {
    this.empId = empId;
  }

  public String getEmpName() {
    return empName;
  }

  public void setEmpName(String empName) {
    this.empName = empName == null ? null : empName.trim();
  }

  public String getGender() {
    return gender;
  }

  public void setGender(String gender) {
    this.gender = gender == null ? null : gender.trim();
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email == null ? null : email.trim();
  }

  public Integer getdId() {
    return dId;
  }

  public void setdId(Integer dId) {
    this.dId = dId;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  @Override
  public String toString() {
    return "Employee{" +
        "empId=" + empId +
        ", empName='" + empName + '\'' +
        ", gender='" + gender + '\'' +
        ", email='" + email + '\'' +
        ", dId=" + dId +
        ", department=" + department +
        '}';
  }
}