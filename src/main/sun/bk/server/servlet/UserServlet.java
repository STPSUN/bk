package main.sun.bk.server.servlet;

import main.sun.bk.server.api.ApiResponse;
import main.sun.bk.server.common.Common;
import main.sun.bk.server.common.SMSUtils;
import main.sun.bk.server.users.model.User;
import main.sun.bk.server.users.service.impl.UserServiceImpl;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

/**
 * Created by SUN on 2017/4/24.
 */
@WebServlet(name = "UserServlet")
public class UserServlet extends HttpServlet {
    private UserServiceImpl userService = new UserServiceImpl();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=utf-8");
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
        if("sendCode".equals(action))
        {
            doSendCode(request, response);
        }
        if("updatePassword".equals(action))
        {
            doUpdatePassword(request, response);
        }
        if("updatePasswordByCode".equals(action))
        {
            doUpdatePasswordByCode(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);
    }

    private void doUpdatePasswordByCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int state = 0;
        try
        {
            String mobile = request.getParameter("mobile");
            String authCode = request.getParameter("authCode");
            String newPassword = request.getParameter("newPassword");
            boolean result = userService.updatePasswordByCode(mobile, authCode, newPassword, request);
            if(result == true)
            {
                state = 1;
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        Common.setApi("ok", state, "update", response, request);
    }

    private void doUpdatePassword(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int state = 0;
        try
        {
            String userName = request.getParameter("userName");
            String oldPassword = request.getParameter("oldPassword");
            String newPassword = request.getParameter("newPassword");
            boolean result = userService.updatePasswordByUserName(userName, newPassword, oldPassword);
            if(result == true)
            {
                state = 1;
            }
        }catch (Exception e)
        {
            e.printStackTrace();
        }

        Common.setApi("ok", state, "updatePassword", response, request);
    }

    private void doSendCode(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        int state = 0;
        String msg = "";
        try
        {
            String mobile = request.getParameter("mobile");
            if(!mobile.isEmpty())
            {
                String code = createVerificationCode();
                SMSUtils.sendCheckCodeSMS(code, mobile);
                HttpSession session = request.getSession();
                session.setAttribute("code", code);
                session.setAttribute("mobile",mobile);
                state = 1;
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            msg = "验证码发送失败";
        }

        Common.setApi(state, msg, response);
    }

    private void doRegister(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        User user = new User();
        int state = 0;
        String msg = "";
        try
        {
            String userName = request.getParameter("userName");
            String password = request.getParameter("password");
            String authCode = request.getParameter("authCode");
            HttpSession session = request.getSession();
            String code = (String)session.getAttribute("code");
            String mobile = (String)session.getAttribute("mobile");
            if(!authCode.isEmpty() && code.equals(authCode))
            {
                if(mobile.equals(userName))
                {
                    if(!userName.isEmpty() && !password.isEmpty())
                    {
                        if(userService.findUserByUserName(userName) == null)
                        {
                            user.setPassword(password);
                            user.setUserName(userName);
                            userService.addUser(user);
                            state = 1;
                        }else
                        {
                            msg = "该用户已注册";
                        }
                    }
                }else
                {
                    msg = "请输入收到验证码的手机号";
                }
            }else
            {
                msg = "验证码错误";
            }
        }catch (Exception e)
        {
            e.printStackTrace();
            msg = "未发送验证码";
        }

        Common.setApi(state, msg, response);
    }

    private void doLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        System.out.println("into dologin************************************************");
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        ApiResponse apiResponse = new ApiResponse();
        PrintWriter out = response.getWriter();
        if(!userName.isEmpty() && !password.isEmpty())
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
    }

    private String createVerificationCode()
    {
        Random random = new Random();
        String result = "";
        for(int i = 0; i < 6; i++)
        {
            result += random.nextInt(10);
        }

        return result;
    }
}
