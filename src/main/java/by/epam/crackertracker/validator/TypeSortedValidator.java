package by.epam.crackertracker.validator;

import by.epam.crackertracker.util.ParameterConstant;
import org.springframework.stereotype.Component;

@Component
public class TypeSortedValidator implements ValidatorI {

    @Override
    public boolean isValidate(String str) {
        boolean status = false;
        if((str != null && !str.isEmpty()) && (str.equals(ParameterConstant.SORTED_DECREASE_CALORIES) ||
                str.equals(ParameterConstant.SORTED_INCREASE_CALORIES) || str.equals(ParameterConstant.SORTED_NOTHING) ||
                str.equals(ParameterConstant.SORTED_DECREASE_NAME) ||
                str.equals(ParameterConstant.SORTED_INCREASE_NAME))){
                status = true;
            }
        return status;
    }
}
