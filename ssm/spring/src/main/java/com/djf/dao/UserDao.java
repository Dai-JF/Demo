package com.djf.dao;

import com.djf.bean.User;
import org.springframework.stereotype.Repository;

/**
 * @author: dai.jf
 * @date: 2021/8/18 20:07
 * @description:
 */
@Repository
public class UserDao extends BaseDao<User> {
  @Override
  public void save() {
    System.out.println("保存用户");
  }
}
