package dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import util.JdbcUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/** created by : Dai time : 2021/8/6 1:26 description: 数据库中数据的通用CRUD操作
 * @author Dai*/
public abstract class BaseDAO {
  /** 使用queryRunner操作数据库 */
  QueryRunner queryRunner = new QueryRunner();

  Connection conn = null;

  /**
   * 通用的增删改操作
   *
   * @param sql 要执行的相应sql语句
   * @param args sql语句中相应参数
   * @return 若返回 0 表示执行失败，反之则返回受影响的行数
   */
  public int update(String sql, Object... args) {
    conn = JdbcUtils.getConn();
    // 不关闭数据库连接了，在事务提交或回滚的时候关闭,且一定要往外抛，好让其在连接中回滚事务，以下方法相同
    try {
      return queryRunner.update(conn, sql, args);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * 通用的查询方法【返回一条记录】
   *
   * @param type 执行查询后返回对象的类型
   * @param sql 执行的sql语句
   * @param args sql对应的参数
   * @param <T> 返回的类型的泛型
   * @return
   */
  public <T> T queryForOne(Class<T> type, String sql, Object... args) {
    conn = JdbcUtils.getConn();
    try {
      return queryRunner.query(conn, sql, new BeanHandler<T>(type), args);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * 通用的查询方法【返回一条记录】
   *
   * @param type 执行查询后返回对象的类型
   * @param sql 执行的sql语句
   * @param args sql对应的参数
   * @param <T> 返回的类型的泛型
   * @return
   */
  public <T> List<T> queryForList(Class<T> type, String sql, Object... args)  {
    conn = JdbcUtils.getConn();
    try {
      return queryRunner.query(conn, sql, new BeanListHandler<>(type), args);
    } catch (SQLException e) {
     throw new RuntimeException(e);
    }
  }

  /** 用于查询特殊值的通用的方法如：COUNT(*) */
  public Object queryForSingleValue(String sql, Object... args)  {
    Connection conn = JdbcUtils.getConn();
    try {
      return queryRunner.query(conn, sql, new ScalarHandler(), args);
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }
}
