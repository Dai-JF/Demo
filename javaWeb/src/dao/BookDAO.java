package dao;

import pojo.Book;

import java.util.List;

/**
 * created by : Dai 2021/8/8 4:03 description:
 *
 * @author Dai
 */
public interface BookDAO {
  /** 添加图书 */
  int addBook(Book book);

  /** 根据id删除图书 */
  int deleteBookById(Integer id);

  /** 修改图书 */
  int updateBook(Book book);

  /** 根据id查找图书 */
  Book queryBookById(Integer id);

  /** 查找全部图书 */
  List<Book> queryBooks();

  Integer queryForPageTotalCount();

  List<Book> queryForItems(int begin, int pageSize);

  List<Book> queryForItemsByPrice(int begin, int pageSize, int minPrice, int maxPrice);

  Integer queryForPageTotalCountByPrice(int minPrice, int maxPrice);
}
