package com.dai.entity;

import lombok.*;

import java.io.Serializable;

/**
 * (Key)实体类
 *
 * @author makejava
 * @since 2021-08-25 19:50:59
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Key implements Serializable {
  private static final long serialVersionUID = 569315675607213024L;

  private Integer id;

  private String keyName;

  private Lock lock;
}
