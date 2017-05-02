package main.sun.bk.server.commentReply.dao.impl;

import main.sun.bk.server.commentReply.dao.CommentReplyDao;
import main.sun.bk.server.commentReply.model.CommentReply;
import main.sun.bk.server.common.ConnectionJdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by SUN on 2017/5/2.
 */
public class CommentReplyImpl implements CommentReplyDao {
    @Override
    public boolean addCommentReplyDao(CommentReply commentReply)
    {
        boolean result = false;
        Connection connection = ConnectionJdbc.connectionJdbc();
        PreparedStatement ps = null;
        String sql = "INSERT INTO comment_reply(comment_id, reply_user, reply_content, reply_time) VALUES(?, ?, ?, ?)";
        try
        {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, commentReply.getCommentId());
            ps.setString(2, commentReply.getReplyUser());
            ps.setString(3, commentReply.getReplyContent());
            ps.setString(4, commentReply.getReplyTime());
            result = ps.executeUpdate() == 1 ? true : false;
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return result;
    }

    @Override
    public CommentReply findCommentReplyById(int commentId)
    {
        Connection connection = ConnectionJdbc.connectionJdbc();
        PreparedStatement ps = null;
        ResultSet rs = null;
        CommentReply commentReply = null;
        String sql = "select * from comment_reply where comment_id= ?";

        try {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, commentId);
            rs = ps.executeQuery();
            while (rs.next())
            {
                commentReply = new CommentReply();
                commentReply.setCommentId(commentId);
                commentReply.setReplyUser(rs.getString("reply_user"));
                commentReply.setReplyContent(rs.getString("reply_content"));
                commentReply.setReplyTime(rs.getString("reply_time"));
            }
            ps.close();

        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return commentReply;
    }
}
