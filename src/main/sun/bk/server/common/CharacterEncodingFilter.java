package main.sun.bk.server.common;

import javax.servlet.*;
import java.io.IOException;
import java.util.logging.Filter;
import java.util.logging.LogRecord;

/**
 * Created by SUN on 2017/4/29.
 */
public class CharacterEncodingFilter implements Filter{
    private FilterConfig config;

    public void destroy() {
    }

    @Override
    public boolean isLoggable(LogRecord record) {
        return false;
    }

    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        String encoding = config.getInitParameter("encoding");
        if (null != encoding && !"".equals(encoding)) {
            request.setCharacterEncoding(encoding);
        }
        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {
        this.config = config;
    }
}
