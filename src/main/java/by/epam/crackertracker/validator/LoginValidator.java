/**
 * it's a class for validate login user
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.validator;

import org.springframework.stereotype.Component;

@Component
public class LoginValidator implements ValidatorI {

    private static final String LOGIN_REGEX = "[(\\w)-]{3,16}";

    public boolean isValidate(String login){
        boolean status = false;
        if(login != null && !login.isEmpty()) {
            status = login.matches(LOGIN_REGEX);
        }
        return status;
    }
}
