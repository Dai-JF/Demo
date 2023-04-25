package com.dai.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author dai.jf
 * @date 2021/10/20-9:31
 * @description:
 */
@Controller
public class ThController {
  @GetMapping("/th")
  public String thTest(Model model){
    model.addAttribute("msg","hello thymeleaf");
    model.addAttribute("link","http://www.baidu.com");
    return "success";
  }
}
