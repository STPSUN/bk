package main.sun.bk.server.servlet;

import main.sun.bk.server.api.ApiResponse;
import main.sun.bk.server.users.model.User;
import main.sun.bk.server.users.service.impl.UserServiceImpl;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by SUN on 2017/4/24.
 */
@WebServlet(name = "UserServlet")
public class UserServlet extends HttpServlet {
    private UserServiceImpl userService = new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        System.out.println("into servlet************************************************");
        String action = "toLogin";
        String ac = request.getParameter("action");
        if(ac != null)
        {
            action = ac;
        }
        if("login".equals(action))
        {
            doLogin(request, response);
        }
        if("register".equals(action))
        {
            doRegister(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);
    }

    private void doRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String authCode = request.getParameter("authCode");
    }

    private void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("into dologin************************************************");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        ApiResponse apiResponse = new ApiResponse();
        PrintWriter out = response.getWriter();
        if(userService.findUserByUserName(userName) != null)
        {
            User user = userService.findUserByUserName(userName);
            if(user != null)
            {
                if(user.getPassword().equals(password))
                {
                    apiResponse.setCode("200");
                    apiResponse.setMsg("success");
                    apiResponse.setData(user);
                }else
                {
                    apiResponse.setCode("202");
                    apiResponse.setMsg("密码错误");
                }
            }else
            {
                apiResponse.setCode("201");
                apiResponse.setMsg("用户不存在");
            }
        }else
        {
            apiResponse.setCode("201");
            apiResponse.setMsg("用户名和密码不能为空");
        }

        JSONObject json = JSONObject.fromObject(apiResponse);
        out.println(json);
//        request.setAttribute("login", json);
//        request.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(request, response);
    }
}
