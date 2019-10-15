package by.epam.crackertracker.controller;

import by.epam.crackertracker.entity.Advice;
import by.epam.crackertracker.entity.Gender;
import by.epam.crackertracker.entity.Role;
import by.epam.crackertracker.entity.User;
import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.service.AdviceService;
import by.epam.crackertracker.service.UserService;
import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.util.PageSelector;
import by.epam.crackertracker.util.ParameterConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.math.BigDecimal;
import java.util.Map;

@Controller
@RequestMapping(PageConstant.REQUEST_USER)
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private AdviceService adviceService;

    @GetMapping(PageConstant.URI_SELECT)
    public String selectAllUsers(@RequestParam Map<String,String> allRequestParams, ModelMap model) {
        String page;
        try {
            model.addAttribute(ParameterConstant.ATTRIBUTE_LIST_USERS, userService.selectAllUsers(allRequestParams, model));
            page = PageConstant.PATH_RESULT_USER;
        } catch (TrackerServiceException e) {
            page = PageConstant.PATH_PAGE_ERROR;
        }
        return page;
    }

    @PostMapping(PageConstant.URI_SELECT_USER_BY_STATUS)
    public String selectAllUsersByStatus(@RequestParam Map<String,String> allRequestParams, ModelMap model) {
        String page;
        try {
            model.addAttribute(ParameterConstant.ATTRIBUTE_LIST_USERS, userService.selectUsersByRole(allRequestParams, model));
            page = PageConstant.PATH_RESULT_USER;
        } catch (TrackerServiceException e) {
            page = PageConstant.PATH_PAGE_ERROR;
        }
            return page;
    }

    @PostMapping(PageConstant.URI_SELECT_USER_BY_GENDER)
    public String selectAllUsersByGender(@RequestParam Map<String, String> allRequestParam, Model model) {
        String page;
        try {
            model.addAttribute(ParameterConstant.ATTRIBUTE_LIST_USERS, userService.selectUsersByGender(allRequestParam, model));
            page = PageConstant.PATH_RESULT_USER;
        } catch (TrackerServiceException e) {
            page = PageConstant.PATH_PAGE_ERROR;
        }
        return page;
    }

    @PostMapping(PageConstant.URI_UPDATE_USER)
    public String updateUser(@RequestParam String  username, @RequestParam String usersurname, @RequestParam String email,
                             @RequestParam String birthday, Model model, @SessionAttribute String login) {

        try {
            userService.updateDataUser(login, username, usersurname, email, birthday);
            model.addAttribute(ParameterConstant.WRONG_DATA, ParameterConstant.MESSAGE_CONGRAT);
        } catch (TrackerServiceException e) {
            model.addAttribute(ParameterConstant.WRONG_DATA, ParameterConstant.MESSAGE_ERROR_REGIST);
        }
        return PageConstant.PATH_PAGE_EDIT_USER;
    }

    @GetMapping(PageConstant.URI_UPDATE_USER)
    public String moveUpdateUser() {
        return PageConstant.PATH_PAGE_EDIT_USER;
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
    public String updateUserRole(@RequestParam String id, @RequestParam String role, Model model, @SessionAttribute String startPage) {
        Role paramRole = Role.valueOf(role.toUpperCase().trim());
        try {
            userService.updateRole(id, paramRole);
            model.addAttribute(ParameterConstant.UPDATE_MESSAGE, ParameterConstant.MESSAGE_CONGRAT);
        } catch (TrackerServiceException e) {
           model.addAttribute(ParameterConstant.UPDATE_MESSAGE, ParameterConstant.WRONG_DATA);
        }
        return startPage;
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
                return PageConstant.PATH_PAGE_MAIN_INDEX;
            }
            request.getSession(true).setAttribute(ParameterConstant.USER, user);
            request.getSession().setAttribute(ParameterConstant.ATTRIBUTE_ROLE, user.getRole());
            request.getSession().setAttribute(ParameterConstant.LOGIN, user.getLogin());
            request.getSession().setAttribute(ParameterConstant.PARAM_BALANCE, user.getBalance());
            request.getSession().setAttribute(ParameterConstant.PARAM_ADVICE, adviceService.selectRandomAdvice());
            page = PageSelector.selectHomePage(user.getRole());
        } catch (TrackerServiceException e) {
            request.setAttribute(ParameterConstant.MESAGE_WRONG_AUTH, "WRONG PASSWORD OR LOGIN");
            page = PageConstant.PATH_PAGE_MAIN_INDEX;
        }
        request.getSession().setAttribute(ParameterConstant.START_PAGE, page);
        return page;
    }

    @PostMapping(PageConstant.URI_REGISTER)
    public String registration(@RequestParam String login, @RequestParam String password, @RequestParam String username,
                               @RequestParam String usersurname, @RequestParam String email,
                               @RequestParam String birthday, @RequestParam String gender, HttpServletRequest request){
       String page =  PageConstant.PATH_PAGE_MAIN_USER;
        try {
            User user = userService.registerUser(login, password, username, usersurname, Gender.valueOf(gender.toUpperCase().trim()), email, birthday);
            request.getSession(true).setAttribute(ParameterConstant.USER, user);
            request.getSession().setAttribute(ParameterConstant.ATTRIBUTE_ROLE, Role.USER);
            request.getSession().setAttribute(ParameterConstant.LOGIN, login);
            request.getSession().setAttribute(ParameterConstant.PARAM_BALANCE, 0);
            request.getSession().setAttribute(ParameterConstant.PARAM_ADVICE, adviceService.selectRandomAdvice());
        } catch (TrackerServiceException e) {
            page = PageConstant.PATH_PAGE_REGISTER;
            request.setAttribute(ParameterConstant.WRONG_DATA_PASS, ParameterConstant.MESSAGE_ERROR_REGIST);
        }
        request.getSession().setAttribute(ParameterConstant.START_PAGE, page);
        return page;
    }

    @GetMapping(PageConstant.URI_REGISTER)
    public String registr(){
        return PageConstant.PATH_PAGE_REGISTER;
    }


    @GetMapping(PageConstant.URI_DEP)
    public String deposit(){
        return PageConstant.PATH_DEPOSIT;
    }

    @PostMapping(PageConstant.URI_DEP)
    public String makeDeposit( HttpServletRequest request, @RequestParam String sum, @RequestParam String type, @SessionAttribute String login,
                              @SessionAttribute String balance,  ModelMap model){
        try {
            userService.deposit(sum, type, login, BigDecimal.valueOf(Double.parseDouble(balance)));
            model.addAttribute(ParameterConstant.MESSAGE_DEPOSIT, ParameterConstant.MESSAGE_CONGRAT);
            Double newSum = Double.parseDouble(sum) + Double.parseDouble(balance);
            request.getSession(true).setAttribute(ParameterConstant.PARAM_BALANCE, newSum);

        } catch (TrackerServiceException e) {
            model.addAttribute(ParameterConstant.MESSAGE_DEPOSIT, ParameterConstant.MESSAGE_ERROR_REGIST);
        }
        return PageConstant.PATH_DEPOSIT;
    }

    @GetMapping(PageConstant.URI_REQUEST)
    public String request(Model model){
        model.addAttribute(ParameterConstant.ATTRIBUTE_LIST_USERS, userService.selectAllAdmins());
        return PageConstant.PATH_RESULT_USER;
    }

}