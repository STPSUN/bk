package main.sun.bk.server.users.service.impl;

import main.sun.bk.server.users.dao.impl.UserImpl;
import main.sun.bk.server.users.model.User;
import main.sun.bk.server.users.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by SUN on 2017/4/24.
 */
public class UserServiceImpl implements UserService {
    UserImpl userImpl = new UserImpl();
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

    @Override
    public boolean updatePasswordByUserName(String userName, String newPassword, String oldPassword)
    {
        boolean result = false;
        User user = userImpl.findUserByUserName(userName);
        if(user != null)
        {
            if(user.getPassword().equals(oldPassword) && !user.getPassword().equals(newPassword))
            {
                user.setPassword(newPassword);
                userImpl.updateUser(user);
                result = true;
            }
        }

        return result;
    }

    @Override
    public boolean updatePasswordByCode(String userName, String code, String newPassword, HttpServletRequest request)
    {
        boolean result = false;
        User user = userImpl.findUserByUserName(userName);
        HttpSession session = request.getSession();
        String authCode = (String)session.getAttribute("code");
        String mobile = (String)session.getAttribute("mobile");
        if(user != null && authCode.equals(code) && mobile.equals(userName))
        {
            user.setPassword(newPassword);
            userImpl.updateUser(user);
            result = true;
        }

        return result;
    }
}
