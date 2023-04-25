package com.dai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: dai.jf
 * @date: 2021/8/24 3:51
 * @description:
 */
@Controller
public class InterceptorController {
  @RequestMapping("in")
  public String interceptor() {
    return "success";
  }
}
