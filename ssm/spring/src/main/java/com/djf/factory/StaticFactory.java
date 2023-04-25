package com.djf.factory;

import com.djf.spring.Book;

/**
 * @author: dai.jf
 * @date: 2021/8/18 13:15
 * @description: 静态工厂：不需要new工厂对象，直接调方法
 */
public class StaticFactory {

  public static Book getInstance(String bookName) {
    System.out.println("静态工厂working");
    Book book = new Book();
    book.setBookAuthor("djf");
    book.setBookName(bookName);
    return book;
  }
}
