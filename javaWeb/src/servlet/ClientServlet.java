package servlet;

import pojo.Book;
import pojo.Page;
import service.BookService;
import service.impl.BookServiceImpl;
import util.ServletUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author: dai.jf
 * @date: 2021/8/10 13:37
 * @description: 前台分页
 */
@WebServlet(name = "ClientServlet", value = "/client/ClientServlet")
public class ClientServlet extends BaseServlet {

  BookService bookService = new BookServiceImpl();
  /**
   * 前台分页功能
   *
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
  protected void page(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // 1 获取请求参数 pageCurrent 和 pageSize[这里没有用到，用的是默认值]
    int pageCurrent = ServletUtils.parseInt(request.getParameter("pageCurrent"), 1);
    int pageSize = ServletUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
    // 2 调用service的page(pageCurrent, pageSize)方法返回一个page对象
    Page<Book> page = bookService.page(pageCurrent, pageSize);

    page.setUrl("client/ClientServlet?action=page");
    // 3保存数据到request域中
    request.setAttribute("page", page);
    // 4请求转发到book_manger页面
    request.getRequestDispatcher("/pages/client/index.jsp").forward(request, response);
  }

  /**
   * 按照价格区间搜索并分页
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
  protected void pageByPrice(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // 1 获取请求参数    pageCurrent 和 pageSize[这里没有用到，用的是默认值]
    int pageCurrent = ServletUtils.parseInt(request.getParameter("pageCurrent"), 1);
    int pageSize = ServletUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
    int minPrice = ServletUtils.parseInt(request.getParameter("min"), 0);
    int maxPrice = ServletUtils.parseInt(request.getParameter("max"), Integer.MAX_VALUE);

    // 2 调用service的page(pageCurrent, pageSize)方法返回一个page对象
    Page<Book> page = bookService.pageByPrice(pageCurrent, pageSize, minPrice, maxPrice);

    StringBuilder sb = new StringBuilder("client/ClientServlet?action=pageByPrice");

    // 如果有最小价格的参数,追加到分页条的地址参数中
    if (request.getParameter("min") != null) {
      sb.append("&min=").append(request.getParameter("min"));
    }
    // 如果有最大价格的参数,追加到分页条的地址参数中
    if (request.getParameter("max") != null) {
      sb.append("&max=").append(request.getParameter("max"));
      page.setUrl(sb.toString());
      // 3保存数据到request域中
      request.setAttribute("page", page);
      // 4请求转发到book_manger页面
      request.getRequestDispatcher("/pages/client/index.jsp").forward(request, response);
    }
  }
}
