package com.dai.controller;

import org.springframework.core.Ordered;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

/**
 * @author: dai.jf
 * @date: 2021/8/22 10:25
 * @description:
 */
public class MyViewResolver implements ViewResolver, Ordered {
  private Integer order;

  @Override
  public int getOrder() {
    return 0;
  }

  /** 改变视图解析器的优先级 */
  public void setOrder(Integer order) {
    this.order = order;
  }

  @Override
  public View resolveViewName(String viewName, Locale locale) throws Exception {
    // 根据视图名返回视图对象
    /** custom:/gaoqing custom:/dama forward:/login.jsp */
    if (viewName.startsWith("custom:")) {
      return new CustomView();
    } else {
      // 如果不能处理返回null即可
      return null;
    }
  }
}
