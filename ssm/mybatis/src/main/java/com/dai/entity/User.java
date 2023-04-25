package com.dai.entity;

import lombok.*;

import java.io.Serializable;

/**
 * (User)实体类
 *
 * @author dai.jf
 * @since 2021-08-24 19:09:46
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User implements Serializable {
  private static final long serialVersionUID = 464288500673883850L;

  private Integer id;

  private String name;

  private String password;

  private String address;

  private String phone;
}
