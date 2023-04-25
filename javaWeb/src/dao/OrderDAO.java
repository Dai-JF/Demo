package dao;

import pojo.Order;

/**
 * (Order)表数据库访问层
 *
 * @author dai.jf
 * @since 2021-08-15 00:13:06
 */
public interface OrderDAO {
  int saveOrder(Order order);
}
