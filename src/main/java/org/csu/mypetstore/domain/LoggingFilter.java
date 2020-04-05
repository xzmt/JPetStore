package org.csu.mypetstore.domain;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;


    @WebFilter(filterName="myFilter",urlPatterns="/*")
    public class LoggingFilter implements Filter
    {
        private PrintWriter logger;
        private String prefix;

        public void destroy()
        {
            if(logger != null)
                logger.close();
        }

        public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException
        {
            HttpServletRequest httpServletRequest = (HttpServletRequest) req;
            logger.println(new Date() + " " + prefix + httpServletRequest.getRequestURI());
            logger.flush();
            chain.doFilter(req, resp);
        }

        public void init(FilterConfig filterConfig) throws ServletException
        {
            prefix = filterConfig.getInitParameter("prefix");
            String logFileName = filterConfig.getInitParameter("logFileName");
            String appPath = filterConfig.getServletContext().getRealPath("/");

            try
            {
                logger = new PrintWriter(new File(appPath,logFileName));
            }
            catch (Exception e)
            {
                e.printStackTrace();
                throw new ServletException(e.getMessage());
            }
        }

    }

