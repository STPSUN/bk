package main.sun.bk.server.servlet;

import main.sun.bk.server.common.Common;
import main.sun.bk.server.daily.model.Daily;
import main.sun.bk.server.daily.service.impl.DailyServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by SUN on 2017/4/29.
 */
@WebServlet(name = "DailyServlet")
public class DailyServlet extends HttpServlet {
    private DailyServiceImpl dailyService = new DailyServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
        String action = "toLogin";
        String ac = request.getParameter("action");
        if(ac != null)
        {
            action = ac;
        }
        if("addDaily".equals(action))
        {
            doAddDaily(request, response);
        }
        if("getAllDaily".equals(action))
        {
            doGetAllDaily(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);
    }

    private void doGetAllDaily(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int state = 0;
        List<Daily> dailyList = new ArrayList<Daily>();
        try
        {
            dailyList = dailyService.getAllDaily();
            if(dailyList != null)
            {
                state = 1;
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        Common.setApi(dailyList, state, "getAllDaily", response, request);
    }

    private void doAddDaily(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int state = 0;
        Daily daily = new Daily();
        try
        {
            String content = request.getParameter("content");
            SimpleDateFormat df = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
            String time = df.format(new Date());
            if(!content.isEmpty())
            {
                daily.setContent(content);
                daily.setCreateTime(time);
                state = 1;
                dailyService.addDaily(daily);
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        Common.setApi(daily, state, "addDaily", response, request);
    }
}
