package com.djf.dao;

import com.djf.bean.Book;
import org.springframework.stereotype.Repository;

/**
 * @author: dai.jf
 * @date: 2021/8/18 20:06
 * @description:
 */
@Repository
public class BookDao extends BaseDao<Book> {
    @Override
    public void save() {
    System.out.println("保存图书");
    }
}
