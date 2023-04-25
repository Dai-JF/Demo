package com.dai.demo.entity;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * @author dai.jf
 * @date 2021/11/3-9:42
 * @description:
 */
@Data
@Component
@ConfigurationProperties("person")
@Profile("prod")
public class Boss implements Person{
  private String name;
  private Integer age;
}
