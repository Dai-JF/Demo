package com.djf.proxy;

import org.springframework.stereotype.Component;

/**
 * @author: dai.jf
 * @date: 2021/8/18 22:17
 * @description: 计算器核方法
 *     <p>需求:打印日志
 */
@Component
public class Cal implements Calculate {

  @Override
  public int add(int i, int j) {
    return i + j;
  }
}
