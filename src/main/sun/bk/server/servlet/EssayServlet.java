package main.sun.bk.server.servlet;

import main.sun.bk.server.api.ApiResponse;
import main.sun.bk.server.essay.model.Essay;
import main.sun.bk.server.essay.service.impl.EssayServiceImpl;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by SUN on 2017/4/24.
 */
@WebServlet(name = "EssayServlet")
public class EssayServlet extends HttpServlet {
    private EssayServiceImpl essayService = new EssayServiceImpl();
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
        if("getAllEssay".equals(action))
        {
            doGetAllEssay(request, response);
        }
        if("getEssayByZan".equals(action))
        {
            doGetEssayByZan(request, response);
        }
        if("getEssayByRecommend".equals(action))
        {
            doGetEssayByRecommend(request, response);
        }
        if("getEssayByTitle".equals(action))
        {
            doGetEssayByTitle(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    private void doGetEssayByTitle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        List<Essay> essayList = new ArrayList<Essay>();
        int state = 0;
        try
        {
            String title = request.getParameter("title");
            essayList = essayService.getAllEssayLikeTitle(title);
            if(!title.isEmpty())
            {
                state = 1;
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        String attribute = "getEssayByTitle";
        setApi(essayList, state, attribute, response, request);
    }

    private void doGetEssayByRecommend(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        ApiResponse apiResponse = new ApiResponse();
        PrintWriter out = response.getWriter();
        List<Essay> essayList = essayService.getAllEssayByRecommend("1");
        if(essayList != null)
        {
            apiResponse.setCode("200");
            apiResponse.setMsg("success");
            apiResponse.setData(essayList);
        }else
        {
            apiResponse.setCode("201");
        }

        JSONObject json = JSONObject.fromObject(apiResponse);
        out.println(json);
//        request.setAttribute("getEssayByRecommend", json);
//        request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
    }

    private void doGetEssayByZan(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        ApiResponse apiResponse = new ApiResponse();
        PrintWriter out = response.getWriter();
        List<Essay> essayList = essayService.getAllEssayByZanDesc();
        if(essayList != null)
        {
            apiResponse.setCode("200");
            apiResponse.setMsg("success");
            apiResponse.setData(essayList);
        }else
        {
            apiResponse.setCode("201");
        }

        JSONObject json = JSONObject.fromObject(apiResponse);
        out.println(json);
//        request.setAttribute("getEssayByZan", json);
//        request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
    }

    private void doGetAllEssay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        ApiResponse apiResponse = new ApiResponse();
        PrintWriter out = response.getWriter();
        List<Essay> essayList = essayService.getAllEssay();
        if(essayList != null)
        {
            apiResponse.setCode("200");
            apiResponse.setMsg("success");
            apiResponse.setData(essayList);
        }else
        {
            apiResponse.setCode("201");
        }

        JSONObject json = JSONObject.fromObject(apiResponse);
        out.println(json);
//        request.setAttribute("getAllEssay", json);
//        request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
    }

    private void doAddEssay(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        ApiResponse apiResponse = new ApiResponse();
        PrintWriter out = response.getWriter();
        Essay essay = new Essay();
        int temp = 0;
        try
        {
            String title = request.getParameter("title");
            String author = request.getParameter("author");
            String content = request.getParameter("content");
            String img = request.getParameter("img");
            String etype = request.getParameter("etype");

            essay.setTitle(title);
            essay.setAuthor(author);
            essay.setContent(content);
            essay.setImg(img);
            essay.setEtype(etype);
            essayService.addEssay(essay);

            temp = 1;
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        if(temp == 1)
        {
            apiResponse.setCode("200");
            apiResponse.setMsg("success");
            apiResponse.setData(essay);
        }else
        {
            apiResponse.setCode("201");
            apiResponse.setMsg("文章添加失败");
        }

        JSONObject json = JSONObject.fromObject(apiResponse);
        out.println(json);
//        request.setAttribute("addEssay", json);
//        request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
    }

    private void setApi(Object object, int state, String attribute, HttpServletResponse response, HttpServletRequest request) throws IOException, ServletException
    {
        ApiResponse apiResponse = new ApiResponse();
        PrintWriter out = response.getWriter();
        if(state == 1)
        {
            apiResponse.setCode("200");
            apiResponse.setMsg("success");
            apiResponse.setData(object);
        }else
        {
            apiResponse.setCode("201");
        }

        JSONObject json = JSONObject.fromObject(apiResponse);
        out.println(json);
//        request.setAttribute(attribute, json);
//        request.getRequestDispatcher("WEB-INF/pages/login.jsp").forward(request, response);
    }
}
