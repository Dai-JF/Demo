package util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * time : 2021/8/6 1:13 description: 数据库的连接与关闭
 *
 * @author Dai
 */
public class JdbcUtils {

  /** druid数据库连接池 */
  private static DataSource source;

  /** 设置每次操作都是同一次连接 */
  private static ThreadLocal<Connection> local = new ThreadLocal<>();

  static {
    try {
      Properties properties = new Properties();
      // 读取 jdbc.properties属性配置文件
      InputStream is = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
      // 从流中加载数据
      properties.load(is);
      // 创建 数据库连接池
      source = DruidDataSourceFactory.createDataSource(properties);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /** 数据库连接 */
  public static Connection getConn() {
    Connection conn = local.get();
    if (conn==null){
      try {
        // 从数据库连接池获得连接
        conn = source.getConnection();
        // 将所以连接保存到threadLocal对象中，确保每次连接都是同一个连接
        local.set(conn);
        // 设置为手动管理事务
        conn.setAutoCommit(false);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return conn;
  }

  /** 提交事务并数据库关闭 */
  public static void commitAndClose() {
    Connection connection = local.get();
    // 不为空说明之前使用过，连接过数据库
    if (connection != null) {
      try {
        connection.commit();
      } catch (SQLException e) {
        e.printStackTrace();
      } finally {
        try {
          connection.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
    // 一定要执行remove，不然出错，【因为tomcat底层使用了下线程池】
    local.remove();
  }

  /** 回滚事务并数据库关闭 */
  public static void rollbackAndClose() {
    Connection connection = local.get();
    // 不为空说明之前使用过，连接过数据库
    if (connection != null) {
      try {
        connection.rollback();
      } catch (SQLException e) {
        e.printStackTrace();
      } finally {
        try {
          connection.close();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }
    // 一定要执行remove，不然出错，【因为tomcat底层使用了下线程池】
    local.remove();
  }
}
