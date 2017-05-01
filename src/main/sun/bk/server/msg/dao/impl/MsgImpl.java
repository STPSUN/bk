package main.sun.bk.server.msg.dao.impl;

import main.sun.bk.server.common.ConnectionJdbc;
import main.sun.bk.server.msg.dao.MsgDao;
import main.sun.bk.server.msg.model.Msg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created by SUN on 2017/5/1.
 */
public class MsgImpl implements MsgDao{
    @Override
    public boolean addMsg(Msg msg)
    {
        boolean result = false;
        Connection connection = ConnectionJdbc.connectionJdbc();
        PreparedStatement ps = null;
        String sql = "INSERT INTO msg(user_name, content, msg_time) VALUES(?, ?, ?)";
        try
        {
            ps = connection.prepareStatement(sql);
            ps.setString(1, msg.getUserName());
            ps.setString(2, msg.getContent());
            ps.setString(3, msg.getMsgTime());
            result = ps.executeUpdate() == 1 ? true : false;
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return result;
    }
}
