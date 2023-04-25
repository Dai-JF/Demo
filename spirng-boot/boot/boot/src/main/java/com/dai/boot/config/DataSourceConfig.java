package com.dai.boot.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * @author dai.jf
 * @date 2021/10/30-14:09
 * @description:
 */
@Deprecated
//@Configuration
public class DataSourceConfig {

  @Bean
  @ConfigurationProperties("spring.datasource")
  public DataSource dataSource() throws SQLException {
    DruidDataSource source = new DruidDataSource();
    //加入监控功能
    source.setFilters("stat");
    return source;
  }


  /**
   * Description: 配置druid的监控页
   *
   * @param
   * @return ServletRegistrationBean
   * @auther dai.jf
   * @date 2021/10/30 - 14:24
   */
  @Bean
  public ServletRegistrationBean statViewServlet() {
    StatViewServlet viewServlet = new StatViewServlet();
    ServletRegistrationBean<StatViewServlet> bean = new ServletRegistrationBean<>(
        viewServlet, "/druid/*");
    return bean;
  }
}
