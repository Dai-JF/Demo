package com.dai.demo.controller;

import com.dai.demo.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dai.jf
 * @date 2021/11/3-9:07
 * @description:
 */
@RestController
public class HelloController {

  /**
   * @ Value：获取配置文件的值，：后面写获取不到配置文件的值时的默认值
   */
  @Value("${person.name:张三}")
  private String name;

  @Autowired
  private Person person;

  @GetMapping("/")
  public String hello() {
    return person.getClass().toString();
  }


  @GetMapping("/person")
  public Person person() {
    return person;
  }
}
