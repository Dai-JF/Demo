package dao.impl;

import dao.BaseDAO;
import dao.BookDAO;
import pojo.Book;

import java.util.List;

/**
 * created by : Dai 2021/8/8 13:26 description:
 *
 * @author Dai
 */
public class BookDAOImpl extends BaseDAO implements BookDAO {

  String sql = "";

  @Override
  public int addBook(Book book) {
    sql = "insert into t_book(name,author,price,sales,stock,img_path)values(?,?,?,?,?,?)";
    return update(
        sql,
        book.getName(),
        book.getAuthor(),
        book.getPrice(),
        book.getSales(),
        book.getStock(),
        book.getImgPath());
  }

  @Override
  public int deleteBookById(Integer id) {
    sql = "delete from t_book where id = ?";
    return update(sql, id);
  }

  @Override
  public int updateBook(Book book) {
    sql = "update t_book set name=?,author=?,price=?,sales=?,stock=?,img_path=? where id = ?";
    return update(
        sql,
        book.getName(),
        book.getAuthor(),
        book.getPrice(),
        book.getSales(),
        book.getStock(),
        book.getImgPath(),
        book.getId());
  }

  @Override
  public Book queryBookById(Integer id) {
    sql = "select id, name,author,price,sales,stock,img_path imgPath from t_book where id=?";
    return queryForOne(Book.class, sql, id);
  }

  @Override
  public List<Book> queryBooks() {
    sql = "select id, name,author,price,sales,stock,img_path imgPath from t_book";
    return queryForList(Book.class, sql);
  }

  @Override
  public Integer queryForPageTotalCount() {
    sql = "select count(*) from t_book";
    Number count = (Number) queryForSingleValue(sql);
    return count.intValue();
  }

  @Override
  public List<Book> queryForItems(int begin, int pageSize) {
    sql = "select id, name,author,price,sales,stock,img_path imgPath from t_book limit ?,? ";
    return queryForList(Book.class, sql, begin, pageSize);
  }

  @Override
  public List<Book> queryForItemsByPrice(int begin, int pageSize, int minPrice, int maxPrice) {
    sql =
        "select id, name,author,price,sales,stock,img_path imgPath from t_book where price between ? and ?  limit ?,? ";
    return queryForList(Book.class, sql, minPrice, maxPrice, begin, pageSize);
  }

  @Override
  public Integer queryForPageTotalCountByPrice(int minPrice, int maxPrice) {
    sql = "select count(*) from t_book where price between ? and ?";
    Number count = (Number) queryForSingleValue(sql, minPrice, maxPrice);
    return count.intValue();
  }
}
