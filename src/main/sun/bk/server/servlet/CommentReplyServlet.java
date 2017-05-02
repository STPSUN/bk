package main.sun.bk.server.servlet;

import main.sun.bk.server.commentReply.model.CommentReply;
import main.sun.bk.server.commentReply.service.impl.CommentReplyServiceImpl;
import main.sun.bk.server.common.Common;

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
@WebServlet(name = "CommentReplyServlet")
public class CommentReplyServlet extends HttpServlet {
    private CommentReplyServiceImpl commentReplyService = new CommentReplyServiceImpl();
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
        if("addCommentReply".equals(action))
        {
            doAddCommentReply(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private void doAddCommentReply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int state = 0;
        String msg = "";
        CommentReply commentReply = null;
        try
        {
            String id = request.getParameter("commentId");
            String replyUser = request.getParameter("replyUser");
            String replyContent = request.getParameter("replyContent");
            SimpleDateFormat df = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
            if(!id.isEmpty() && !replyUser.isEmpty() && !replyContent.isEmpty())
            {
                commentReply = new CommentReply();
                commentReply.setCommentId(Integer.parseInt(id));
                commentReply.setReplyUser(replyUser);
                commentReply.setReplyContent(replyContent);
                commentReply.setReplyTime(df.format(new Date()));

                commentReplyService.addCommentReply(commentReply);
                state = 1;
            }else
            {
                msg = "请求体参数不能为空";
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            msg = "回复失败";
        }

        Common.setApi(commentReply, state, msg, response);
    }
}
