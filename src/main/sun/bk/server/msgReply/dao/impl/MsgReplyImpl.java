package main.sun.bk.server.msgReply.dao.impl;

import main.sun.bk.server.common.ConnectionJdbc;
import main.sun.bk.server.msgReply.dao.MsgReplyDao;
import main.sun.bk.server.msgReply.model.MsgReply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SUN on 2017/5/2.
 */
public class MsgReplyImpl implements MsgReplyDao{
    @Override
    public boolean addMsgReply(MsgReply msgReply)
    {
        boolean result = false;
        Connection connection = ConnectionJdbc.connectionJdbc();
        PreparedStatement ps = null;
        String sql = "INSERT INTO msg_reply(msg_id, reply_user, content, reply_time) VALUES(?, ?, ?, ?)";
        try
        {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, msgReply.getMsgId());
            ps.setString(2, msgReply.getReplyUser());
            ps.setString(3, msgReply.getContent());
            ps.setString(4, msgReply.getReplyTime());
            result = ps.executeUpdate() == 1 ? true : false;
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public List<MsgReply> getAllMsgReply()
    {
        List<MsgReply> msgReplyList = new ArrayList<MsgReply>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        Connection connection = ConnectionJdbc.connectionJdbc();
        String sql = "SELECT * FROM msg_reply";

        try
        {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next())
            {
                MsgReply msgReply = new MsgReply();
                msgReply.setMsgId(rs.getInt("msg_id"));
                msgReply.setReplyUser(rs.getString("reply_user"));
                msgReply.setContent(rs.getString("reply_content"));
                msgReply.setReplyTime(rs.getString("reply_time"));

                msgReplyList.add(msgReply);
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return msgReplyList;
    }

    @Override
    public List<MsgReply> getMsgReplyById(int msgId)
    {
        List<MsgReply> msgReplyList = new ArrayList<MsgReply>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        Connection connection = ConnectionJdbc.connectionJdbc();
        String sql = "select * from msg_reply where msg_id = ?";

        try
        {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, msgId);
            rs = ps.executeQuery();
            while (rs.next())
            {
                MsgReply msgReply = new MsgReply();
                msgReply.setMsgId(rs.getInt("msg_id"));
                msgReply.setContent(rs.getString("reply_content"));
                msgReply.setReplyUser(rs.getString("reply_user"));
                msgReply.setReplyTime(rs.getString("reply_time"));

                msgReplyList.add(msgReply);
            }

            ps.close();
            connection.close();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return msgReplyList;
    }
}
