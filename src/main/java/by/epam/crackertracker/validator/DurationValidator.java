package by.epam.crackertracker.validator;

import org.springframework.stereotype.Component;

@Component
public class DurationValidator implements ValidatorI {
    public static final String NUMBER_REGEX = "[0-9]{1,3}";

    @Override
    public boolean isValidate(String str) {
        boolean status = false;
        if(str != null && !str.isEmpty()){
            status = str.matches(NUMBER_REGEX);
        }
        return status;
    }
}
