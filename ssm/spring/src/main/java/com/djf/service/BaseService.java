package com.djf.service;

import com.djf.dao.BaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author: dai.jf
 * @date: 2021/8/18 20:07
 * @description:
 */
public class BaseService<T> {

  @Autowired BaseDao<T> baseDao;

  public void save() {
    System.out.println("自动注入的dao:" + baseDao);
    baseDao.save();
  }
}
