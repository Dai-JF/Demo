package dao.impl;

import dao.BaseDAO;
import dao.OrderDAO;
import pojo.Order;

/**
 * @author: dai.jf
 * @date: 2021/8/15 0:25
 * @description:
 */
public class OrderDAOImpl extends BaseDAO implements OrderDAO {
  String sql = "";

  @Override
  public int saveOrder(Order order) {
    String sql =
        "insert into t_order(`order_id`,`create_time`,`price`,`status`,`user_id`) values(?,?,?,?,?)";
    return update(
        sql,
        order.getOrderId(),
        order.getCreateTime(),
        order.getPrice(),
        order.getStatus(),
        order.getUserId());
  }

}
