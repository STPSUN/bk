package main.sun.bk.server.msg.dao.impl;

import main.sun.bk.server.common.ConnectionJdbc;
import main.sun.bk.server.msg.dao.MsgDao;
import main.sun.bk.server.msg.model.Msg;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    @Override
    public List<Msg> getAllMsg()
    {
        List<Msg> msgList = new ArrayList<Msg>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        Connection connection = ConnectionJdbc.connectionJdbc();
        String sql = "SELECT * FROM msg";

        try
        {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next())
            {
                Msg msg = new Msg();
                msg.setMsgId(rs.getInt("msg_id"));
                msg.setUserName(rs.getString("user_name"));
                msg.setMsgTime(rs.getString("msg_time"));
                msg.setContent(rs.getString("content"));

                msgList.add(msg);
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return msgList;
    }

    @Override
    public boolean deleteMsgById(int msgId)
    {
        boolean result = false;
        Connection connection = ConnectionJdbc.connectionJdbc();
        PreparedStatement ps = null;
        String sql = "DELETE FROM msg WHERE msg_id=?";
        try
        {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, msgId);
            ps.executeUpdate();
            ps.close();
            connection.close();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return result;
    }
}
