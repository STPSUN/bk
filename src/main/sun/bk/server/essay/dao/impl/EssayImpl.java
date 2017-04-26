package main.sun.bk.server.essay.dao.impl;

import main.sun.bk.server.common.ConnectionJdbc;
import main.sun.bk.server.essay.dao.EssayDao;
import main.sun.bk.server.essay.model.Essay;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SUN on 2017/4/24.
 */
public class EssayImpl implements EssayDao {
    public boolean addEssay(Essay essay)
    {
        boolean result = false;
        Connection connection = ConnectionJdbc.connectionJdbc();
        PreparedStatement ps = null;
        String sql = "INSERT INTO essay(title, author, create_time, zan, content, img, etype, recommend) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
        try
        {
            ps = connection.prepareStatement(sql);
            ps.setString(1, essay.getTitle());
            ps.setString(2, essay.getAuthor());
            ps.setString(3, essay.getCreateTime());
            ps.setInt(4, essay.getZan());
            ps.setString(5, essay.getContent());
            ps.setString(6, essay.getImg());
            ps.setString(7, essay.getEtype());
            ps.setString(8, essay.getRecommend());
            result = ps.executeUpdate() == 1 ? true : false;
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return result;
    }

    public List<Essay> findEssayByRecommend(String recommend)
    {
        List<Essay> essayList = new ArrayList<Essay>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        Connection connection = ConnectionJdbc.connectionJdbc();
        String sql = "select * from essay where recommend=?";

        try
        {
            ps = connection.prepareStatement(sql);
            ps.setString(1, recommend);
            rs = ps.executeQuery();
            while (rs.next())
            {
                Essay essay = new Essay();
                essay.setEssayId(rs.getInt("essay_id"));
                essay.setTitle(rs.getString("title"));
                essay.setAuthor(rs.getString("author"));
                essay.setCreateTime(rs.getString("create_time"));
                essay.setZan(rs.getInt("zan"));
                essay.setContent(rs.getString("content"));
                essay.setImg(rs.getString("img"));
                essay.setEtype(rs.getString("etype"));
                essay.setRecommend(rs.getString("recommend"));

                essayList.add(essay);
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return essayList;
    }

    public List<Essay> findEssayByType(String type)
    {
        List<Essay> essayList = new ArrayList<Essay>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        Connection connection = ConnectionJdbc.connectionJdbc();
        String sql = "select * from essay where etype=?";

        try
        {
            ps = connection.prepareStatement(sql);
            ps.setString(1, type);
            rs = ps.executeQuery();
            while (rs.next())
            {
                Essay essay = new Essay();
                essay.setEssayId(rs.getInt("essay_id"));
                essay.setTitle(rs.getString("title"));
                essay.setAuthor(rs.getString("author"));
                essay.setCreateTime(rs.getString("create_time"));
                essay.setZan(rs.getInt("zan"));
                essay.setContent(rs.getString("content"));
                essay.setImg(rs.getString("img"));
                essay.setEtype(rs.getString("etype"));
                essay.setRecommend(rs.getString("recommend"));

                essayList.add(essay);
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return essayList;
    }

    public List<Essay> findAllEssayByLikeTitle(String title)
    {
        List<Essay> essayList = new ArrayList<Essay>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        Connection connection = ConnectionJdbc.connectionJdbc();
        String sql = "select * from essay where title LIKE '%" + title + "%'";

        try
        {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next())
            {
                Essay essay = new Essay();
                essay.setEssayId(rs.getInt("essay_id"));
                essay.setTitle(rs.getString("title"));
                essay.setAuthor(rs.getString("author"));
                essay.setCreateTime(rs.getString("create_time"));
                essay.setZan(rs.getInt("zan"));
                essay.setContent(rs.getString("content"));
                essay.setImg(rs.getString("img"));
                essay.setEtype(rs.getString("etype"));
                essay.setRecommend(rs.getString("recommend"));

                essayList.add(essay);
            }

            ps.close();
            connection.close();
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return essayList;
    }

    public List<Essay> findAllEssayByZanDesc()
    {
        List<Essay> essayList = new ArrayList<Essay>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        Connection connection = ConnectionJdbc.connectionJdbc();
        String sql = "SELECT * FROM essay ORDER BY zan DESC";

        try
        {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next())
            {
                Essay essay = new Essay();
                essay.setEssayId(rs.getInt("essay_id"));
                essay.setTitle(rs.getString("title"));
                essay.setAuthor(rs.getString("author"));
                essay.setCreateTime(rs.getString("create_time"));
                essay.setZan(rs.getInt("zan"));
                essay.setContent(rs.getString("content"));
                essay.setImg(rs.getString("img"));
                essay.setEtype(rs.getString("etype"));
                essay.setRecommend(rs.getString("recommend"));

                essayList.add(essay);
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return essayList;
    }

    public List<Essay> findAllEssay()
    {
        List<Essay> essayList = new ArrayList<Essay>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        Connection connection = ConnectionJdbc.connectionJdbc();
        String sql = "SELECT * FROM essay";

        try
        {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next())
            {
                Essay essay = new Essay();
                essay.setEssayId(rs.getInt("essay_id"));
                essay.setTitle(rs.getString("title"));
                essay.setAuthor(rs.getString("author"));
                essay.setCreateTime(rs.getString("create_time"));
                essay.setZan(rs.getInt("zan"));
                essay.setContent(rs.getString("content"));
                essay.setImg(rs.getString("img"));
                essay.setEtype(rs.getString("etype"));
                essay.setRecommend(rs.getString("recommend"));

                essayList.add(essay);
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }

        return essayList;
    }
}
