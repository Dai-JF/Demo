package com.djf.spring;

import com.djf.service.BookService;
import com.djf.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author: dai.jf
 * @date: 2021/8/18 11:45
 * @description:
 */
public class SpringTest {

  ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");

  @Test
  public void test1() {
    // 通过IOC容器创建对象，并为属性赋值★
    Person bean1 = (Person) context.getBean("person1");
    Person bean2 = (Person) context.getBean("person2");
    System.out.println(bean1 == bean2);
  }

  @Test
  public void test2() {
    // 根据bean的类型从IOC容器中获取bean的实例★
    // No qualifying bean of type 'com.djf.spring.Person' available: expected single matching bean
    // but found 2: person1,person2
    Person bean = (Person) context.getBean("person1", Person.class);
    System.out.println(bean);
  }

  @Test
  public void test3() {
    // 配置通过静态工厂方法创建的bean、实例工厂方法创建的bean、FactoryBean★
    Object staticFactory = context.getBean("staticFactory");
    Object instanceFactory = context.getBean("BookFactory");
    System.out.println(instanceFactory);
    System.out.println(staticFactory);
  }

  @Test
  public void test4() {
    // 通过构造器为bean的属性赋值（index,type属性介绍）
    Person bean = (Person) context.getBean("person3");
    System.out.println(bean.getName());
  }

  @Test
  public void test5() throws SQLException {
    // 从spring得到数据库连接池
    DataSource bean = (DataSource) context.getBean("source");
    System.out.println(bean.getConnection());
  }

  @Test
  public void test6() {
    // 泛型依赖注入
    BookService bookService = context.getBean(BookService.class);
    UserService userService = context.getBean(UserService.class);
    bookService.save();
    userService.save();
    // 带泛型的父类类型:com.djf.service.BaseService<com.djf.bean.Book>
    System.out.println(bookService.getClass().getGenericSuperclass());
  }
}
