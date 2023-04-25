package service;

import pojo.Book;
import pojo.Page;

import java.util.List;

/** created by : Dai 2021/8/8 13:56 description: */
public interface BookService {

  void addBook(Book book);

  void deleteBook(Integer id);

  void updateBook(Book book);

  Book queryBookById(Integer id);

  List<Book> queryBooks();

  Page<Book> page(int pageCurrent, int pageSize);

  Page<Book> pageByPrice(int pageCurrent, int pageSize, int minPrice, int maxPrice);
}
