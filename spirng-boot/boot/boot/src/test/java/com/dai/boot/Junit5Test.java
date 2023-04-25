package com.dai.boot;

import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

/**
 * @author dai.jf
 * @date 2021/11/2-15:54
 * @description:
 */
@DisplayName("JUNIT5测试类")
public class Junit5Test {

  @BeforeEach
  void beforeEachTest() {
    System.out.println("beforeEach就要开始了");
  }

  @BeforeAll
  static void beforeAllTest() {
    System.out.println("BeforeAll就要开始了");
  }

  @AfterEach
  void afterEachTest() {
    System.out.println("afterEach结束了");
  }

  @AfterAll
  static void afterAllTest() {
    System.out.println("AfterAll结束了");
  }

  @Test
  @DisplayName("测试1")
  void test01() {
    System.out.println(1);
  }

  @Test
  @Disabled
  @DisplayName("测试2")
  void test02() {
    System.out.println(2);
  }

  @Test
  @Timeout(value = 200, unit = TimeUnit.MILLISECONDS)
  @DisplayName("测试3")
  void test03() throws InterruptedException {
    System.out.println(3);
    //规定了方法超时时间，若超过超时时间就报错
    Thread.sleep(200);
  }
}
