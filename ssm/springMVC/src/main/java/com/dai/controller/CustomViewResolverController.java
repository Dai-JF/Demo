package com.dai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
public class CustomViewResolverController {

  /**
   * 自定义前缀
   *
   * @return
   */
  @RequestMapping("/custom")
  public String custom(Model model) {
    ArrayList<Object> vList = new ArrayList<>();
    vList.add("1");
    vList.add("2");
    model.addAttribute("video", vList);
    return "custom:/custom";
  }
}
