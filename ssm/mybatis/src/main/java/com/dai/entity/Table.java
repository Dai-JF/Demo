package com.dai.entity;

import lombok.*;

/**
 * @author: dai.jf
 * @date: 2021/8/25 18:28
 * @description: 测试resultMap自定义封装规则
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Table {
  private int id;
  private String name;
  private int age;
}
