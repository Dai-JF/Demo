package com.dai.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author: dai.jf
 * @date: 2021/8/24 13:32
 * @description: 处理异常@ControllerAdvice
 */
@ControllerAdvice
public class DoExceptionController {

  /**
   * 要携带异常信息，参数位置不能写model 返回modelAndView就行
   *
   * @param e
   * @return
   */
  @ExceptionHandler(NullPointerException.class)
  public ModelAndView handle01(Exception e) {
    System.out.println(e);
    return new ModelAndView("errorPage");
  }
}
