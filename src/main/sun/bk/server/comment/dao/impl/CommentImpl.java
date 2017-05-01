package main.sun.bk.server.comment.dao.impl;

import main.sun.bk.server.comment.dao.CommentDao;
import main.sun.bk.server.comment.model.Comment;
import main.sun.bk.server.common.ConnectionJdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SUN on 2017/5/1.
 */
public class CommentImpl implements CommentDao{
    @Override
    public boolean addComment(Comment comment) {
        boolean result = false;
        Connection connection = ConnectionJdbc.connectionJdbc();
        PreparedStatement ps = null;
        String sql = "INSERT INTO comment(essay_id, user_name, comment, comment_time) VALUES(?, ?, ?, ?)";
        try
        {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, comment.getEssayId());
            ps.setString(2, comment.getUserName());
            ps.setString(3, comment.getComment());
            ps.setString(4, comment.getCommentTime());
            result = ps.executeUpdate() == 1 ? true : false;
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public void deleteCommentById(int commentId)
    {
        Connection connection = ConnectionJdbc.connectionJdbc();
        PreparedStatement ps = null;
        String sql = "DELETE FROM comment WHERE comment_id=?";
        try
        {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, commentId);
            ps.executeUpdate();
            ps.close();
            connection.close();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public List<Comment> findCommentByEssayId(int essayId)
    {
        List<Comment> commentList= new ArrayList<Comment>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        Connection connection = ConnectionJdbc.connectionJdbc();
        String sql = "select * from comment where essay_id=?";

        try
        {
            ps = connection.prepareStatement(sql);
            ps.setInt(1, essayId);
            rs = ps.executeQuery();
            while (rs.next())
            {
                Comment comment = new Comment();
                comment.setCommentId(rs.getInt("comment_id"));
                comment.setUserName(rs.getString("user_name"));
                comment.setComment(rs.getString("comment"));
                comment.setEssayId(rs.getInt("essay_id"));
                comment.setCommentTime(rs.getString("comment_time"));

                commentList.add(comment);
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return commentList;
    }
}
