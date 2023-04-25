package dao;

import pojo.OrderItem;

/**
 * @author: dai.jf
 * @date: 2021/8/15 0:31
 * @description:
 */
public interface OrderItemDAO {
  int saveOrderItems(OrderItem orderItem);
}
