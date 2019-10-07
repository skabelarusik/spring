package by.epam.crackertracker.controller;

import by.epam.crackertracker.entity.Role;
import by.epam.crackertracker.entity.User;
import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.service.UserService;
import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.util.PageSelector;
import by.epam.crackertracker.util.ParameterConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;

@Controller
@RequestMapping(PageConstant.REQUEST_USER)
public class UserController {

    @Autowired
    public UserService userService;

    @GetMapping(PageConstant.URI_SELECT)
    public String selectAllUsers(HttpServletRequest request, Model model) {
        String page;
        try {
            model.addAttribute(ParameterConstant.ATTRIBUTE_LIST_USERS, userService.selectAllUsers(request));
            page = PageConstant.PATH_RESULT_USER;
        } catch (TrackerServiceException e) {
            page = PageConstant.PATH_PAGE_ERROR;
        }
        return page;
    }

    @PostMapping(PageConstant.URI_SELECT_USER_BY_STATUS)
    public String selectAllUsersByStatus(HttpServletRequest request, Model model) {
        String page;
        try {
            model.addAttribute(ParameterConstant.ATTRIBUTE_LIST_USERS, userService.selectUsersByRole(request));
            page = PageConstant.PATH_RESULT_USER;
        } catch (TrackerServiceException e) {
            page = PageConstant.PATH_PAGE_ERROR;
        }
            return page;
    }

    @PostMapping(PageConstant.URI_SELECT_USER_BY_GENDER)
    public String selectAllUsersByGender(HttpServletRequest request, Model model) {
        String page;
        try {
            model.addAttribute(ParameterConstant.ATTRIBUTE_LIST_USERS, userService.selectUsersByGender(request));
            page = PageConstant.PATH_RESULT_USER;
        } catch (TrackerServiceException e) {
            page = PageConstant.PATH_PAGE_ERROR;
        }
        return page;
    }

    @PostMapping(PageConstant.URI_UPDATE_USER)
    public String updateUser(HttpServletRequest request) {
        String name = request.getParameter(ParameterConstant.PARAM_NAME);
        String surname = request.getParameter(ParameterConstant.PARAM_SURNAME);
        String email = request.getParameter(ParameterConstant.EMAIL);
        String birthday = request.getParameter(ParameterConstant.PARAM_BIRTHDAY);
        String login = (String) request.getSession(true).getAttribute(ParameterConstant.LOGIN);
        try {
            userService.updateDataUser(login, name, surname, email, birthday);
            request.setAttribute(ParameterConstant.WRONG_DATA, ParameterConstant.MESSAGE_CONGRAT);
        } catch (TrackerServiceException e) {
            request.setAttribute(ParameterConstant.WRONG_DATA, ParameterConstant.MESSAGE_ERROR_REGIST);
        }
        return PageConstant.PATH_PAGE_EDIT_USER;
    }

    @GetMapping(PageConstant.URI_UPDATE_USER)
    public String moveUpdateUser() {
        return "editUser";
    }

    @PostMapping(PageConstant.UPDATE_PASS)
    public String updatePass(HttpServletRequest request){
        String oldPassword = request.getParameter(ParameterConstant.PARAM_NEW_PASSWORD);
        User user = (User)request.getSession(true).getAttribute(ParameterConstant.USER);
        String password = user.getPassword();
        String newPassword = request.getParameter(ParameterConstant.PARAM_NEW_PASSWORD);
        String newCheckPassword = request.getParameter(ParameterConstant.PARAM_NEW_PASSWORD_CHECK);
        String login = user.getLogin();
        try {
            userService.updatePasswordUser(login, oldPassword, password, newPassword, newCheckPassword);
            request.setAttribute(ParameterConstant.WRONG_DATA_PASS, ParameterConstant.MESSAGE_CONGRAT);
        } catch (TrackerServiceException e) {
            request.setAttribute(ParameterConstant.WRONG_DATA_PASS, ParameterConstant.MESSAGE_ERROR_REGIST);
        }
        return PageConstant.PATH_PAGE_EDIT_USER;
    }

    @PostMapping(PageConstant.URI_UPDATE_USER_ROLE)
    public String updateUserRole(HttpServletRequest request) {
        String id = request.getParameter(ParameterConstant.PARAM_ID);
        Role role = Role.valueOf(request.getParameter(ParameterConstant.ATTRIBUTE_ROLE).toUpperCase().trim());
        try {
            userService.updateRole(id, role);
            request.setAttribute("updateMessage", ParameterConstant.MESSAGE_CONGRAT);
        } catch (TrackerServiceException e) {
            request.setAttribute("updateMessage", ParameterConstant.WRONG_DATA);
        }
        return PageSelector.selectHomePage((Role) request.getSession(true).getAttribute(ParameterConstant.ATTRIBUTE_ROLE));
    }

    @PostMapping(PageConstant.URI_LOGIN)
    public String loginUser(HttpServletRequest request) throws TrackerServiceException {
        User user = null;
        String page = null;
        try{
            user = userService.checkUser(request.getParameter(ParameterConstant.PARAM_LOGIN),
                    request.getParameter(ParameterConstant.PARAM_PASSWORD));
            if(user.getActive() == 0){
                request.setAttribute(ParameterConstant.MESAGE_WRONG_AUTH, "YOU WAS BANNED");
                return  PageConstant.PATH_PAGE_MAIN_INDEX;
            }
            request.getSession(true).setAttribute(ParameterConstant.USER, user);
            request.getSession().setAttribute(ParameterConstant.ATTRIBUTE_ROLE, user.getRole());
            request.getSession().setAttribute(ParameterConstant.LOGIN, user.getLogin());
            page = PageSelector.selectHomePage(user.getRole());
        } catch (TrackerServiceException e) {
            request.setAttribute(ParameterConstant.MESAGE_WRONG_AUTH, "WRONG PASSWORD OR LOGIN");
            page = PageConstant.PATH_PAGE_MAIN_INDEX;
        }
        request.getSession().setAttribute(ParameterConstant.START_PAGE, page);
        return page;
    }


    @PostMapping(PageConstant.URI_REGISTER)
    public String registration(){
        return PageConstant.PATH_PAGE_REGISTER;
    }

    @PostMapping(PageConstant.URI_DEP)
    public String deposit(){
        return PageConstant.PATH_DEPOSIT;
    }

    @GetMapping(PageConstant.URI_REQUEST)
    public String request(){
        return PageConstant.PATH_LIST_USER;
    }

}