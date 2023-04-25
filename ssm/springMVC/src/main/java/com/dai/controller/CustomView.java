package com.dai.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author: dai.jf
 * @date: 2021/8/22 9:50
 * @description: 自定义视图对象
 */
@Controller
public class CustomView implements View {
  /**
   * 返回的内容类型
   *
   * @return
   */
  @Override
  public String getContentType() {
    return "text/html";
  }

  @Override
  public void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response)
      throws Exception {
    response.setContentType("text/html");
    System.out.println("之前保存的数据" + model);
    response.getWriter().write("<h1>abcde</h1>");
  }
}
