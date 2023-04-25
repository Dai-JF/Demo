package dao;

import pojo.User;

/**
 * created by : Dai
 * time : 2021/8/6 1:42
 * description:
 */
public interface UserDAO {
    /**
     * 根据用户名获取用户信息  [验证用户名是否有效]
     * 如果返回null, 说明没有这个用户。反之亦然
     */
    User queryByUsername(String username);

    /**
     * 根据用户名和密码查询用户信息   [验证用户名和密码是否有效]
     * 如果返回null，说明用户名密码错误。反之亦然
     */
    User queryByUsernameAndPassword(String username, String password);

    /**
     * 保存用户信息
     */
    int saveUser(User user);
}
