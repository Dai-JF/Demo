package com.dai.boot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 * @author dai
 */
@MapperScan("com.dai.boot.mapper")//不用每个mapper文件都写mapper注解
@ServletComponentScan(basePackages = "com.dai.boot")
@SpringBootApplication
public class Boot02Application {

  public static void main(String[] args) {
    SpringApplication.run(Boot02Application.class, args);
  }

}
