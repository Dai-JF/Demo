package com.dai;

import com.dai.dao.KeyDao;
import com.dai.dao.TableDao;
import com.dai.entity.Key;
import com.dai.entity.Table;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author: dai.jf
 * @date: 2021/8/25 18:36
 * @description:
 */
public class ResultMapTest {
  SqlSessionFactory sqlSessionFactory;

  @Before
  public void init() throws IOException {
    // 根据全局配置文件得到sqlSessionFactory
    InputStream inputStream = Resources.getResourceAsStream("mybatisConfig.xml");
    sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
  }

  /**
   * description: mybatisn默认自定封装结果集 <br>
   * 默认规则： <br>
   * 1、按照属性名和表的列名一一对应 【不区分大小写】<br>
   * 2、不是一一对应 <br>
   * 2、1、开启驼峰命名规则（前提必须属性名满足驼峰命名规则aaa_bbb---->aaaBbb） <br>
   * 2、2、给属性名起别名
   *
   * @author: dai.jf
   * @date: 2021/8/25 18:42
   * @return: void
   */
  @Test
  public void test1() {
    SqlSession session = sqlSessionFactory.openSession();
    TableDao tableDao = session.getMapper(TableDao.class);
    Table table = tableDao.getTable(1);
    System.out.println(table);
  }

  /**
   * description:联合查询-->1、级联属性封装查询的结果
   *
   * @author: dai.jf
   * @date: 2021/8/25 20:25
   * @return: null
   */
  @Test
  public void test2() {
    SqlSession session = sqlSessionFactory.openSession();
    KeyDao keyDao = session.getMapper(KeyDao.class);
    Key key = keyDao.getById(1);
    System.out.println(key);
  }
}
