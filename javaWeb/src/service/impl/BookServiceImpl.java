package service.impl;

import dao.BookDAO;
import dao.impl.BookDAOImpl;
import pojo.Book;
import pojo.Page;
import service.BookService;

import java.util.List;

/**
 * @date : 2021/8/8 13:59
 * @description:
 * @author Dai
 */
public class BookServiceImpl implements BookService {

  BookDAO bookDAO = new BookDAOImpl();

  @Override
  public void addBook(Book book) {
    bookDAO.addBook(book);
  }

  @Override
  public void deleteBook(Integer id) {
    bookDAO.deleteBookById(id);
  }

  @Override
  public void updateBook(Book book) {
    bookDAO.updateBook(book);
  }

  @Override
  public Book queryBookById(Integer id) {
    return bookDAO.queryBookById(id);
  }

  @Override
  public List<Book> queryBooks() {
    return bookDAO.queryBooks();
  }

  @Override
  public Page<Book> page(int pageCurrent, int pageSize) {
    Page<Book> page = new Page<>();
    // 给各个属性赋值

    // 设置每页显示数量
    page.setPageSize(pageSize);
    // 求总记录数
    Integer pageTotalCount = bookDAO.queryForPageTotalCount();
    // 设置总记录数
    page.setPageTotalCount(pageTotalCount);
    // 求总页码
    int pageTotal = pageTotalCount / pageSize;
    if (pageTotalCount % pageSize > 0) {
      // 除不尽说明还有多的记录，放到新的一页
      pageTotal += 1;
    }
    // 设置总页码
    page.setPageTotal(pageTotal);

    /*
    数据边界的有效检查
     */
    if (pageCurrent < 1) {
      pageCurrent = 1;
    }
    if (pageCurrent > pageTotal) {
      pageCurrent = pageTotal;
    }
    // 设置当前页
    page.setPageCurrent(pageCurrent);
    // 求当前页数据的开始索引
    int begin = (page.getPageCurrent() - 1) * pageSize;
    // 求当前页数据
    List<Book> items = bookDAO.queryForItems(begin, pageSize);
    // 设置当前页数据
    page.setItems(items);

    return page;
  }

  @Override
  public Page<Book> pageByPrice(int pageCurrent, int pageSize, int minPrice, int maxPrice) {
    Page<Book> page = new Page<>();
    // 给各个属性赋值

    // 设置每页显示数量
    page.setPageSize(pageSize);
    // 根据价格区间求总记录数
    Integer pageTotalCount = bookDAO.queryForPageTotalCountByPrice(minPrice, maxPrice);
    // 设置总记录数
    page.setPageTotalCount(pageTotalCount);
    // 求总页码
    int pageTotal = pageTotalCount / pageSize;
    if (pageTotalCount % pageSize > 0) {
      // 除不尽说明还有多的记录，放到新的一页
      pageTotal += 1;
    }
    // 设置总页码
    page.setPageTotal(pageTotal);

    /*
    数据边界的有效检查
     */
    if (pageCurrent < 1) {
      pageCurrent = 1;
    }
    if (pageCurrent > pageTotal) {
      pageCurrent = pageTotal;
    }
    // 设置当前页
    page.setPageCurrent(pageCurrent);
    // 求当前页数据的开始索引
    int begin = (page.getPageCurrent() - 1) * pageSize;
    // 求当前页数据
    List<Book> items = bookDAO.queryForItemsByPrice(begin, pageSize,minPrice,maxPrice);
    // 设置当前页数据
    page.setItems(items);
    return page;
  }
}
