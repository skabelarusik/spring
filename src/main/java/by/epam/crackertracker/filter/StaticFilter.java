package by.epam.crackertracker.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

//@WebFilter(urlPatterns = {"/jsp/picture/*"})
public class StaticFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        servletRequest.getServletContext().getNamedDispatcher("default").forward(servletRequest, servletResponse);
    }

}