package com.dai.boot.controller;

import com.dai.boot.entity.User;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

/**
 * @author dai.jf
 * @date 2021/10/27-9:19
 * @description:
 */
@Controller
public class LoginController {

  @Autowired
  JdbcTemplate template;

  @GetMapping("/sql")
  @ResponseBody
  public String query() {
    Long object = template.queryForObject("select count(*) from car", Long.class);
    return object.toString();
  }

  @GetMapping({"/", "/login"})
  public String toPage() {
    return "login";
  }

  @PostMapping("login")
  public String main(User user, HttpSession session, Model model) {
    if (!StringUtils.isEmpty(user.getUsername()) && "a".equals(user.getPassword())) {
      session.setAttribute("loginUser", user);
      //重定向，解决表单重复提交
      return "redirect:main.html";
    } else {
      model.addAttribute("msg", "账号密码错误");
      return "login";
    }
  }

  @GetMapping("/main.html")
  public String toMain(HttpSession session, Model model) {
    //一般写在拦截器中
    Object loginUser = session.getAttribute("loginUser");
    if (loginUser != null) {
      return "main";
    } else {
      model.addAttribute("msg", "请先登录");
      return "login";
    }
  }
}
