package by.epam.crackertracker.resources;

import by.epam.crackertracker.entity.*;

public class ParametresTest {

    public static final String NULL = null;
    public static final String EMPTY = "";
    public static final String ONE_SIZE = "s";
    public static final String BIG_SIZE_400 = "asdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasd" +
            "asdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasd" +
            "asdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasd" +
            "asdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasd" +
            "asdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasd" +
            "asdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasd" +
            "asdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasd" +
            "asdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasd" +
            "asdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasd" +
            "asdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasd" +
            "asdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasd" +
            "asdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasd" +
            "asdasdasdasdasdasdasdasdasdasdasdasdasdasdas@sad.dasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasdasd";
    public static final String TEN_SIZE = "asddsaasdd";

    public static final String CORRECT_DATE = "1999-10-10";
    public static final String UNCORRECT_DATE = "1810-10-10";
    public static final String WRONG_DATE = "10-10-1910";

    public static final String CORRECT_COST = "25";
    public static final String UNCORRECT_COST = "1234567";
    public static final String NEGATIVE_COST = "-1";

    public static final String CORRECT_DAY = MealDay.FRIDAY.name();
    public static final String UNCORRECT_DAY = "MON DAY";

    public static final String CORRECT_TYPE_DEPOSIT = Payment.KIWI.toString();
    public static final String UNCORRECT_TYPE_DEPOSIT = "MAESTRO";

    public static final String CORRECT_DOUBLE = "25.4";
    public static final String UNCORRECT_DOUBLE = "1234567123.99";
    public static final String NEGATIVE_DOUBLE = "-25.4";

    public static final String GOOD_EMAIL = "ber-linn@tut.by";
    public static final String BAD_EMAIL = "@.by";
    public static final String BAD_EMAIL_2 = "asd.by";

    public static final String CORRECT_GENDER = Gender.MALE.name();
    public static final String UNCORRECT_GENDER = "PAREN";

    public static final String CORRECT_MESSAGE_TYPE = "output";
    public static final String UNCORRECT_MESSAGE_TYPE = "PAREN";

    public static final String BIG_INT = "55000";

    public static final String RUSSIA_NAME = "Андрей";
    public static final String CORRECT_PASSWORD = "Anderre1e23";
    public static final String CORRECT_DOUBLE_2 = "5.4";

    public static final int CORRECT_INT = 4;
    public static final int UNCORRECT_INT = -4;

    public static final String GOOD_INT = "2";
    public static final String NEGATIVE_INT = "-4";

    public static final String CORRECT_ROLE = Role.USER.name();
    public static final String UNCORRECT_ROLE = "admen";

    public static final String CORRECT_TIME = MealTime.SECOND_LUNCH.name();
    public static final String UNCORRECT_TIME = "obed";

    public static final String CORRECT_TYPE_SORTED = "DECREASE_CALORIES";
    public static final String UNCORRECT_TYPE_SORTED = "no";














}
