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
import java.util.List;

/**
 * @author: dai.jf
 * @date: 2021/8/9 3:33
 * @description: value 处的/manager是加管理员权限
 */
@WebServlet(name = "BookServlet", value = "/manager/BookServlet")
public class BookServlet extends BaseServlet {

  BookService bookService = new BookServiceImpl();

  /**
   * 添加图书
   *
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
  protected void add(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // 1 获取请求参数,封装成bean对象
    Book book = ServletUtils.copyParamToBean(request.getParameterMap(), new Book());
    // 2 调用service 的 addBook()方法
    bookService.addBook(book);
    // 3 添加图书后重定向到图书页面的最后一页
    int pageCurrent = ServletUtils.parseInt(request.getParameter("pageCurrent"), 0);
    // 最后一页满了就自动加一页
    pageCurrent += 1;
    response.sendRedirect(
        request.getContextPath() + "/manager/BookServlet?action=page&pageCurrent=" + pageCurrent);
  }

  /**
   * 删除图书
   *
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
  protected void delete(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // 1 获取请求参数 id
    int id = ServletUtils.parseInt(request.getParameter("id"), 0);
    // 2 调用service 的 deleteBook()方法
    bookService.deleteBook(id);
    // 3 重定向到图书页面,删除时跳转到被删除的数据的当前页
    response.sendRedirect(
        request.getContextPath()
            + "/manager/BookServlet?action=page&pageCurrent="
            + request.getParameter("pageCurrent"));
  }

  /**
   * 获取要修改的图书，用于表单回显
   *
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
  protected void getBook(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // 1 获取请求参数 id
    int id = ServletUtils.parseInt(request.getParameter("id"), 0);
    // 2 调用service的 queryBookById()查询图书 返回一个book对象
    Book book = bookService.queryBookById(id);
    // 3 保存图书信息到request域中
    request.setAttribute("book", book);
    // 4 跳转至修改页面
    request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request, response);
  }

  /**
   * 修改图书内容并保存
   *
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
  protected void update(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // 1 获取请求参数,封装成bean对象
    Book book = ServletUtils.copyParamToBean(request.getParameterMap(), new Book());
    // 2 调用service的 updateBook() 修改图书信息
    bookService.updateBook(book);
    // 3 重定向到显示图书页面
    response.sendRedirect(
        request.getContextPath()
            + "/manager/BookServlet?action=page&pageCurrent="
            + request.getParameter("pageCurrent"));
  }

  /**
   * 显示所以图书
   *
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
  protected void list(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // 1 通过bookService查询全部图书 返回一个book对象
    List<Book> bookList = bookService.queryBooks();
    // 2 把全部图书保存到request域中
    request.setAttribute("bookList", bookList);
    // 3 请求转发到book_manger页面
    request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
  }

  /**
   * 分页功能
   *
   * @param request
   * @param response
   * @throws ServletException
   * @throws IOException
   */
  protected void page(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // 1 获取请求参数 pageCurrent 和 pageSize[但是这里没有用到，用的是默认值]
    int pageCurrent = ServletUtils.parseInt(request.getParameter("pageCurrent"), 1);
    int pageSize = ServletUtils.parseInt(request.getParameter("pageSize"), Page.PAGE_SIZE);
    // 2 调用service的page(pageCurrent, pageSize)方法返回一个page对象
    Page<Book> page = bookService.page(pageCurrent, pageSize);

    //设置分页跳转的url
    page.setUrl("manager/BookServlet?action=page");
    // 3保存数据到request域中
    request.setAttribute("page", page);
    // 4请求转发到book_manger页面
    request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
  }
}
