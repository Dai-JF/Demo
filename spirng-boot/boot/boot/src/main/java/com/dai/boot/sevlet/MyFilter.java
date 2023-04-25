package com.dai.boot.sevlet;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * @author dai.jf
 * @date 2021/10/29-20:44
 * @description:
 */
@Slf4j
//@WebFilter(urlPatterns = {"/css/*","images/*"})
public class MyFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
    log.info("filter初始化完成");
  }

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
      FilterChain filterChain) throws IOException, ServletException {
    log.info("filter工作中。。");
    filterChain.doFilter(servletRequest,servletResponse);
  }

  @Override
  public void destroy() {
    log.info("filter销毁完成");
  }
}
