package com.djf.proxy;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author: dai.jf
 * @date: 2021/8/18 22:21
 * @description:
 */
public class CalTest {

  @Test
  public void test1() {
   Calculate cal = new Cal();
    // 得到代理对象

    Calculate calProxy = CalProxy.getProxy(cal);
    System.out.println(calProxy.add(1, 2));

    // 从容器中拿目标对象注意:要用接口类型,而不是本类;[没继承接口才是本类]
    // 因为aop底层为动态代理,容器中存放的是他的代理对象:$proxy12
    // Calculate bean = cx.getBean(Calculate.class);
  }

  @Test
  public void test2() {
    ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
    Calculate bean = context.getBean(Calculate.class);
    bean.add(1, 6);
  }
}
