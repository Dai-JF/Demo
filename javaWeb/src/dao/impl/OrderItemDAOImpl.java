package dao.impl;

import dao.BaseDAO;
import dao.OrderItemDAO;
import pojo.OrderItem;

/**
 * @author: dai.jf
 * @date: 2021/8/15 0:32
 * @description:
 */
public class OrderItemDAOImpl extends BaseDAO implements OrderItemDAO {
  String sql = "";

  @Override
  public int saveOrderItems(OrderItem orderItem) {
    String sql =
        "insert into t_order_item(`name`,`count`,`price`,`total_price`,`order_id`) values(?,?,?,?,?)";
    return update(
        sql,
        orderItem.getName(),
        orderItem.getCount(),
        orderItem.getPrice(),
        orderItem.getTotalMoney(),
        orderItem.getOrderId());
  }
}
