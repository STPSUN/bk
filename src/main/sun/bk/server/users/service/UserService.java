package main.sun.bk.server.users.service;

import main.sun.bk.server.users.model.User;

/**
 * Created by SUN on 2017/4/24.
 */
public interface UserService {
    public User findUserByUserName(String userName);

    public void addUser(User user);
}
