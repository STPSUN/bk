package main.sun.bk.server.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by SUN on 2017/4/24.
 */
@WebServlet(name = "EssayServlet")
public class EssayServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = "toLogin";
        String ac = request.getParameter("action");
        if(ac != null)
        {
            action = ac;
        }
        if("addEssay".equals(action))
        {
            doAddEssay(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private void doAddEssay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        try
        {
            String title = request.getParameter("title");
            String author = request.getParameter("author");
            String content = request.getParameter("content");
            String img = request.getParameter("img");
            String etype = request.getParameter("etype");
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
