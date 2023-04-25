package com.dai.bootstarttest.controller;

import com.dai.hello.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dai.jf
 * @date 2021/11/3-10:56
 * @description:
 */
@RestController
public class HelloController {

  @Autowired
  HelloService service;

  @GetMapping("/")
  public String sayHello() {
   return service.sayHello("张三");
  }
}
