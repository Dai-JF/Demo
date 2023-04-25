package com.dai.boot.excrption;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * @author dai.jf
 * @date 2021/10/29-16:29
 * @description: 处理整个web controller的异常
 */
@Slf4j
@ControllerAdvice
public class GlobalException {

  @ExceptionHandler({NullPointerException.class, ClassNotFoundException.class})
  public String handler(Exception e) {
    log.error("异常是:{}", e);
    return "login";
  }

}
