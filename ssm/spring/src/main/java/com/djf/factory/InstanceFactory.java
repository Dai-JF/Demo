package com.djf.factory;

import com.djf.spring.Book;

/**
 * @author: dai.jf
 * @date: 2021/8/18 13:12
 * @description: 实例工厂： 需要new 实力工厂对象
 */
public class InstanceFactory {

  // new InstanceFactory().getInstance();
  public Book getInstance(String bookName) {
    System.out.println("实例工厂working");
    Book book = new Book();
    book.setBookAuthor("djf");
    book.setBookName(bookName);
    return book;
  }
}
