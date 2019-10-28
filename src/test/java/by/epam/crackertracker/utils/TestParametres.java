package by.epam.crackertracker.utils;

public class TestParametres {

    private TestParametres(){}

    //common parametres
    public static final String EMPTY = "";
    public static final String GOOD_ID = "5";
    public static final String WRONG_ID = "-1";
    public static final int START_PAGE = 1;
    public static final String LOGIN_FIRST = "Andrey";
    public static final String LOGIN_SECOND = "Tatsiana";

    //paths pages roles
    public static final String REDIRECT_ADMIN = "redirect:admin";
    public static final String ADMIN = "admin";
    public static final String REDIRECT = "redirect:";

    //for program name controller test
    public static final String PROGRAM_NAME = "program1";
    public static final String DURATION = "3";
    public static final String COST = "12";

    //for message controller test
    public static final String MESSAGE = "How are you?";
    public static final String TOPIC = "Hello!?";

    // for advice controller test

    public static final String VALIDE_TEXT = "testtes t";

    public static final String LONGER = "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
      "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
        "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
 "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
     "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
            "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc" +
         "asdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxcccccccccccccccasdsadddddddddddddddddddddassssssssssszxccccccccccccccc";

}
