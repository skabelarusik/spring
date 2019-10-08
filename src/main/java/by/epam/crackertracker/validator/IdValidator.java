/**
 * it's a class for validate int parameters
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.validator;

import org.springframework.stereotype.Component;

@Component
public class IdValidator implements ValidatorI {
    public static final String NUMBER_REGEX = "[0-9]{1,9}";

    @Override
    public boolean isValidate(String str) {
        boolean status = false;
        if(str != null && !str.isEmpty()){
            status = str.matches(NUMBER_REGEX);
        }
        return status;
    }
}
