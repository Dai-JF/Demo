package com.dai.dao;

import com.dai.entity.User;
import org.apache.ibatis.annotations.MapKey;

import java.util.List;
import java.util.Map;

/**
 * @author: dai.jf
 * @date: 2021/8/24 19:11
 * @description:
 */
public interface UserDao {

  int addUser(User user);

  int deleteUser(Integer id);

  int updateUser(User user);

  User getUserById(Integer id);

  List<User> getAll();

  /**
   * description: 【单条记录才行】列名作为key 列值作为value
   *
   * @author: dai.jf
   * @date: 2021/8/25 16:52
   * @param id:
   * @return: java.util.Map<java.lang.String,java.lang.Object>
   */
  Map<String, Object> returnMap(Integer id);

  /**
   * description:查询多条记录封装map【这样中里面拿出一个user和list相比就特别简单】
   *
   * @author: dai.jf
   * @date: 2021/8/25 17:17
   * @return: java.util.Map<java.lang.Integer :主键,com.dai.entity.User：封装的user对象>
   *     <p>@MapKey("id"):告诉他主键是key，不然他不知道【把查询记录的id作为key封装这个map】
   */
  @MapKey("id")
  Map<Integer, User> maps();
}
