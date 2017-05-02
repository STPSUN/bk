package main.sun.bk.server.msg.dao.impl;

import main.sun.bk.server.common.ConnectionJdbc;
import main.sun.bk.server.msg.dao.MsgDao;
import main.sun.bk.server.msg.model.Msg;
import main.sun.bk.server.msg.model.MsgAll;

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
    public List<MsgAll> getAllMsg()
    {
        List<MsgAll> msgAllList = new ArrayList<MsgAll>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        Connection connection = ConnectionJdbc.connectionJdbc();
        String sql = "SELECT m.msg_id, m.user_name, m.content, m.msg_time, mr.reply_user, mr.reply_content, mr.reply_time " +
                "FROM  msg m " +
                "JOIN msg_reply mr " +
                "ON m.msg_id = mr.msg_id";

        try
        {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next())
            {
                MsgAll msgAll = new MsgAll();
                msgAll.setMsgId(rs.getInt("msg_id"));
                msgAll.setMsgUser(rs.getString("user_name"));
                msgAll.setContent(rs.getString("content"));
                msgAll.setMsgTime(rs.getString("msg_time"));
                msgAll.setReplyUser(rs.getString("reply_user"));
                msgAll.setReplyContent(rs.getString("reply_content"));
                msgAll.setReplyTime(rs.getString("reply_time"));

                msgAllList.add(msgAll);
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return msgAllList;
    }
}
