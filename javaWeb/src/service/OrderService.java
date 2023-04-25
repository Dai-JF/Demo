package service;

import pojo.Cart;

/**
 * @author: dai.jf
 * @date: 2021/8/15 0:38
 * @description:
 */
public interface OrderService {
  String createOrder(Cart cart, Integer userId);
}
