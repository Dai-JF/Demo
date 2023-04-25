package service;

import pojo.User;

/**
 * created by : Dai 2021/8/6 5:21
 * description:
 */
public interface UserService {

    /**
     * 用户注册
     */
    void register(User user);

    /**
     * 用户登录
     *
     * @return 如果返回null，说明登录失败，返回有值，是登录成功
     */
    User login(User user);

    /**
     * 检查 用户名是否可用
     *
     * @return 返回true表示用户名已存在，返回false表示用户名可用
     */
    boolean exitUsername(String username);
}
