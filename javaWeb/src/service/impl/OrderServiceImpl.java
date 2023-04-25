package service.impl;

import dao.BookDAO;
import dao.OrderDAO;
import dao.OrderItemDAO;
import dao.impl.BookDAOImpl;
import dao.impl.OrderDAOImpl;
import dao.impl.OrderItemDAOImpl;
import pojo.*;
import service.OrderService;

import java.util.Date;
import java.util.Map;

/**
 * @author: dai.jf
 * @date: 2021/8/15 0:39
 * @description:
 */
public class OrderServiceImpl implements OrderService {

  OrderDAO orderDAO = new OrderDAOImpl();
  OrderItemDAO itemDAO = new OrderItemDAOImpl();
  BookDAO bookDAO = new BookDAOImpl();

  @Override
  public String createOrder(Cart cart, Integer userId) {

    // 加上时间戳和用户id，生成唯一的订单号
    String orderId = System.currentTimeMillis() + "" + userId;
    // 创建一个订单对象
    Order order = new Order(orderId, new Date(), cart.getTotalPrice(), 0, userId);

    // 保存订单
    orderDAO.saveOrder(order);


    //模拟网络错误
    //int i = 12/0;

    // 遍历购物车每一个商品的商品项
    for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
      // 获取每一个购物车的商品项
      CartItem cartItem = entry.getValue();
      // 转为每一个订单项
      OrderItem orderItem =
          new OrderItem(
              null,
              cartItem.getName(),
              cartItem.getPrice(),
              cartItem.getTotalPrice(),
              cartItem.getCount(),
              orderId);
      // 保存订单项到数据库
      itemDAO.saveOrderItems(orderItem);
      // 更新库存和销量
      Book book = bookDAO.queryBookById(cartItem.getId());
      book.setSales(book.getSales() + cartItem.getCount());
      book.setStock(book.getStock() - cartItem.getCount());
      bookDAO.updateBook(book);
    }

    // 清空购物车
    cart.clear();
    return orderId;
  }
}
