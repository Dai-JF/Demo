package com.dai.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * @author: dai.jf
 * @date: 2021/8/24 4:25
 * @description:
 */
@Controller
public class GuoJiController {
  @Autowired private MessageSource messageSource;

  @RequestMapping("/topage")
  public String toPage(Locale locale, Model model, HttpSession session) {
    System.out.println(locale);
    String welcomeinfo = messageSource.getMessage("welcomeInfo", null, locale);
    System.out.println(welcomeinfo);
    model.addAttribute("msg", "哈哈哈");
    return "login";
  }
}
