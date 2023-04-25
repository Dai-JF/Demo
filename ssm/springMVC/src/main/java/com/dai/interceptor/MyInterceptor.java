package com.dai.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: dai.jf
 * @date: 2021/8/24 3:49
 * @description:
 */
public class MyInterceptor implements HandlerInterceptor {
  /**
   * 目标方法运行前运行
   *
   * @param request
   * @param response
   * @param handler
   * @return false：不放行
   * @throws Exception
   */
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    System.out.println("preHandle...");
    return true;
  }

  /**
   * 目标方法运行后运行
   *
   * @param request
   * @param response
   * @param handler
   * @param modelAndView
   * @throws Exception
   */
  @Override
  public void postHandle(
      HttpServletRequest request,
      HttpServletResponse response,
      Object handler,
      ModelAndView modelAndView)
      throws Exception {
    System.out.println("postHandle...");
  }

  /**
   * 来到页面【资源响应】后运行
   *
   * @param request
   * @param response
   * @param handler
   * @param ex
   * @throws Exception
   */
  @Override
  public void afterCompletion(
      HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
      throws Exception {
    System.out.println("afterCompletion...");
  }
}
