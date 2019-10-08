/**
 * it's a class for project parameters and attributes constant
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.util;

public class ParameterConstant {

    public static final String USER = "user";

    private ParameterConstant(){}

    //COMMON PARAMETERS
    public static final String PARAM_ID = "id";
    public static final String NAME = "name";
    public static final String TEXT = "text";
    public static final String DATE = "date";

    //USER PARAMETERS
    public static final String PASSWORD = "password";
    public static final String SURNAME = "surname";
    public static final String LOGIN = "login";
    public static final String GENDER = "sex";
    public static final String BIRTHDAY = "birthday";
    public static final String EMAIL = "email";
    public static final String REGISTR_DATE = "registrdate";
    public static final String PARAM_LOGIN = "login";
    public static final String PARAM_PASSWORD = "password";
    public static final String MONEY = "money";
    public static final String STATUS = "status";
    public static final String ACTIVE = "active";
    public static final String ATTRIBUTE_AVATAR = "avatar";

    //ADVICE PARAMETERS
    public static final String TEXT_ADVICE = "message";
    public static final String ID_ADVICE = "idadvices";

    //REVIEW PARAMETERS
    public static final String ID_REVIEW = "idreview";
    public static final String SHOW_REVIEW = "show_review";
    public static final int SHOW = 1;
    public static final int SHOW_DELETED = 0;

    //PRODUCT PARAMETERS
    public static final String PRODUCT_CALORIES = "calories";
    public static final String PRODUCT_ID = "idproducts";
    public static final String PRODUCT_CARBS = "carbs";
    public static final String PRODUCT_FATS = "fats";
    public static final String PRODUCT_PROTEINS = "proteins";

    public static final String START_PAGE = "startPage";


    public static final String PARAM_NAME = "username";
    public static final String PARAM_SURNAME = "usersurname";
    public static final String PARAM_EMAIL = "email";
    public static final String PARAM_BIRTHDAY = "birthday";
    public static final String PARAM_OLD_PASSWORD = "oldpassword";
    public static final String PARAM_NEW_PASSWORD = "newpassword";
    public static final String PARAM_NEW_PASSWORD_CHECK = "newpasswordCheck";
    public static final String PARAM_ADVICE = "advice";
    public static final String PARAM_GENDER = "gender";
    public static final String PARAM_ID_PRODUCT = "idproduct";
    public static final String PARAM_NAME_PRODUCT = "nameProduct";
    public static final String PARAM_CALORIES_PRODUCT = "caloriesProduct";
    public static final String PARAM_PROTEINS_PRODUCT = "proteinsProduct";
    public static final String PARAM_FATS_PRODUCT = "fatsProduct";
    public static final String PARAM_CARBS_PRODUCT = "carbsProduct";
    public static final String PARAM_TOPIC = "topic";
    public static final String PARAM_TEXT = "text";
    public static final String PARAM_BALANCE = "balance";
    public static final String PARAM_SUM_DEPOSIT = "sum";
    public static final String PARAM_TYPE = "type";
    public static final String PARAM_MIN_CALORIES = "minCalories";
    public static final String PARAM_MAX_CALORIES = "maxCalories";
    public static final String PARAM_AGE = "age";
    public static final String PARAM_ACTIVE = "active";
    public static final String PARAM_WEIGHT = "weight";
    public static final String PARAM_HEIGHT = "height";
    public static final String PARAM_FEMALE = "Female";
    public static final String PARAM_MALE = "Male";
    public static final String PARAM_ACT1 = "act1";
    public static final String PARAM_ACT2 = "act2";
    public static final String PARAM_ACT3 = "act3";
    public static final String PARAM_ACT4 = "act4";
    public static final String PARAM_FILE = "file";
    public static final String PARAM_NAME_PROGR = "name";
    public static final String PARAM_DURATION = "duration";
    public static final String PARAM_COST = "cost";
    public static final String PARAM_RECIPIENT = "recipient";
    public static final String PARAM_SENDER = "sender";

    public static final String RU = "ru";
    public static final String EN = "en";
    public static final String BY = "by";
    public static final String SUBMIT_BY = "BY";
    public static final String SUBMIT_EN = "US";
    public static final String SUBMIT_RU = "RU";

    public static final String INPUT_MESSAGE = "input";
    public static final String OUTPUT_MESSAGE = "output";

    public static final String ATTRIBUTE_ROLE = "role";
    public static final String SUBMIT_LANGUAGE = "langv";
    public static final String ATTRIBUTE_LANGUAGE = "lang";
    public static final String ATTRIBUTE_ERROR_AUTH = "errorLoginPassMessage";
    public static final String ATTRIBUTE_CURRENT_PAGE = "currentPage";
    public static final String ATTRIBUTE_UPDATE = "editmessage";
    public static final String ATTRIBUTE_RES_ADD = "addmessage";
    public static final String ATTRIBUTE_ADD_DELETE_MESS = "addmessagedelete";
    public static final String ATTRIBUTE_LIST_ADVICES = "listAdvice";
    public static final String ATTRIBUTE_LIST_USERS = "listUser";
    public static final String ATTRIBUTE_LIST_USERS_GENDER = "listUserGender";
    public static final String ATTRIBUTE_LIST_USERS_ROLE = "listUserRole";
    public static final String ATTRIBUTE_LIST_REVIEW = "listReview";
    public static final String ATTRIBUTE_LIST_MESSAGE_IN = "listMessageInput";
    public static final String ATTRIBUTE_LIST_MESSAGE_OUT = "listMessageOutput";
    public static final String ATTRIBUTE_LIST_CURATOR  = "mapCurator";
    public static final String ATTRIBUTE_LIST_PRODUCTS = "listProducts";
    public static final String ATTRIBUTE_LIST_PRODUCTS_BUCKET = "listProductsBucket";
    public static final String ATTRIBUTE_ROUTE = "typeRoute";
    public static final String ATTRIBUTE_LIST_PRODUCTS_BY_CALORIES = "listProductsByCalories";
    public static final String ATTRIBUTE_RESULT = "result";
    public static final String ATTRIBUTE_RESULT_CALORIES = "result_calories";
    public static final String ATTRIBUTE_LIST_PROGRAMS = "listPrograms";
    public static final String ATTRIBUTE_LIST_PROGRAMS_CURATOR = "listProgramsCurator";
    public static final String ATTRIBUTE_PAGE_MESSAGE = "mess";
    public static final String ATTRIBUTE_SUCCESS_UPDATE = "1";
    public static final String ATTRIBUTE_SUCCESS_INSERT = "2";
    public static final String ATTRIBUTE_SUCCESS_DELETE = "3";
    public static final String ATTRIBUTE_WRONG_ACTION = "4";
    public static final String ATTRIBUTE_SUCESS_REGISTER = "5";
    public static final String ATTRIBUTE_SUCESS_LOGIN = "6";
    public static final String ATTRIBUTE_WRONG_LOGIN = "7";
    public static final String ATTRIBUTE_PROGRAM_COMPONENT  = "programComponent";
    public static final String ATTRIBUTE_SHOW_REVIEW = "show_review";


    public static final String MESAGE_WRONG_AUTH = "messageAuth";
    public static final String MESSAGE_ERROR_REGIST = "Please, check it";
    public static final String MESSAGE_CONGRAT = "congratulations!";
    public static final String MESSAGE_ERROR_DOWNLOAD = "errorDownload";
    public static final String WRONG = "Wrong download";
    public static final String WRONG_DATA = "wrongData";
    public static final String WRONG_DATA_PASS = "wrongData";
    public static final String WRONG_DELETE_USER = "addmessagedelete";
    public static final String MESSAGE_UPDATE_PRODUCT = "messageUpdateProduct";
    public static final String MESSAGE_DELETE_PRODUCT =  "messageDeleteProduct";
    public static final String MESSAGE_INSERT_PRODUCT = "messageInsertProduct";
    public static final String MESSAGE_DELETE_REVIEW = "messageReview";
    public static final String MESSAGE_UPDATE_ROLE = "messageUpdateRole";
    public static final String MESSAGE_DEPOSIT = "depositMessage";
    public static final String MESSAGE_SEND_REVIEW = "messageReview";
    public static final String MESSAGE_SUBSCRIBERS = "listSubscr";
    public static final String LIST_CURATOR_SUBSCRIBERS = "listCuratorSubscr";
    public static final String MESSAGE_PROGRAMS_NAME = "listPrograms";
    public static final String MESSAGE_CURATOR_PROGRAMS_NAME = "listProgramsCurator";
    public static final String MESSAGE_HAVE_SUBS = "messageSubsc";
    public static final String MESSAGE_PROGRAM_NAME = "messageProgramName";
    public static final String MESSAGE_PRODUCT = "messageProduct";
    public static final String MESSAGE_DONT_HAVE_SUBS = "you dont have subscribers";
    public static final String MESSAGE_WRONG_PRODUCTS = "messageWrongProduct";
    public static final String MESSAGE_PRODUCT_TO_PROGRAM = "messageInsertProduct";

    public static final String ATTRIBUTE_PREV_PAGE = "previousPage";
    public static final String ATTRIBUTE_NEXT_PAGE = "nextPage";
    public static final String ATTRIBUTE_RES_PAGE = "currPage";

    public static final String SORTED_INCREASE_CALORIES = "INCREASE_CALORIES";
    public static final String SORTED_DECREASE_CALORIES = "DECREASE_CALORIES";
    public static final String SORTED_NOTHING = "NOTHING";
    public static final String SORTED_INCREASE_NAME = "INCREASE_NAME";
    public static final String SORTED_DECREASE_NAME = "DECREASE_NAME";

    public static final String ATTRIBUTE_BUTTON_NAME = "buttonName";
    public static final String ATTRIBUTE_DELETE_TYPE = "delete";
    public static final String ATTRIBUTE_RESTORE_TYPE = "restore";
    public static final String TYPE_0 = "0";
    public static final String TYPE_1 = "1";
    public static final String ATTRIBUTE_NAME_PROGRAM = "nameProgramName";
    public static final String ATTRIBUTE_COST_PROGRAM = "costProgramName";
    public static final String ATTRIBUTE_DUR_PROGRAM = "durProgramName";
    public static final String PARAM_DAY = "day";
    public static final String PARAM_TIME = "time";
    public static final String PARAM_NUM_PRODUCT = "numProduct";
    public static final String PARAM_NUM_PROGRAM = "idProgram";
    public static final String PARAM_PORTIONS = "portions";





}
