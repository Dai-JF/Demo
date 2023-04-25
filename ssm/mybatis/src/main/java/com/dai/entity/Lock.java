package com.dai.entity;

import lombok.*;

import java.io.Serializable;

/**
 * (Lock)实体类
 *
 * @author makejava
 * @since 2021-08-25 19:51:00
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Lock implements Serializable {
  private static final long serialVersionUID = 895945005146044004L;

  private Integer id;

  private String lockName;
}
