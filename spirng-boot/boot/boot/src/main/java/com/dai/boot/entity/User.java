package com.dai.boot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author dai.jf
 * @date 2021/10/27-9:17
 * @description:
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@TableName("user")//和类名相同时,可不写
public class User {

  /**
   * 所有属性在数据库都要存在
   */
  @TableField(exist = false)
  private String username;
  @TableField(exist = false)
  private String password;

  /**
   * 数据库字段
   */
  private Long id;
  private String name;
  private Integer age;
  private String email;
}
