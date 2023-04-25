package com.dai.demo.config;

import com.dai.demo.entity.Color;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * @author dai.jf
 * @date 2021/11/3-9:48
 * @description:
 */
@Configuration
public class MyConfig {

  @Profile("prod")
  @Bean
  public Color red() {
    return new Color();
  }

  @Profile("test")
  @Bean
  public Color green() {
    return new Color();
  }
}
