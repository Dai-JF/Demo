package com.dai.RestfulCRUD.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/** @author Dai */
public class Employee {

  private Integer id;
  private String lastName;

  @JsonFormat(pattern = "yyyy-MM-dd")
  private Date birth = new Date();

  private String email;
  /** 1 male, 0 female */
  private Integer gender;

  private Department department;

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getEmail() {
    return email;
  }

  public Date getBirth() {
    return birth;
  }

  public void setBirth(Date birth) {
    this.birth = birth;
  }

  @Override
  public String toString() {
    return "Employee{"
        + "id="
        + id
        + ", lastName='"
        + lastName
        + '\''
        + ", birth="
        + birth
        + ", email='"
        + email
        + '\''
        + ", gender="
        + gender
        + ", department="
        + department
        + '}';
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public Integer getGender() {
    return gender;
  }

  public void setGender(Integer gender) {
    this.gender = gender;
  }

  public Department getDepartment() {
    return department;
  }

  public void setDepartment(Department department) {
    this.department = department;
  }

  public Employee(
      Integer id, String lastName, String email, Integer gender, Department department) {
    super();
    this.id = id;
    this.lastName = lastName;
    this.email = email;
    this.gender = gender;
    this.department = department;
  }

  public Employee() {}
}
