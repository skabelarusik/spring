/**
 * filter for checking XSS-attacks
 * @author Andrey Krupin,  june-august 2019
 */


package by.epam.crackertracker.filter;

import by.epam.crackertracker.util.PageConstant;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.Enumeration;

//@WebFilter(urlPatterns = {"/1/*"})
public class XssFilter implements Filter {
    public static final String XSS_SCRIPT_TAG = "<script";
    public static final String XSS_SCRIPT_END_TAG = "</script>";
    public static final String JAVASCRIPT = "javascript:";

    private static final Logger LOGGER = org.apache.log4j.Logger.getRootLogger();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        Enumeration<String> parameterNames = servletRequest.getParameterNames();
        while (parameterNames.hasMoreElements()){
            String text = servletRequest.getParameter(parameterNames.nextElement()).toLowerCase();
            if(text.contains(XSS_SCRIPT_TAG) || text.contains(XSS_SCRIPT_END_TAG) ||
            text.contains(JAVASCRIPT)){
                RequestDispatcher dispatcher = servletRequest.getServletContext().
                        getRequestDispatcher(PageConstant.PATH_PAGE_ERROR);
                LOGGER.info("tried xss-atack");
                dispatcher.forward(servletRequest, servletResponse);
                return;
            }

        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
