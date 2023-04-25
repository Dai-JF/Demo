package com.dai.boot;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @author dai.jf
 * @date 2021/11/2-18:40
 * @description:
 */

public class AssertionsTest {

  @DisplayName("断言机制测试")
  @Test
  void test01() {
    int cal = cal(2, 3);
    assertEquals(5, cal, "计算结果错误");
  }

  int cal(int i, int j) {
    return i + j;
  }
}
