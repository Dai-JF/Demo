package com.dai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author: dai.jf
 * @date: 2021/8/20 4:12
 * @description:
 */
@Controller
public class RestController {

  /**
   * 路径上有占位符 语法:在任意路径写 { 变量名 } <br>
   * 注:只能占一层路径
   *
   * <p>PathVariable:动态获取url中{}内变量的值
   *
   * <p>/book/1 /book/2---->/book/{id}
   */
  @RequestMapping(value = "/book", method = RequestMethod.POST)
  public String add() {
    System.out.println("添加了图书");
    return "success";
  }

  @RequestMapping(value = "/book/{id}", method = RequestMethod.DELETE)
  public String delete(@PathVariable("id") Integer id) {
    System.out.println("删除了" + id + "号图书");
    return "success";
  }

  @RequestMapping(value = "/book/{id}", method = RequestMethod.PUT)
  public String update(@PathVariable("id") Integer id) {
    System.out.println("更新了" + id + "号图书");
    return "success";
  }

  @RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
  public String query(@PathVariable("id") Integer id) {
    System.out.println("查询到了" + id + "号图书");
    return "success";
  }
}
