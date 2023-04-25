package com.dai.hello.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author dai.jf
 * @date 2021/11/3-10:36
 * @description:
 */
@Component
@ConfigurationProperties("dai.hello")
public class HelloProperties {

  private String prefix;
  private String suffix;

  public HelloProperties() {
  }

  public HelloProperties(String prefix, String suffix) {
    this.prefix = prefix;
    this.suffix = suffix;
  }

  public String getPrefix() {
    return prefix;
  }

  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }

  public String getSuffix() {
    return suffix;
  }

  public void setSuffix(String suffix) {
    this.suffix = suffix;
  }
}
