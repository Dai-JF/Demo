package com.dai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author: dai.jf
 * @date: 2021/8/19 23:17
 * @description:
 *     <p>运行流程：
 *     <p>1 客户端点击连接会发送http://localhost:8080/springMVC/hello 请求
 *     <p>2 来到tomcat服务器
 *     <p>3 springMVC的前端控制器接收请求
 *     <p>4 看请求地址和 @RequestMapping()的哪个标注能匹配，来找到到底和哪个类的哪个方法匹配
 *     <p>5 前端控制器找到目标处理器类和目标方法，直接利用反射执行方法
 *     <p>6 方法执行后，会有个返回值 SpringMVC认为这个返回值就是要去的页面地址
 *     <p>7 拿到返回值后，用视图解析器进行拼串得到完整地址
 *     <p>8 得到地址，前端控制器帮我们转发到页面
 */
@Controller
public class FirstController {

  /**
   * /斜杠：表示从当前项目下开始。处理当前项目下的 /hello请求【可以省，最好加上】
   *
   * <p>@RequestMapping:告诉springMVC这个方法是用来处理什么请求
   *
   * @return
   */
  @RequestMapping("/hello")
  public String firstController() {
    return "success";
  }

  @RequestMapping("/hi")
  public String view() {
    // 相对路径
    return "../../hi";
  }

  /**
   * forward:[转发] 到一个页面
   *
   * <p>/hi.jsp: 转发到当前项目下的hi页面【/一定要加上，不加就是相对路径，容易出错】
   *
   * <p>且forward:/hi.jsp不会自动拼串
   */
  @RequestMapping("/handle01")
  public String handle01() {
    System.out.println("handle01");
    return "forward:/hi.jsp";
  }

  /**
   * 两次转发
   *
   * @return
   */
  @RequestMapping("/handle02")
  public String handle02() {
    System.out.println("handle02");
    return "forward:/handle01";
  }

  /**
   * redirect:[重定向]到一个页面
   *
   * <p>转发 forward：转发路径
   *
   * <p>重定向 redirect：重定向路径
   *
   * <p>/hi.jsp就表示从当前项目下开始，springMVC会自动加上应用上下文路径【就是自动加项目名】
   *
   * <p>原生servlet重定向：要base标签加上项目名才行
   */
  @RequestMapping("handle03")
  public String handle03() {
    System.out.println("handle03");
    return "redirect:/hi.jsp";
  }

  /**
   * 两次重定向
   *
   * <p>有前缀的转发和重定向，视图解析器不会自动拼串
   */
  @RequestMapping("handle04")
  public String handle04() {
    System.out.println("handle04");
    return "redirect:/handle03";
  }
}
