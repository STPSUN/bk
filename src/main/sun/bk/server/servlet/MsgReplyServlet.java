package main.sun.bk.server.servlet;

import main.sun.bk.server.common.Common;
import main.sun.bk.server.msgReply.model.MsgReply;
import main.sun.bk.server.msgReply.service.impl.MsgReplyServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by SUN on 2017/5/2.
 */
@WebServlet(name = "MsgReplyServlet")
public class MsgReplyServlet extends HttpServlet {
    private MsgReplyServiceImpl msgReplyService = new MsgReplyServiceImpl();
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
        if("addMsgReply".equals(action))
        {
            doAddMsgReply(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);
    }

    private void doAddMsgReply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int state = 0;
        String msg = "";
        MsgReply msgReply = null;
        try
        {
            String userName = request.getParameter("userName");
            String msgId = request.getParameter("msgId");
            String content = request.getParameter("content");
            SimpleDateFormat df = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
            if(!userName.isEmpty() && !msgId.isEmpty() && !content.isEmpty())
            {
                msgReply = new MsgReply();
                msgReply.setReplyUser(userName);
                msgReply.setContent(content);
                msgReply.setMsgId(Integer.parseInt(msgId));
                msgReply.setReplyTime(df.format(new Date()));

                msgReplyService.addMsgReply(msgReply);
                state = 1;
            }else
            {
                msg = "请求体数据不能为空";
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            msg = "回复留言失败";
        }

        Common.setApi(msgReply, state, msg, response);
    }
}
