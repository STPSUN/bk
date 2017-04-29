package main.sun.bk.server.common;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.logging.Filter;
import java.util.logging.LogRecord;

/**
 * Created by SUN on 2017/4/29.
 */
public class CharacterEncodingFilter implements Filter{
    @Override
    public boolean isLoggable(LogRecord record) {
        return false;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws ServletException, IOException
    {
        request.setCharacterEncoding("utf-8");
        chain.doFilter(request, response);
    }
}
