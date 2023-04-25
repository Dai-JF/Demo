package dao.impl;

import dao.BaseDAO;
import dao.UserDAO;
import pojo.User;

/**
 * created by : Dai
 * time : 2021/8/6 1:45
 * description:
 */
public class UserDAOImpl extends BaseDAO implements UserDAO {
    String sql = null;

    @Override
    public User queryByUsername(String username) {
        sql = "select `id`,`username`,`password`,`email` from t_user where username = ?";
        return queryForOne(User.class, sql, username);
    }

    @Override
    public User queryByUsernameAndPassword(String username, String password) {
        sql = "select `id`, `username`,`password`,`email` from t_user where username = ? and password = ?";
        return queryForOne(User.class, sql, username, password);
    }

    @Override
    public int saveUser(User user) {
        sql = "insert into t_user(username,password,email) values(?,?,?)";
        return update(sql, user.getUsername(), user.getPassword(), user.getEmail());
    }
}
