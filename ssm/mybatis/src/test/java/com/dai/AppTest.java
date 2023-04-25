package com.dai;

import com.dai.dao.UserDao;
import com.dai.entity.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/** Unit test for simple App. */
public class AppTest {
  SqlSessionFactory sqlSessionFactory;

  /** Before：在测试方法执行之前执行 */
  @Before
  public void init() throws IOException {
    // 根据全局配置文件得到sqlSessionFactory
    InputStream inputStream = Resources.getResourceAsStream("mybatisConfig.xml");
    sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
  }

  @Test
  public void query() {
    // 获取和数据库的一次会话：getConnection()
    try (SqlSession session = sqlSessionFactory.openSession()) {
      // 使用SqlSession操作数据库，获取dao接口实现[接口映射器]
      UserDao userDao = session.getMapper(UserDao.class);
      User user = userDao.getUserById(1);
      System.out.println(user);
    }
  }

  @Test
  public void queryList() {
    try (SqlSession session = sqlSessionFactory.openSession()) {
      UserDao userDao = session.getMapper(UserDao.class);
      List<User> all = userDao.getAll();
      System.out.println(all);
    }
  }

  @Test
  public void queryMap() {
    try (SqlSession session = sqlSessionFactory.openSession()) {
      UserDao userDao = session.getMapper(UserDao.class);
      Map<String, Object> map = userDao.returnMap(10);
      System.out.println(map);
    }
  }

  @Test
  public void queryMaps() {
    try (SqlSession session = sqlSessionFactory.openSession()) {
      UserDao userDao = session.getMapper(UserDao.class);
      Map<Integer, User> maps = userDao.maps();
      System.out.println(maps);
    }
  }

  @Test
  public void insert() {
    SqlSession session = sqlSessionFactory.openSession();
    try {
      UserDao userDao = session.getMapper(UserDao.class);
      User user = new User(null, "戴俊峰", "root", "tongLu", "1806582781");
      int i = userDao.addUser(user);
      System.out.println(i);
      System.out.println("获取到的id--->" + user.getId());
    } finally {
      // DEBUG 08-24 23:28:21,671 Setting autocommit to false
      // 需手动提交
      session.commit();
      session.close();
    }
  }

  @Test
  public void update() {
    // true: 设置自动提交
    try (SqlSession session = sqlSessionFactory.openSession(true)) {
      UserDao userDao = session.getMapper(UserDao.class);
      int i = userDao.updateUser(new User(8, "戴俊峰", "dai", "tongLu", "1806582781"));
      System.out.println(i);
    }
  }

  /**
   * @Description: @Author: dai.jf @Date: 2021/8/25 1:27
   *
   * <p>* @return: void
   */
  @Test
  public void delete() {
    // true: 设置自动提交
    try (SqlSession session = sqlSessionFactory.openSession(true)) {
      UserDao userDao = session.getMapper(UserDao.class);
      int i = userDao.deleteUser(5);
      System.out.println(i);
    }
  }
}
