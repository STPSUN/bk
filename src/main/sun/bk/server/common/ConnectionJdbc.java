package main.sun.bk.server.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by SUN on 2017/4/24.
 */
public class ConnectionJdbc {
    public static Connection connectionJdbc()
    {
        String driver = "com.mysql.jdbc.Driver";
        String url = "jdbc:mysql://116.62.37.49:3306/bk?useUnicode=true&characterEncodin=utf8";
//        String url = "jdbc:mysql://localhost:3306/bld?useUnicode=true&characterEncodin=utf8";
        Connection connection = null;
        try
        {
            Class.forName(driver);
            try
            {
                connection = DriverManager.getConnection(url, "root", "sun123");
//                System.out.println("ok");
//                connection = DriverManager.getConnection(url, "root", "sun");
            }catch (SQLException e)
            {
                e.printStackTrace();
            }
        }catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        return connection;
    }
}
