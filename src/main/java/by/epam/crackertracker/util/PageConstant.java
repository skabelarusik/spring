/**
 * it's a class for project page constant
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.util;

import java.net.URI;

public class PageConstant {



    private PageConstant(){}

    public static final String PATH_PAGE_LOGIN = "login";
    public static final String PATH_PAGE_MAIN_USER = "user";
    public static final String PATH_PAGE_MAIN_ADMIN = "admin";
    public static final String PATH_PAGE_MAIN_CURATOR = "curator";
    public static final String PATH_PAGE_MAIN_SUPERUSER = "superuser";
    public static final String PATH_PAGE_MAIN_INDEX = "index";
    public static final String PATH_PAGE_EDIT_USER = "editUser";


    public static final String PATH_RESULT_USER = "resultUser";
    public static final String PATH_RESULT_PRODUCT = "resultProduct";
    public static final String PATH_RESULT_PROGRAMS = "resultPrograms";
    public static final String PATH_RESULT_ADVICE = "resultAdvice";
    public static final String PATH_SEND_MESSAGE = "send_message";
    public static final String PATH_RESULT_REVIEW = "resultReview";
    public static final String PATH_RESULT_REVIEW_MAIN = "reviewMain";
    public static final String PATH_RESULT_PROGRAM = "resultProgram";

    public static final String PATH_RESULT_PROGRAM_NAME = "resultProgramName";
    public static final String PATH_PAGE_EDIT_PROGRAM = "editProgram";


    public static final String PATH_LIST_USER = "listUser";
    public static final String PATH_PAGE_REGISTER = "register";
    public static final String PATH_PAGE_ERROR = "error";
    public static final String DEFAULT_AVATAR_PATH = "/picture/user.png";
    public static final String PATH_PAGE_SEND_REVIEW = "send_review";
    public static final String PATH_PAGE_CALCULATOR = "/jsp/calculator.jsp";
    public static final String PATH_PAGE_MESSAGE = "resultMessage";
    public static final String PATH_PAGE_PRODUCT = "resultProduct";
    public static final String PATH_PAGE_SUBSCRIPTION = "resultSubscription";
    public static final String PATH_PAGE_INDEX = "/";
    public static final String PAGE_ABOUT = "about";
    public static final String PATH_DEPOSIT = "deposit";
    public static final String PATH_PICTURE = "/picture/**";
    public static final String PATH_PAGE_SERVICE = "about";
    public static final String URI_BUY = "/buy";
    public static final String PAGE_NEWS = "news";


    public static final String URI_SELECT = "/select";
    public static final String URI_SELECT_USER_BY_STATUS = "/select_by_status";
    public static final String URI_SELECT_USER_BY_GENDER = "/select_by_gender";
    public static final String URI_SELECT_BY_CALORIES = "/select_by_calories";
    public static final String URI_SELECT_DEL = "/select_del";
    public static final String URI_SELECT_CURATOR = "/select_curator";
    public static final String URI_SELECT_CURATOR_DEL = "/select_del_curator";
    public static final String URI_SUBSCRIPTION = "/subscription";
    public static final String URI_SEARCH_PRODUCT = "/search";
    public static final String NEWS = "/news";



    public static final String URI_UPDATE_USER = "/update";
    public static final String URI_UPDATE_USER_ROLE = "/update_role";
    public static final String UPDATE_PASS = "/update_pass";
    public static final String URI_FULL_UPDATE_USER = "/user/update";


    public static final String URI_ADD = "/add";
    public static final String URI_DELETE = "/delete";
    public static final String URI_SHOW = "/show";
    public static final String URI_SHOW_MAIN = "/show_main";

    public static final String URI_SHOW_DEL = "/show_del";
    public static final String URI_SEND_REVIEW = "/send";
    public static final String URI_DELETE_BY_ID = "/delete_by_id";
    public static final String URI_SEND_MESS = "/send_mes";


    public static final String URI_LOGIN = "/login";
    public static final String LOGOUT = "/logout";
    public static final String LOGOUT_SUCCESS ="/logout-success" ;
    public static final String URI_REGISTER = "/registration";
    public static final String URI_RESULT = "/result";
    public static final String URI_DEP = "/deposit";
    public static final String URI_BACK = "/back";
    public static final String URI_REQUEST = "/request";
    public static final String URI_ABOUT = "/about";
    public static final String URI_INPUT_MESSAGE = "/input";
    public static final String URI_OUTPUT_MESSAGE = "/output";
    public static final String URI_MAIN = "/main";
    public static final String URI_RESOURCES = "/uui/**";
    public static final String URI_USER_REGISTER = "/user/registration";
    public static final String URI_USER_DEP = "/user/deposit";
    public static final String URI_USER_REQUEST = "/user/request";





    public static final String PATH_MESSAGE = "/message";
    public static final String PATH_REVIEW = "/review";
    public static final String PATH_ADVICE = "/advice";
    public static final String PATH_PROGRAM = "/program";
    public static final String PATH_PROGRAM_NAME = "/program_name";
    public static final String REQUEST_USER = "/user";
    public static final String URI_PRODUCT = "/product";
    public static final String CALCULATE = "/calculate";
    public static final String CALCULATE_CALORIES = "/calculate_calories";
    public static final String PATH_BUCKET = "/bucket";
    public static final String BUCKET_CALCULATE = "/bucket/calculate";


    public static final String URI_LANG = "/lang";
    public static final String URI_LANG_BY = "/lang/by";
    public static final String URI_LANG_RU = "/lang/ru";
    public static final String URI_LANG_EN = "/lang/en";

    public static final String PROJECT = "by.epam.crackertracker";


    public static final String SECUR_PATH_UPDATE_ROLE = "/user/update_role";
    public static final String SECUR_PATH_REVIEW_DELETE = "/review/delete_by_id";
    public static final String SECUR_PATH_REVIEW_DEL = "/review/delete";

    public static final String SECUR_PATH_ADVICE_ADD = "/advice/add";
    public static final String SECUR_PATH_ADVICE_DELETE = "/advice/delete";
    public static final String SECUR_PATH_ADVICE_SELECT = "/advice/select";
    public static final String SECUR_PATH_PRODUCT_UPDATE = "/product/update";
    public static final String SECUR_PATH_PRODUCT_DELETE = "/product/delete";
    public static final String SECUR_PATH_PRODUCT_ADD = "/product/add";
    public static final String SECUR_PATH_PRODUCT_SELECT = "/product/select";
    public static final String SECUR_PATH_PRODUCT_SEARCH = "/product/search";
    public static final String PATH_REVIEW_SHOW = "/review/show";
    public static final String PATH_REVIEW_SHOW_DEL = "/review/show_del";
    public static final String PATH_PROGRAM_NAME_SELECT = "/program_name/select";
    public static final String PATH_PROGRAM_NAME_SELECT_DEL = "/program_name/select_del";
    public static final String PATH_PROGRAM_NAME_SELECT_CURATOR = "/program_name/select_curator";
    public static final String PATH_PROGRAM_NAME_SELECT_CURATOR_DEL = "/program_name/select_del_curator";
    public static final String PATH_PROGRAM_NAME_ADD = "/program_name/add";
    public static final String PATH_PROGRAM_NAME_DELETE = "/program_name/delete";
    public static final String URI_SUBS_SELEC = "/subscription/select";
    public static final String PATH_PROGRAM_SELECT = "/program/select";
    public static final String PATH_PROGRAM_ADD = "/program/add";
    public static final String PATH_PROGRAM_DELETE = "/program/delete";







    public static final String SECUR_PATH_REVIEW_SEND = "/review/send";
    public static final String URI_INPUT_MESSAGE_FULL = "/message/input";
    public static final String URI_OUTPUT_MESSAGE_FULL = "/message/output";
    public static final String URI_OUTPUT_MESSAGE_SEND = "/message/send";
    public static final String URI_MESSAGE_SEND_FORM = "/message/send_mes";
    public static final String URI_MESSAGE_DELETE = "/message/delete";
    public static final String PATH_BUCKET_ADD = "/bucket/add";
    public static final String URI_SHOW_HISTORY = "/show_history";












}
