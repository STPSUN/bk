package main.sun.bk.server.users.dao;

import main.sun.bk.server.users.model.User;

/**
 * Created by SUN on 2017/4/24.
 */
public interface UserDao {
    public User findUserByUserName(String userName);

    public boolean addUser(User user);

    public boolean updateUser(User user);
}
