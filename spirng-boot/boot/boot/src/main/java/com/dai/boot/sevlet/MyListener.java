package com.dai.boot.sevlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import lombok.extern.slf4j.Slf4j;

/**
 * @author dai.jf
 * @date 2021/10/29-20:47
 * @description:
 */
@Slf4j
@WebListener
public class MyListener implements ServletContextListener {

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    log.info("监听到项目初始化完成");
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    log.info("监听到项目初始化销毁");
  }
}
