package com.dai.boot.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dai.jf
 * @date 2021/10/16-16:43
 * @description:
 */
@RestController
public class HelloController {

  @GetMapping("/user")
  public String get() {
    return "get";
  }

  @PostMapping("/user")
  public String post() {
    return "post";
  }

  @DeleteMapping("/user")
  public String delete() {
    return "delete";
  }

  @PutMapping("/user")
  public String put() {
    return "put";
  }

}
