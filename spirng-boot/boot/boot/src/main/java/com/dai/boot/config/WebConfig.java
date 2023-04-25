package com.dai.boot.config;

import com.dai.boot.interceptor.LoginInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author dai.jf
 * @date 2021/10/27-11:12
 * @description: 拦截器注册到容器中
 */
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new LoginInterceptor())
        //所有请求都被拦截包括静态资源
        .addPathPatterns("/**")
        //放行的请求
        .excludePathPatterns("/", "/login", "/css/**", "/fonts/**", "/images/**",
            "/js/**");
  }
}