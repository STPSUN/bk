package main.sun.bk.server.common;

import main.sun.bk.server.api.ApiResponse;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by SUN on 2017/4/29.
 */
public class Common {
    public static void setApi(Object object, int state, String attribute, HttpServletResponse response, HttpServletRequest request) throws IOException, ServletException
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
    }

    public static void setApi(int state, String msg, HttpServletResponse response) throws IOException
    {
        ApiResponse apiResponse = new ApiResponse();
        PrintWriter out = response.getWriter();
        if(state == 1)
        {
            apiResponse.setCode("200");
            apiResponse.setMsg("success");
        }else
        {
            apiResponse.setCode("201");
            apiResponse.setMsg(msg);
        }

        JSONObject json = JSONObject.fromObject(apiResponse);
        out.println(json);
    }

    public static void setApi(Object object, int state, String msg, HttpServletResponse response) throws IOException
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
            apiResponse.setMsg(msg);
        }

        JSONObject json = JSONObject.fromObject(apiResponse);
        out.println(json);
    }

}
