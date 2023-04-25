package com.djf.spring;

import org.springframework.stereotype.Component;

/**
 * @author: dai.jf
 * @date: 2021/8/18 11:47
 * @description:
 */
public class Person {
  int id;
  String name;

  Book book;

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public Book getBook() {
    return book;
  }

  public void setBook(Book book) {
    this.book = book;
  }

  public Person() {}

  public Person(int id, String name) {
    this.id = id;
    this.name = name;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Person{" + "id=" + id + ", name='" + name + '\'' + ", book=" + book + '}';
  }
}
