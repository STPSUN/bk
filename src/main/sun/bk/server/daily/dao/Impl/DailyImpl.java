package main.sun.bk.server.daily.dao.Impl;

import main.sun.bk.server.common.ConnectionJdbc;
import main.sun.bk.server.daily.dao.DailyDao;
import main.sun.bk.server.daily.model.Daily;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SUN on 2017/4/28.
 */
public class DailyImpl implements DailyDao{
    @Override
    public boolean addDeily(Daily daily)
    {
        boolean result = false;
        Connection connection = ConnectionJdbc.connectionJdbc();
        PreparedStatement ps = null;
        String sql = "INSERT INTO daily(create_time, zan, content) VALUES(?, ?, ?)";
        try
        {
            ps = connection.prepareStatement(sql);
            ps.setString(1, daily.getCreateTime());
            ps.setInt(2, daily.getZan());
            ps.setString(3, daily.getContent());
            result = ps.executeUpdate() == 1 ? true : false;
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<Daily> getAllDaily() {
        List<Daily> dailyList = new ArrayList<Daily>();
        PreparedStatement ps = null;
        ResultSet rs = null;

        Connection connection = ConnectionJdbc.connectionJdbc();
        String sql = "SELECT * FROM daily";

        try
        {
            ps = connection.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next())
            {
                Daily daily = new Daily();
                daily.setDailyId(rs.getInt("daily_id"));
                daily.setCreateTime(rs.getString("create_time"));
                daily.setZan(rs.getInt("zan"));
                daily.setContent(rs.getString("content"));

                dailyList.add(daily);
            }
        }catch (SQLException e)
        {
            e.printStackTrace();
        }
        return dailyList;
    }
}
