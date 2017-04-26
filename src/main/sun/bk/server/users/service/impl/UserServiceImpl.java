package main.sun.bk.server.users.service.impl;

import main.sun.bk.server.users.dao.impl.UserImpl;
import main.sun.bk.server.users.model.User;
import main.sun.bk.server.users.service.UserService;

/**
 * Created by SUN on 2017/4/24.
 */
public class UserServiceImpl implements UserService {
    public User findUserByUserName(String userName)
    {
        UserImpl userImpl = new UserImpl();

        return userImpl.findUserByUserName(userName);
    }

    public void addUser(User user)
    {
        UserImpl userImpl = new UserImpl();
        userImpl.addUser(user);
    }
}
