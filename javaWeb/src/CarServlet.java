import pojo.Book;
import pojo.Cart;
import pojo.CartItem;
import service.BookService;
import service.impl.BookServiceImpl;
import servlet.BaseServlet;
import util.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: dai.jf
 * @date: 2021/8/13 17:59
 * @description:
 */
@WebServlet(name = "CarServlet", value = "/CarServlet")
public class CarServlet extends BaseServlet {
  BookService bookService = new BookServiceImpl();

  /**
   * 添加商品至购物车
   *
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
  protected void addItem(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    // 获取请求参数id
    int id = ServletUtils.parseInt(request.getParameter("id"), 0);
    // 调用bookService的queryBookById(id)得到商品信息
    Book book = bookService.queryBookById(id);
    // 把图书信息转为carItem
    CartItem item = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());
    // 判断购物车是否为空，为空就创建
    Cart cart = (Cart) request.getSession().getAttribute("cart");
    if (cart == null) {
      cart = new Cart();
      request.getSession().setAttribute("cart", cart);
    }
    // 调用car.addItem()添加商品项
    cart.addItem(item);
    // 显示最后一个添加的商品[只能是session，重定向不支持reques t域共享数据]
    request.getSession().setAttribute("lastItem", item.getName());
    System.out.println(cart);
    // 重定向回该商品原来所在页
     response.sendRedirect(request.getHeader("Referer"));

    // 使用ajax返回购物车商品总数和最后一个添加的商品
//    Map<String, Object> map = new HashMap<>();
//    map.put("totalCount", cart.getTotalCount());
//    map.put("lastname", item.getName());
//
//    Gson gson = new Gson();
//    String json = gson.toJson(map);
//
//    response.getWriter().write(json);
  }
  /**
   * 删除商品
   *
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
  protected void deleteItem(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // 获取请求参数id
    int id = ServletUtils.parseInt(request.getParameter("id"), 0);
    // 获取购物车对象
    Cart cart = (Cart) request.getSession().getAttribute("cart");
    if (cart != null) {
      // 删除商品
      cart.deleteItem(id);
    }
    // 重定向回该商品原来所在页
    response.sendRedirect(request.getHeader("Referer"));
  }

  /**
   * 清空购物车
   *
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
  protected void clearItems(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // 获取购物车对象
    Cart cart = (Cart) request.getSession().getAttribute("cart");
    if (cart != null) {
      // 清空购物车
      cart.clear();
      // 重定向回该商品原来所在页
      response.sendRedirect(request.getHeader("Referer"));
    }
  }

  /**
   * 修改商品数量
   *
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
  protected void updateCount(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // 获取请求的参数 商品编号 、商品数量
    int id = ServletUtils.parseInt(request.getParameter("id"), 0);
    int count = ServletUtils.parseInt(request.getParameter("count"), 1);
    // 获取Cart购物车对象
    Cart cart = (Cart) request.getSession().getAttribute("cart");
    if (cart != null) {
      // 修改商品数量
      cart.updateCount(id, count);
      // 重定向回原来购物车展示页面
      response.sendRedirect(request.getHeader("Referer"));
    }
  }
}
