package by.epam.crackertracker.validator;

import org.springframework.stereotype.Component;

@Component
public class MinMaxCaloriesValidator implements ValidatorI {
    public static final int MIN_CALORIES = 0;
    public static final int MAX_CALORIES = 1500;
    public static final String NUMBER_REGEX = "[0-9]{1,4}";

    @Override
    public boolean isValidate(String str) {
        boolean status = false;
        if((str != null && !str.isEmpty()) && (str.matches(NUMBER_REGEX)))
        {
            int num = Integer.parseInt(str);
            if(num >= MIN_CALORIES && num <= MAX_CALORIES){
                status = true;
            }
        }
        return status;
    }
}
