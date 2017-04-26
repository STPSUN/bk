package main.sun.bk.server.users.dao.impl;

import main.sun.bk.server.common.ConnectionJdbc;
import main.sun.bk.server.users.dao.UserDao;
import main.sun.bk.server.users.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by SUN on 2017/4/24.
 */
public class UserImpl implements UserDao {
    public User findUserByUserName(String userName)
    {
        Connection connection = ConnectionJdbc.connectionJdbc();
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = null;
        String sql = "select * from users where user_name = ?";

        try {
            ps = connection.prepareStatement(sql);
            ps.setString(1, userName);
            rs = ps.executeQuery();
            while (rs.next())
            {
                user = new User();
                user.setUserName(userName);
                user.setPassword(rs.getString("password"));
                user.setAuthCode(rs.getString("auth_code"));
                user.setSource(rs.getString("source"));
            }

        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return user;
    }

    public boolean addUser(User user)
    {
        boolean result = false;
        Connection connection = ConnectionJdbc.connectionJdbc();
        PreparedStatement ps = null;
        String sql = "INSERT INTO users(user_name, password, auth_code, source) VALUES(?, ?, ?, ?)";
        try
        {
            ps = connection.prepareStatement(sql);
            ps.setString(1, user.getUserName());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getAuthCode());
            ps.setString(4, user.getSource());
            result = ps.executeUpdate() == 1 ? true : false;
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return result;
    }

}
