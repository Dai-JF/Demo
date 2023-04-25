package com.dai.hello.auto;

import com.dai.hello.bean.HelloProperties;
import com.dai.hello.service.HelloService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author dai.jf
 * @date 2021/11/3-10:41
 * @description:
 */
@Configuration
@ConditionalOnMissingBean(HelloService.class)
@EnableConfigurationProperties(HelloProperties.class)
public class HelloServiceAutoConfiguration {

  public HelloService service() {
    HelloService service = new HelloService();
    return service;
  }
}
