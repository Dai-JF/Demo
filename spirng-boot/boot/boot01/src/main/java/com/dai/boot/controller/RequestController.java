package com.dai.boot.controller;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author dai.jf
 * @date 2021/10/16-20:11
 * @description:
 */
@Controller
public class RequestController {

  @GetMapping("/goto")
  public String gotoPage(HttpServletRequest request) {
    request.setAttribute("msg", "成功了！！！");
    request.setAttribute("code", 200);
    //转发到success请求
    return "forward:/success";
  }

  @ResponseBody
  @GetMapping("/success")
  public Map handleSuccess(@RequestAttribute("msg") String msg, @RequestAttribute("code") Integer code) {
    HashMap<Object, Object> map = new HashMap<>();
    map.put("msg", msg);
    map.put("code", code);
    return map;
  }
}
