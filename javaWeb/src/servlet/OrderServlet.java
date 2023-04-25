package servlet;

import pojo.Cart;
import pojo.User;
import service.OrderService;
import service.impl.OrderServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: dai.jf
 * @date: 2021/8/15 1:04
 * @description:
 */
@WebServlet(name = "OrderServlet", value = "/OrderServlet")
public class OrderServlet extends BaseServlet {

  OrderService orderService = new OrderServiceImpl();

  protected void createOrder(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // 先获取Cart购物车对象
    Cart cart = (Cart) request.getSession().getAttribute("cart");
    // 获取Userid
    User loginUser = (User) request.getSession().getAttribute("user");
    if (loginUser == null) {
      request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
      return;
    }
    Integer userId = loginUser.getId();
    // 调用orderService.createOrder(Cart,Userid);生成订单
    String orderId = orderService.createOrder(cart, userId);

    request.getSession().setAttribute("orderId", orderId);

    response.sendRedirect(request.getContextPath() + "/pages/cart/checkout.jsp");
  }
}
