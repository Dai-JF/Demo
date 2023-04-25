package com.dai.controller;

import com.dai.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author: dai.jf
 * @date: 2021/8/20 8:36
 * @description: 给session放数据 一般用原生API
 *     <p>当我们的传入参数是一个POJO[普通实体类]
 *     <p>springMVC会自动帮我们进行赋值 1)将pojo的每个属性从request参数中尝试获取出来,并封装 2)还能级联封装 :属性的属性
 */
@Controller
public class DataController {
  @RequestMapping("/addUser")
  public String addUser(User user) {
    System.out.println(user);
    return "success";
  }

  /**
   * 1)参数可以直接写原生API
   *
   * @param session
   * @param request
   * @return
   */
  @RequestMapping("/test")
  public String api(HttpSession session, HttpServletRequest request) {
    session.setAttribute("sessionKey", "我是sessionValue");
    request.setAttribute("requestKey", "我是requestValue");
    return "success";
  }

  /**
   * 2)除了西写原生API传参数放在域中 <br>
   * 还有:map, model modelMap,且三种方式的数据都存在request域中<br>
   * 三种方式其实都是class org.springframework.validation.support.BindingAwareModelMap在工作
   */
  @RequestMapping("/map")
  public String mapTest(Map map) {
    map.put("msg", "hi");
    System.out.println(map.getClass());
    return "success";
  }

  @RequestMapping("/model")
  public String modelTest(Model model) {
    model.addAttribute("msg", "hello");
    System.out.println(model.getClass());

    return "success";
  }

  @RequestMapping("/modelMap")
  public String modelMapTest(ModelMap modelMap) {
    modelMap.addAttribute("msg", "halo");
    System.out.println(modelMap.getClass());
    return "success";
  }

  /**
   * 3)modelAndView 数据也是默认在request域
   *
   * @return
   */
  @RequestMapping("/mv")
  public ModelAndView mv() {
    // success:视图名,将要去的页面
    ModelAndView mv = new ModelAndView("success");
    //ModelAndView mv = new ModelAndView();
    //mv.setViewName("success");
    mv.addObject("msg", "你好");
    return mv;
  }
}
