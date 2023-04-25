package service.impl;

import dao.UserDAO;
import dao.impl.UserDAOImpl;
import pojo.User;
import service.UserService;

/**
 * created by : Dai 2021/8/6 5:22
 * description:
 */
public class UserServiceImpl implements UserService {

    UserDAO userDAO = new UserDAOImpl();

    @Override
    public void register(User user) {
        userDAO.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDAO.queryByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public boolean exitUsername(String username) {
        // // 等于null,说明没查到，没查到表示可用
        return userDAO.queryByUsername(username) != null;
    }
}
