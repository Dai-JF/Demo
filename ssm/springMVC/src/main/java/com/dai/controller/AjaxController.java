package com.dai.controller;

import com.dai.RestfulCRUD.dao.EmployeeDao;
import com.dai.RestfulCRUD.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

/**
 * @author: dai.jf
 * @date: 2021/8/23 23:34
 * @description:
 */
@Controller
public class AjaxController {

  @Autowired EmployeeDao dao;

  @ResponseBody
  @RequestMapping("/hh")
  public ResponseEntity<String> responseEntity() {
    String body = "<h1>hi</h1>";
    MultiValueMap<String, String> headers = new HttpHeaders();
    headers.add("set-cookie", "user=123");
    return new ResponseEntity<String>(body, headers, HttpStatus.OK);
  }

  /**
   * 1、将返回的数据放在响应体内
   *
   * <p>2、把返回的内容直接显示在页面
   *
   * <p>如果是对象 jackson包自动将对象转为json格式 返回浏览器
   */
  @ResponseBody
  @RequestMapping("/ajax1")
  public Collection<Employee> ajaxTest1() {
    Collection<Employee> all = dao.getAll();
    return all;
  }

  /**
   * responseBody:返回json数据
   *
   * <p>requestBody:1、获取请求体内容 2、接受json数据 封装成对象
   *
   * <p>这里直接将接受的数据转为employee对象
   *
   * @param employee
   * @return
   */
  @RequestMapping("/ajax2")
  public String ajaxTest2(@RequestBody Employee employee) {
    System.out.println(employee);
    return "success";
  }

  /**
   * HttpEntity:获取请求头
   *
   * @param str
   * @return
   */
  @RequestMapping("/testH")
  public String ajaxTest2(HttpEntity<String> str) {
    System.out.println(str);
    return "success";
  }
}
