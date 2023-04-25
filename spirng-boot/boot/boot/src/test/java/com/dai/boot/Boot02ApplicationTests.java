package com.dai.boot;

import com.dai.boot.entity.User;
import com.dai.boot.mapper.UserMapper;
import javax.sql.DataSource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

@Slf4j
@SpringBootTest
class Boot02ApplicationTests {

  @Autowired
  JdbcTemplate template;

  @Autowired
  DataSource source;

  @Autowired
  UserMapper userMapper;

  @Test
  void contextLoads() {
    Long object = template.queryForObject("select count(*) from car", Long.class);
    log.info("记录总数：{}", object);

    log.info("数据源的类型：{}", source.getClass());
  }

  @Test
  void testUserMapper() {
    User user = userMapper.selectById(1);
    log.info("user信息:{}", user);
  }
}
