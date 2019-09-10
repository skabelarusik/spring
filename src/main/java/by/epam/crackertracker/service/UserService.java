/**
 * service for working with user dao
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.service;

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

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UserService {
    private static final Logger LOGGER = Logger.getRootLogger();
    public static final int MAX_TABLE_USERS = 11;

    public User checkUser(String loginValue, String passValue) throws TrackerServiceException {
        User user = null;
        UserDaoJdbcImpl dao = new UserDaoJdbcImpl();
        LoginValidator validator = new LoginValidator();
        PasswordValidator validator1 = new PasswordValidator();
        try{
            if(validator.isValidate(loginValue) && validator1.isValidate(passValue)) {
                user = dao.selectByLogin(loginValue, passValue);
            }
        } catch (TrackerDBException e){
            LOGGER.error(" Wrong service check user",e);
            throw new TrackerServiceException("Wrong service check user",e);
        }
        return user;
    }

    public boolean registerUser(String login, String pass, String name, String surname, Gender gender, String email,
                               String dateOfBirth) throws TrackerServiceException {
        LoginValidator loginValidator = new LoginValidator();
        PasswordValidator passwordValidator = new PasswordValidator();
        EmailValidator emailValidator = new EmailValidator();
        BirthdayValidator birthdayValidator = new BirthdayValidator();
        NameSurnameValidator nameSurnameValidator = new NameSurnameValidator();
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

    public Map<String,Double> selectRandomUser() throws TrackerServiceException {
        UserDaoJdbcImpl dao = new UserDaoJdbcImpl();
        Map<String, Double> mapCurators;
        try {
            mapCurators = dao.selectTopFiveCurators();
        } catch (TrackerDBException e) {
            LOGGER.error("Wrong service select random users", e);
            throw new TrackerServiceException("Wrong service select random users", e);
        }
        return mapCurators;
    }

    public List<User> selectAllUsers(HttpServletRequest request) throws TrackerServiceException {
        UserDaoJdbcImpl dao = new UserDaoJdbcImpl();
        String currentPage = request.getParameter(ParameterConstant.ATTRIBUTE_NEXT_PAGE);
        String type = request.getParameter(ParameterConstant.PARAM_TYPE);
        List<User> userList;
        if(type == null){
            type = ParameterConstant.SORTED_NOTHING;
        }
        if(currentPage == null){
            int intPage = 1;
            try {
                userList = dao.selectAll(intPage, type);
            } catch (TrackerDBException e) {
                LOGGER.error(" Wrong service select all users",e);
                throw new TrackerServiceException("Wrong service select all users",e);
            }
            request.setAttribute(ParameterConstant.ATTRIBUTE_RES_PAGE, intPage);
            if(userList.size() == MAX_TABLE_USERS){
                request.setAttribute(ParameterConstant.ATTRIBUTE_NEXT_PAGE, intPage + 1);
            }
        } else {
            int intPage = Integer.parseInt(currentPage);
            try {
                userList = dao.selectAll(intPage, type);
            } catch (TrackerDBException e) {
                LOGGER.error("Wrong service  select all users",e);
                throw new TrackerServiceException("Wrong service select all users",e);
            }
            request.setAttribute(ParameterConstant.ATTRIBUTE_RES_PAGE, intPage);
            if(userList.size() == MAX_TABLE_USERS){
                request.setAttribute(ParameterConstant.ATTRIBUTE_NEXT_PAGE, intPage + 1);
            }
            if(intPage > 1){
                request.setAttribute(ParameterConstant.ATTRIBUTE_PREV_PAGE, intPage - 1);
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
        return newUserList;
    }

    public boolean updateDataUser(String login, String name, String surname,
                                  String email, String birthday) throws TrackerServiceException {
        boolean status = false;
        EmailValidator emailValidator = new EmailValidator();
        BirthdayValidator birthdayValidator = new BirthdayValidator();
        NameSurnameValidator nameSurnameValidator = new NameSurnameValidator();
        LoginValidator loginValidator = new LoginValidator();
        try{
            if(emailValidator.isValidate(email) && birthdayValidator.isValidate(birthday) && nameSurnameValidator.isValidate(name) &&
                    nameSurnameValidator.isValidate(surname) && loginValidator.isValidate(login)) {
                UserDaoJdbcImpl dao = new UserDaoJdbcImpl();
                User user = new User(login);
                user.setName(name);
                user.setSurname(surname);
                user.setEmail(email);
                user.setBirthDate(LocalDate.parse(birthday));
                status = dao.updateUser(user);
            }
        } catch (TrackerDBException e){
            LOGGER.error("Wrong service update user",e);
            throw new TrackerServiceException("Wrong service update user",e);
        }

        return status;
    }

    public boolean updatePasswordUser(String login, String oldPass, String currentPass,
                                      String newPass, String newPassCheck) throws TrackerServiceException {
        UserDaoJdbcImpl dao = new UserDaoJdbcImpl();
        LoginValidator loginValidator = new LoginValidator();
        PasswordValidator passwordValidator = new PasswordValidator();
        boolean status = false;
        try{
            if(loginValidator.isValidate(login) && passwordValidator.isValidate(oldPass) && passwordValidator.isValidate(currentPass) &&
                    passwordValidator.isValidate(newPass) && passwordValidator.isValidate(newPassCheck) &&
                    oldPass.equals(currentPass) && newPass.equals(newPassCheck)) {
                status = dao.updatePasswordUser(login, newPass);
            }
        } catch (TrackerDBException e){
            LOGGER.error("Wrong service update password user",e);
            throw new TrackerServiceException("Wrong service update password user",e);
        }
        return status;
    }

    public boolean sendAvatar(String login, String path) throws TrackerServiceException {
        LoginValidator loginValidator = new LoginValidator();
        PathAvatarValidator pathAvatarValidator = new PathAvatarValidator();
        UserDaoJdbcImpl dao;
        boolean status = false;
        try{
            if(loginValidator.isValidate(login) && pathAvatarValidator.isValide(login, path)){
                dao = new UserDaoJdbcImpl();
                status = dao.updateFilePath(login, path);
            }
        } catch (TrackerDBException e){
            LOGGER.error("Wrong service send avatar user",e);
            throw new TrackerServiceException("Wrong service send avatar user",e);
        }
        return status;
    }

    public List<User> selectUsersByRole(HttpServletRequest request, String role) throws TrackerServiceException {
        UserDaoJdbcImpl dao = new UserDaoJdbcImpl();
        RoleValidator roleValidator = new RoleValidator();
        TypeSortedValidator typeSortedValidator = new TypeSortedValidator();
        String currentPage = request.getParameter(ParameterConstant.ATTRIBUTE_NEXT_PAGE);
        String type = request.getParameter(ParameterConstant.PARAM_TYPE);
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
            request.setAttribute(ParameterConstant.ATTRIBUTE_RES_PAGE, intPage);
        } else {
            intPage = Integer.parseInt(currentPage);
            request.setAttribute(ParameterConstant.ATTRIBUTE_RES_PAGE, intPage);
            if(intPage > 1){
                request.setAttribute(ParameterConstant.ATTRIBUTE_PREV_PAGE, intPage - 1);
            }
        }
        try {
            userList = dao.selectByRole(intPage, type, role);
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
            request.setAttribute(ParameterConstant.ATTRIBUTE_NEXT_PAGE, intPage + 1);
        }
        return newUserList;
    }

    public List<User> selectUsersByGender(HttpServletRequest request, String gender) throws TrackerServiceException {
        UserDaoJdbcImpl dao = new UserDaoJdbcImpl();
        GenderValidator genderValidator = new GenderValidator();
        TypeSortedValidator typeSortedValidator = new TypeSortedValidator();
        String currentPage = request.getParameter(ParameterConstant.ATTRIBUTE_NEXT_PAGE);
        String type = request.getParameter(ParameterConstant.PARAM_TYPE);
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
            userList = dao.selectByGender(intPage, type, gender);
            request.setAttribute(ParameterConstant.ATTRIBUTE_RES_PAGE, intPage);
            if(userList.size() == MAX_TABLE_USERS){
                request.setAttribute(ParameterConstant.ATTRIBUTE_NEXT_PAGE, intPage + 1);
            }
        } else {
            int intPage = Integer.parseInt(currentPage);
            userList = dao.selectByGender(intPage, type, gender);
            request.setAttribute(ParameterConstant.ATTRIBUTE_RES_PAGE, intPage);
            if(userList.size() == MAX_TABLE_USERS){
                request.setAttribute(ParameterConstant.ATTRIBUTE_NEXT_PAGE, intPage + 1);
            }
            if(intPage > 1){
                request.setAttribute(ParameterConstant.ATTRIBUTE_PREV_PAGE, intPage - 1);
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

    public boolean deleteUser(String login, String id) throws TrackerServiceException {
        LoginValidator loginValidator = new LoginValidator();
        IdValidator idValidator = new IdValidator();
        boolean status = false;
        if(loginValidator.isValidate(login) && idValidator.isValidate(id)){
           int idUser = Integer.parseInt(id);
           UserDaoJdbcImpl dao = new UserDaoJdbcImpl();
            try {
                status = dao.deleteByIdLogin(idUser, login);
            } catch (TrackerDBException e) {
                LOGGER.error("Wrong service delete user",e);
                throw new TrackerServiceException("Wrong service delete user",e);
            }
        }
        return status;
    }

    public boolean updateRole(String id, Role role) throws TrackerServiceException {
       IdValidator validator = new IdValidator();
       RoleValidator roleValidator = new RoleValidator();
        boolean status = false;
        UserDaoJdbcImpl dao = null;
        if (validator.isValidate(id) && roleValidator.isValidate(role.name())) {
            dao = new UserDaoJdbcImpl();
            try {
                status = dao.updateRoleUser(Integer.parseInt(id), role);
            } catch (TrackerDBException e) {
                LOGGER.error("Wrong service check user",e);
                throw new TrackerServiceException("Wrong service update role",e);
            }
        }
        return status;
    }

    public boolean deposit(String sum, String type, String login, BigDecimal oldSum) throws TrackerServiceException {
        DoubleValidator doubleValidator = new DoubleValidator();
        DepositValidator depositValidator = new DepositValidator();
        UserDaoJdbcImpl dao;
        boolean status = false;
        try{
            if(doubleValidator.isValidate(sum) && depositValidator.isValidate(type)){
                double sumDeposit = Double.parseDouble(sum);
                if(sumDeposit >= 5){
                    BigDecimal totalSum = oldSum.add(BigDecimal.valueOf(sumDeposit));
                    dao = new UserDaoJdbcImpl();
                    status = dao.deposit(login, totalSum);
                }
            }
        } catch (TrackerDBException e){
            LOGGER.error("Wrong service deposit to account user",e);
            throw new TrackerServiceException("Wrong service deposit to account user",e);
        }
        return status;
    }

}
