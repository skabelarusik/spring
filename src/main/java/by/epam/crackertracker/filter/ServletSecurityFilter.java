/**
 * filter for checking access user
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.filter;

import by.epam.crackertracker.entity.Role;
import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.util.ParameterConstant;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

//@WebFilter(urlPatterns = {"/*"})
public class ServletSecurityFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();
        Role role = (Role) session.getAttribute(ParameterConstant.ATTRIBUTE_ROLE);
        if(checkAccess(request, role)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else{
            //log
            response.sendRedirect(PageConstant.PATH_PAGE_ERROR);
        }
    }

    public boolean checkAccess(HttpServletRequest request, Role role){
        boolean status = false;
        String uri = request.getRequestURI();
        if((uri.equals(PageConstant.PATH_PAGE_LOGIN) || uri.equals(PageConstant.PATH_PAGE_REGISTER)
                || uri.equals(PageConstant.PATH_PAGE_INDEX))|| uri.equals(PageConstant.PATH_PAGE_ERROR)
                || uri.equals(PageConstant.PATH_PAGE_REVIEW) || uri.equals(PageConstant.URI_CONTROLLER)
                || uri.equals(PageConstant.URI_LANG_ADMIN) || uri.contains(PageConstant.PATH_PICTURE)
                || uri.equals(PageConstant.URI_LOGIN) || uri.equals(PageConstant.URI_REGISTER)
                || uri.equals(PageConstant.PATH_PAGE_SERVICE)){
            status = true;
        } else {
            if(role == Role.ADMIN && (uri.equals(PageConstant.PATH_PAGE_MAIN_ADMIN) || uri.equals(PageConstant.PATH_PAGE_EDITING) ||
                    uri.equals(PageConstant.PATH_PAGE_RESULT) ||  uri.contains(PageConstant.URI_CONTROLLER_ADMIN) ||
                    uri.equals(PageConstant.PATH_DEPOSIT) || uri.equals(PageConstant.PATH_PAGE_MESSAGE) ||
                    uri.equals(PageConstant.PATH_PAGE_SEND_REVIEW) || uri.equals(PageConstant.URI_DEP))){
                status = true;}
         else
            if(role == Role.CURATOR && (uri.equals(PageConstant.PATH_PAGE_MAIN_CURATOR) || uri.equals(PageConstant.PATH_PAGE_EDITING) ||
                    uri.equals(PageConstant.PATH_PAGE_RESULT) || uri.contains(PageConstant.URI_CONTROLLER_CURATOR)) ||
                    uri.equals(PageConstant.PATH_DEPOSIT) || uri.equals(PageConstant.PATH_PAGE_MESSAGE) ||
                    uri.equals(PageConstant.PATH_PAGE_SEND_REVIEW)) {
                status = true;
         } else
            if(role == Role.SUPERUSER && (uri.equals(PageConstant.PATH_PAGE_MAIN_SUPERUSER)
                    || uri.equals(PageConstant.URI_CONTROLLER) || uri.equals(PageConstant.PATH_PAGE_EDITING) ||
                    uri.equals(PageConstant.PATH_PAGE_RESULT) || uri.contains(PageConstant.URI_CONTROLLER_SUPER_USER)) ||
                    uri.equals(PageConstant.PATH_DEPOSIT) || uri.equals(PageConstant.PATH_PAGE_MESSAGE)||
                    uri.equals(PageConstant.PATH_PAGE_SEND_REVIEW)){
                status = true;
         } else if(role == Role.USER && (uri.equals(PageConstant.PATH_PAGE_MAIN_USER) || uri.equals(PageConstant.PATH_PAGE_EDITING) ||
                    uri.equals(PageConstant.PATH_PAGE_RESULT) || uri.contains(PageConstant.URI_CONTROLLER_USER)) ||
                    uri.equals(PageConstant.PATH_DEPOSIT) || uri.equals(PageConstant.PATH_PAGE_MESSAGE) ||
                    uri.equals(PageConstant.PATH_PAGE_SEND_REVIEW)){
                status = true;
            }
        }
        return status;
    }

}



