package com.dai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: dai.jf
 * @date: 2021/8/20 5:27
 * @description:
 */
@Controller
public class ParamController {

  /**
   * 获取请求参数<br>
   * 方式一: 直接给处理方法上写上和请求参数名相同的变量,
   *
   * <p>方式二: 用了@RequestParam("")就默认必须带参数
   *
   * <p>RequestParam("username") String param: 等同于 string param =request.getParameter("username");
   *
   * <p>required =[这个参数是否必须的] false,defaultValue = "空的"
   *
   * <p>@CookieValue:获得某个cookie的值
   *
   * <p>以前得到某个cookie的值: Cookie[] cookies = request.getCookies() for(Cookie c : cookies){
   * if(c.getName().equals("JSESSIONID"){ c.getValue(); } }
   *
   * @param param
   * @return
   */
  @RequestMapping("/param")
  public String getParam(
      @RequestParam(value = "username", required = false, defaultValue = "空的") String param,
      @CookieValue("JSESSIONID") String jsessionId) {
    System.out.println("JSESSIONID=" + jsessionId);
    System.out.println("你传过来的参数是" + param);
    return "success";
  }

  /**
   * String userAgent = request.getHeader("User-Agent");
   *
   * @param userAgent
   * @return
   */
  @RequestMapping("/header")
  public String header(@RequestHeader("user-Agent") String userAgent) {
    System.out.println("请求头中浏览器信息是" + userAgent);
    return "success";
  }
}
