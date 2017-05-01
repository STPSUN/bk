package main.sun.bk.server.servlet;

import main.sun.bk.server.comment.model.Comment;
import main.sun.bk.server.comment.service.impl.CommentServiceImpl;
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
 * Created by SUN on 2017/5/1.
 */
@WebServlet(name = "CommentServlet")
public class CommentServlet extends HttpServlet {
    private CommentServiceImpl commentService = new CommentServiceImpl();
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
        if("addComment".equals(action))
        {
            doAddComment(request, response);
        }
        if("deleteCommentById".equals(action))
        {
            doDeleteComment(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private void doDeleteComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int state = 0;
        String msg = "";
        try
        {
            String id = request.getParameter("commentId");
            commentService.deleteCommentById(Integer.parseInt(id));
            state = 1;
        }catch (Exception e)
        {
            msg = "没有该评论ID";
        }

        Common.setApi(state, msg, response);
    }
    private void doAddComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int state = 0;
        String msg = "";
        Comment comment = new Comment();
        try
        {
            String essayId = request.getParameter("essayId");
            String usreName = request.getParameter("userName");
            String commentText = request.getParameter("comment");
            SimpleDateFormat df = new SimpleDateFormat("yyy:MM:dd HH:mm:ss");

            comment.setEssayId(Integer.parseInt(essayId));
            comment.setUserName(usreName);
            comment.setComment(commentText);
            comment.setCommentTime(df.format(new Date()));

            commentService.addComment(comment);
            state = 1;

        }catch (Exception e)
        {
            e.printStackTrace();
            msg = "评论添加失败";
        }

        Common.setApi(comment, state, msg, response);
    }
}
