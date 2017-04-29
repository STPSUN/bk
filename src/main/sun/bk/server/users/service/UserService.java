package main.sun.bk.server.users.service;

import main.sun.bk.server.users.model.User;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by SUN on 2017/4/24.
 */
public interface UserService {
    public User findUserByUserName(String userName);

    public void addUser(User user);

    public boolean updatePasswordByUserName(String userName, String newPassword, String oldPassword);

    public boolean updatePasswordByCode(String userName, String code, String newPassword, HttpServletRequest request);
}
