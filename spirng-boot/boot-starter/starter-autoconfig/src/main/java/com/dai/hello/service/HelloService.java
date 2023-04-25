package com.dai.hello.service;

import com.dai.hello.bean.HelloProperties;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author dai.jf
 * @date 2021/11/3-10:34
 * @description: 默认不放在容器中
 */
public class HelloService {

  @Autowired
  HelloProperties properties;

  public String sayHello(String name) {
    return properties.getPrefix() + ":" + name + "--->" + properties.getSuffix();
  }

}
