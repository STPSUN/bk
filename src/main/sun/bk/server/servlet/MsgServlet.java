package main.sun.bk.server.servlet;

import main.sun.bk.server.common.Common;
import main.sun.bk.server.msg.model.Msg;
import main.sun.bk.server.msg.model.MsgAll2;
import main.sun.bk.server.msg.service.impl.MsgServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by SUN on 2017/5/1.
 */
@WebServlet(name = "MsgServlet")
public class MsgServlet extends HttpServlet {
    private MsgServiceImpl msgService = new MsgServiceImpl();
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
        if("addMsg".equals(action))
        {
            doAddMsg(request, response);
        }
        if("getAllMsg".equals(action))
        {
            doGetAllMsg(request, response);
        }
        if("deleteMsg".equals(action))
        {
            doDeleteMsg(request, response);
        }
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private void doDeleteMsg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int state = 0;
        String msg = "";
        try
        {
            String id = request.getParameter("msgId");
            if(!id.isEmpty())
            {
                msgService.deleteMsgById(Integer.parseInt(id));
                state = 1;
            }else
            {
                msg = "msgId不能为空";
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            msg = "删除失败";
        }

        Common.setApi(state, msg, response);
    }

    private void doGetAllMsg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int state = 0;
        String msg = "";
        List<MsgAll2> msgAll2List = msgService.getAllMsg();
        if(!msgAll2List.isEmpty())
        {
            state = 1;
        }else
        {
            msg = "未获取到数据";
        }

        Common.setApi(msgAll2List, state, msg, response);
    }

    private void doAddMsg(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int state = 0;
        String msg = "";
        Msg msg1 = new Msg();
        try
        {
            String userName = request.getParameter("userName");
            String content = request.getParameter("content");
            SimpleDateFormat df = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
            if(!userName.isEmpty() && !content.isEmpty())
            {
                msg1.setUserName(userName);
                msg1.setContent(content);
                msg1.setMsgTime(df.format(new Date()));

                msgService.addMsg(msg1);
                state = 1;
            }else
            {
                msg = "请求体参数不能为空";
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            msg = "添加失败";
        }

        Common.setApi(msg1, state, msg, response);
    }
}