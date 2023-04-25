package com.dai.dao;

import com.dai.entity.Key;

/**
 * @author: dai.jf
 * @date: 2021/8/25 19:55
 * @description:
 */
public interface KeyDao {
  /**
   * description: 根据id找钥匙和锁【钥匙中有一个锁的属性】
   *
   * @author: dai.jf
   * @date: 2021/8/25 19:56
   * @param id:
   * @return: com.dai.entity.Key
   */
  Key getById(Integer id);
}
