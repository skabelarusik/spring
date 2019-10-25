package by.epam.crackertracker.controller;

import by.epam.crackertracker.config.UserPrincipal;
import by.epam.crackertracker.entity.Gender;
import by.epam.crackertracker.entity.Role;
import by.epam.crackertracker.entity.User;
import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.service.AdviceService;
import by.epam.crackertracker.service.BucketService;
import by.epam.crackertracker.service.UserService;
import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.util.PageSelector;
import by.epam.crackertracker.util.ParameterConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

@Controller
@RequestMapping(PageConstant.REQUEST_USER)
public class UserController {


    @Autowired
    private UserService userService;

    @Autowired
    private AdviceService adviceService;

    @Autowired
    private BucketService bucketService;

    @ExceptionHandler(TrackerServiceException.class)
    @GetMapping(PageConstant.URI_SELECT)
    public String selectAllUsers(@RequestParam Map<String,String> allRequestParams, ModelMap model) throws TrackerServiceException {
        String page = PageConstant.PATH_RESULT_USER;
        model.addAttribute(ParameterConstant.ATTRIBUTE_LIST_USERS, userService.selectAllUsers(allRequestParams, model));
        return page;
    }

    @ExceptionHandler(TrackerServiceException.class)
    @PostMapping(PageConstant.URI_SELECT_USER_BY_STATUS)
    public String selectAllUsersByStatus(@RequestParam Map<String,String> allRequestParams, ModelMap model) throws TrackerServiceException {
        model.addAttribute(ParameterConstant.ATTRIBUTE_LIST_USERS, userService.selectUsersByRole(allRequestParams, model));
        String  page = PageConstant.PATH_RESULT_USER;
        return page;
    }

    @ExceptionHandler(TrackerServiceException.class)
    @PostMapping(PageConstant.URI_SELECT_USER_BY_GENDER)
    public String selectAllUsersByGender(@RequestParam Map<String, String> allRequestParam, Model model) throws TrackerServiceException {
        model.addAttribute(ParameterConstant.ATTRIBUTE_LIST_USERS, userService.selectUsersByGender(allRequestParam, model));
        String page = PageConstant.PATH_RESULT_USER;
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
            return PageConstant.PATH_PAGE_EDIT_USER;
        }
        return PageConstant.PATH_PAGE_EDIT_USER;
    }

    @GetMapping(PageConstant.URI_UPDATE_USER)
    public String moveUpdateUser() {
        return PageConstant.PATH_PAGE_EDIT_USER;
    }

    @PostMapping(PageConstant.UPDATE_PASS)
    public String updatePass(@RequestParam String  oldpassword, @RequestParam String newpassword, @RequestParam String newpasswordCheck,
                             @AuthenticationPrincipal UserPrincipal user,  Model model){
        String password = user.getPassword();
        String login = user.getUsername();
        try {
            userService.updatePasswordUser(login, oldpassword, password, newpassword, newpasswordCheck);
            model.addAttribute(ParameterConstant.WRONG_DATA_PASS, ParameterConstant.MESSAGE_CONGRAT);
        } catch (TrackerServiceException e) {
            model.addAttribute(ParameterConstant.WRONG_DATA_PASS, ParameterConstant.MESSAGE_ERROR_REGIST);
            return PageConstant.PATH_PAGE_EDIT_USER;
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


    @RequestMapping(PageConstant.URI_LOGIN)
    public String loginUserP(HttpServletRequest request) throws TrackerServiceException {
        User user = null;
        String page = null;
        try{
            user = userService.checkUser(request.getParameter("username"),
                    request.getParameter(ParameterConstant.PASSWORD));
            if(user.getActive() == 0){
                request.setAttribute(ParameterConstant.MESAGE_WRONG_AUTH, "YOU WAS BANNED");
                return PageConstant.PATH_PAGE_MAIN_INDEX;
            }
            request.getSession(true).setAttribute(ParameterConstant.USER, user);
            request.getSession().setAttribute(ParameterConstant.ATTRIBUTE_ROLE, user.getRole());
            request.getSession().setAttribute(ParameterConstant.LOGIN, user.getLogin());
            request.getSession().setAttribute(ParameterConstant.PARAM_BALANCE, user.getBalance());
            request.getSession().setAttribute(ParameterConstant.PARAM_ADVICE, adviceService.selectRandomAdvice());
            page = PageSelector.selectHomePage(user.getRole().name());
        } catch (TrackerServiceException e) {
            request.setAttribute(ParameterConstant.MESAGE_WRONG_AUTH, "WRONG PASSWORD OR LOGIN");
            page = PageConstant.PATH_PAGE_LOGIN;
        }
        request.getSession().setAttribute(ParameterConstant.START_PAGE, page);
        return  page;
    }


    @PostMapping(PageConstant.URI_REGISTER)
    public String registration(@RequestParam String login, @RequestParam String password, @RequestParam String username,
                               @RequestParam String usersurname, @RequestParam String email,
                               @RequestParam String birthday, @RequestParam String gender, HttpServletRequest request){
        String page =  PageConstant.PATH_PAGE_MAIN_USER;
        try {
            userService.registerUser(login, password, username, usersurname, Gender.valueOf(gender.toUpperCase().trim()), email, birthday);
            User user = new User(login, password);
            user.setRole(Role.USER);
            user.setBalance(new BigDecimal(0));
            user.setName(username);
            user.setSurname(usersurname);
            user.setEmail(email);
            user.setGender(Gender.valueOf(gender.toUpperCase().trim()));
            user.setBirthDate(LocalDate.parse(birthday));
            request.getSession(true).setAttribute(ParameterConstant.USER, user);
            request.getSession().setAttribute(ParameterConstant.LOGIN, login);
            request.getSession().setAttribute(ParameterConstant.PARAM_BALANCE, 0);
            request.getSession().setAttribute(ParameterConstant.ATTRIBUTE_ROLE, ParameterConstant.ROLE_USER);
            request.getSession().setAttribute(ParameterConstant.PARAM_ADVICE, adviceService.selectRandomAdvice());
        } catch (TrackerServiceException e) {
            page = PageConstant.PATH_PAGE_REGISTER;
            request.setAttribute(ParameterConstant.WRONG_DATA_PASS, ParameterConstant.MESSAGE_ERROR_REGIST);
            return page;
        }
        request.getSession().setAttribute(ParameterConstant.START_PAGE, page);
        return  page;
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
    public String makeDeposit(@RequestParam String sum, @AuthenticationPrincipal UserPrincipal user,  @RequestParam String type,
                              @SessionAttribute String balance,  Model model, HttpServletRequest request){
        try {
            userService.deposit(sum, type, user.getUsername(), BigDecimal.valueOf(Double.parseDouble(balance)));
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

    @ExceptionHandler(TrackerServiceException.class)
    @RequestMapping(PageConstant.URI_MAIN)
    public String main(@AuthenticationPrincipal UserPrincipal user, Model model, HttpServletRequest request) throws TrackerServiceException {
        String page = PageSelector.selectHomePage(user.getRole());
        request.getSession(true).setAttribute(ParameterConstant.LOGIN, user.getUsername());
        request.getSession().setAttribute(ParameterConstant.USER, user.getUser());
        request.getSession().setAttribute(ParameterConstant.START_PAGE, page);
        request.getSession().setAttribute(ParameterConstant.ATTRIBUTE_ROLE, user.getRole());
        request.getSession().setAttribute(ParameterConstant.PARAM_BALANCE, user.getBalance());
        model.addAttribute(ParameterConstant.ATTRIBUTE_LIST_PRODUCTS_BUCKET ,
                bucketService.selectAll(user.getUsername()));
        return page;
    }

}