/**
 * service for working with user dao
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.service;

import by.epam.crackertracker.dao.UserDao;
import by.epam.crackertracker.dao.UserDaoJdbcImpl;
import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.util.ParameterConstant;
import by.epam.crackertracker.validator.*;
import by.epam.crackertracker.entity.Role;
import by.epam.crackertracker.entity.Gender;
import by.epam.crackertracker.entity.User;
import by.epam.crackertracker.exception.TrackerDBException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private GenderValidator genderValidator;

    @Autowired
    private TypeSortedValidator typeSortedValidator;

    @Autowired
    private LoginValidator loginValidator;

    @Autowired
    private PasswordValidator validator1;

    @Autowired
    private PasswordValidator passwordValidator;

    @Autowired
    private NameSurnameValidator nameSurnameValidator;

    @Autowired
    private BirthdayValidator birthdayValidator;

    @Autowired
    private EmailValidator emailValidator;

    @Autowired
    private RoleValidator roleValidator;

    @Autowired
    private IdValidator idValidator;

    @Autowired
    private DoubleValidator doubleValidator;

    @Autowired
    private DepositValidator depositValidator;

    private static final Logger LOGGER = Logger.getRootLogger();
    public static final int MAX_TABLE_USERS = 11;

    public User checkUser(String loginValue, String passValue) throws TrackerServiceException {
        User user = null;
//        user.setActive(1);
//        user.setBalance(new BigDecimal(10));
//        user.setLogin("Andrey");
//        user.setName("And");
//        user.setGender(Gender.MALE);
//        user.setRole(Role.ADMIN);

        try{
            if(loginValidator.isValidate(loginValue) && validator1.isValidate(passValue)) {
                user = userDao.selectByLogin(loginValue, passValue);
            }
        } catch (TrackerDBException e){
            LOGGER.error(" Wrong service check user",e);
            throw new TrackerServiceException("Wrong service check user",e);
        }

        return user;
    }

    public boolean registerUser(String login, String pass, String name, String surname, Gender gender, String email,
                               String dateOfBirth) throws TrackerServiceException {
        boolean status = false;
        if(login != null && !login.isEmpty() && pass != null && !pass.isEmpty() && gender != null && email != null &&
                !email.isEmpty() && dateOfBirth != null && !dateOfBirth.isEmpty()){
            try {
                if(loginValidator.isValidate(login) && passwordValidator.isValidate(pass) && emailValidator.isValidate(email) &&
                    birthdayValidator.isValidate(dateOfBirth) && nameSurnameValidator.isValidate(name) &&
                    nameSurnameValidator.isValidate(surname)){
                UserDaoJdbcImpl dao = new UserDaoJdbcImpl();
                User user = new User(login, pass);
                user.setName(name);
                user.setSurname(surname);
                user.setGender(gender);
                user.setBirthDate(LocalDate.parse(dateOfBirth));
                user.setRegistrDate(LocalDate.now());
                user.setEmail(email);
                user.setPath(PageConstant.DEFAULT_AVATAR_PATH);
                status = dao.insert(user);
                }
            } catch (TrackerDBException e) {
                LOGGER.error("Wrong service register user", e);
                throw new TrackerServiceException("Wrong service register user", e);
            }
        }
        return status;
    }

    public List<User> selectAllUsers( Map<String,String> allRequestParams,  ModelMap model) throws TrackerServiceException {
        String currentPage = allRequestParams.get(ParameterConstant.ATTRIBUTE_NEXT_PAGE);
        String type = allRequestParams.get(ParameterConstant.PARAM_TYPE);
        List<User> userList;
        if(type == null){
            type = ParameterConstant.SORTED_NOTHING;
        }
        if(currentPage == null){
            int intPage = 1;
            try {
                userList = userDao.selectAll(intPage, type);
            } catch (Exception e) {
                LOGGER.error(" Wrong service select all users",e);
                throw new TrackerServiceException("Wrong service select all users",e);
            }
            model.addAttribute(ParameterConstant.ATTRIBUTE_RES_PAGE, intPage);
            if(userList.size() == MAX_TABLE_USERS){
                model.addAttribute(ParameterConstant.ATTRIBUTE_NEXT_PAGE, intPage + 1);
            }
        } else {
            int intPage = Integer.parseInt(currentPage);
            try {
                userList = userDao.selectAll(intPage, type);
            } catch (Exception e) {
                LOGGER.error("Wrong service  select all users",e);
                throw new TrackerServiceException("Wrong service select all users",e);
            }
            model.addAttribute(ParameterConstant.ATTRIBUTE_RES_PAGE, intPage);
            if(userList.size() == MAX_TABLE_USERS){
                model.addAttribute(ParameterConstant.ATTRIBUTE_NEXT_PAGE, intPage + 1);
            }
            if(intPage > 1){
                model.addAttribute(ParameterConstant.ATTRIBUTE_PREV_PAGE, intPage - 1);
            }
        }
        List<User> newUserList = null;
        if(!userList.isEmpty() && userList.size() < MAX_TABLE_USERS){
            newUserList = new ArrayList<>(userList.size());
            for(int i = 0 ; i < userList.size(); i++){
                newUserList.add(userList.get(i));
            }
        } else {
            newUserList = new ArrayList<>(userList.size()-1);
            for(int i = 0 ; i < userList.size() - 1; i++){
                newUserList.add(userList.get(i));
            }
        }
        return userList;
    }

    public void updateDataUser(String login, String name, String surname,
                                  String email, String birthday) throws TrackerServiceException {
        try{
            if(emailValidator.isValidate(email) && birthdayValidator.isValidate(birthday) && nameSurnameValidator.isValidate(name) &&
                    nameSurnameValidator.isValidate(surname) && loginValidator.isValidate(login)) {
                User user = new User(login);
                user.setName(name);
                user.setSurname(surname);
                user.setEmail(email);
                user.setBirthDate(LocalDate.parse(birthday));
                userDao.updateUser(user);
            } else {
                throw new TrackerServiceException("Wrong data");
            }
        } catch (TrackerDBException e){
            LOGGER.error("Wrong service update user",e);
            throw new TrackerServiceException("Wrong service update user",e);
        }
    }

    public void updatePasswordUser(String login, String oldPass, String currentPass,
                                      String newPass, String newPassCheck) throws TrackerServiceException {
        boolean status = false;
        try{
            if(loginValidator.isValidate(login) && passwordValidator.isValidate(oldPass) && passwordValidator.isValidate(currentPass) &&
                    passwordValidator.isValidate(newPass) && passwordValidator.isValidate(newPassCheck) &&
                    oldPass.equals(currentPass) && newPass.equals(newPassCheck)) {
                userDao.updatePasswordUser(login, newPass);
            } else{
                throw new TrackerServiceException("Not valide data");
            }
        } catch (TrackerDBException e){
            LOGGER.error("Wrong service update password user",e);
            throw new TrackerServiceException("Wrong service update password user",e);
        }
    }

//    public boolean sendAvatar(String login, String path) throws TrackerServiceException {
//        PathAvatarValidator pathAvatarValidator = new PathAvatarValidator();
//        boolean status = false;
//        try{
//            if(loginValidator.isValidate(login) && pathAvatarValidator.isValide(login, path)){
//                userDao = new UserDaoJdbcImpl();
//                status = userDao.updateFilePath(login, path);
//            }
//        } catch (TrackerDBException e){
//            LOGGER.error("Wrong service send avatar user",e);
//            throw new TrackerServiceException("Wrong service send avatar user",e);
//        }
//        return status;
//    }

    public List<User> selectUsersByRole( Map<String,String> allRequestParams, ModelMap model) throws TrackerServiceException {
        String role = allRequestParams.get(ParameterConstant.ATTRIBUTE_ROLE);
        String currentPage = allRequestParams.get(ParameterConstant.ATTRIBUTE_NEXT_PAGE);
        String type = allRequestParams.get(ParameterConstant.PARAM_TYPE);
        List<User> userList;
        int intPage;
        if(type == null){
            type = ParameterConstant.SORTED_NOTHING;
        }
        if(!typeSortedValidator.isValidate(type) || !roleValidator.isValidate(role)){
            throw new TrackerServiceException("Wrong role or type users sorted in service select users by role");
        }
        if(currentPage == null){
            intPage = 1;
            model.addAttribute(ParameterConstant.ATTRIBUTE_RES_PAGE, intPage);
        } else {
            intPage = Integer.parseInt(currentPage);
            model.addAttribute(ParameterConstant.ATTRIBUTE_RES_PAGE, intPage);
            if(intPage > 1){
                model.addAttribute(ParameterConstant.ATTRIBUTE_PREV_PAGE, intPage - 1);
            }
        }
        try {
            userList = userDao.selectByRole(intPage, type, role);
        } catch (TrackerDBException e) {
            LOGGER.error("Wrong service select user by role",e);
            throw new TrackerServiceException("Wrong service select user by role",e);
        }
        List<User> newUserList = null;
        if(!userList.isEmpty() && userList.size() < MAX_TABLE_USERS){
            newUserList = new ArrayList<>(userList.size());
            for(int i = 0 ; i < userList.size(); i++){
                newUserList.add(userList.get(i));
            }
        } else {
            newUserList = new ArrayList<>(userList.size()-1);
            for(int i = 0 ; i < userList.size() - 1; i++){
                newUserList.add(userList.get(i));
            }
            model.addAttribute(ParameterConstant.ATTRIBUTE_NEXT_PAGE, intPage + 1);
        }
        return newUserList;
    }

    public List<User> selectUsersByGender( Map<String, String> allRequestParam, Model model) throws TrackerServiceException {
        String gender = allRequestParam.get(ParameterConstant.PARAM_GENDER);
        String currentPage = allRequestParam.get(ParameterConstant.ATTRIBUTE_NEXT_PAGE);
        String type = allRequestParam.get(ParameterConstant.PARAM_TYPE);
        List<User> userList;
            if(type == null){
                type = ParameterConstant.SORTED_NOTHING;
            }
        if(!typeSortedValidator.isValidate(type) || !genderValidator.isValidate(gender)){
            throw new TrackerServiceException("Wrong role or type users sorted in service select users by gender");
        }
        try{
            if(currentPage == null){
            int intPage = 1;
            userList = userDao.selectByGender(intPage, type, gender);
            model.addAttribute(ParameterConstant.ATTRIBUTE_RES_PAGE, intPage);
            if(userList.size() == MAX_TABLE_USERS){
               model.addAttribute(ParameterConstant.ATTRIBUTE_NEXT_PAGE, intPage + 1);
            }
        } else {
            int intPage = Integer.parseInt(currentPage);
            userList = userDao.selectByGender(intPage, type, gender);
            model.addAttribute(ParameterConstant.ATTRIBUTE_RES_PAGE, intPage);
            if(userList.size() == MAX_TABLE_USERS){
                model.addAttribute(ParameterConstant.ATTRIBUTE_NEXT_PAGE, intPage + 1);
            }
            if(intPage > 1){
               model.addAttribute(ParameterConstant.ATTRIBUTE_PREV_PAGE, intPage - 1);
            }
        }
        } catch (TrackerDBException e){
            LOGGER.error("Wrong service select user by gender",e);
            throw new TrackerServiceException("Wrong service select user by gender",e);
        }
        List<User> newUserList = null;
        if(!userList.isEmpty() && userList.size() < MAX_TABLE_USERS){
            newUserList = new ArrayList<>(userList.size());
            for(int i = 0 ; i < userList.size(); i++){
                newUserList.add(userList.get(i));
            }
        } else {
            newUserList = new ArrayList<>(userList.size()-1);
            for(int i = 0 ; i < userList.size() - 1; i++){
                newUserList.add(userList.get(i));
            }
        }
        return newUserList;
    }

    public void updateRole(String id, Role role) throws TrackerServiceException {
        if (idValidator.isValidate(id) && roleValidator.isValidate(role.name())) {
            try {
                userDao.updateRoleUser(Integer.parseInt(id), role);
            } catch (TrackerDBException e) {
                LOGGER.error("Wrong service check user",e);
                throw new TrackerServiceException("Wrong service update role",e);
            }
        } else {
            throw new TrackerServiceException("Wrong data");
        }
    }

//    public boolean deposit(String sum, String type, String login, BigDecimal oldSum) throws TrackerServiceException {
//        boolean status = false;
//        try{
//            if(doubleValidator.isValidate(sum) && depositValidator.isValidate(type)){
//                double sumDeposit = Double.parseDouble(sum);
//                if(sumDeposit >= 5){
//                    BigDecimal totalSum = oldSum.add(BigDecimal.valueOf(sumDeposit));
//                    userDao = new UserDaoJdbcImpl();
//                    status = userDao.deposit(login, totalSum);
//                }
//            }
//        } catch (TrackerDBException e){
//            LOGGER.error("Wrong service deposit to account user",e);
//            throw new TrackerServiceException("Wrong service deposit to account user",e);
//        }
//        return status;
//    }

}
