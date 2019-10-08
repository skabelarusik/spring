/**
 * it's a class for validate type message
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.validator;

import by.epam.crackertracker.util.ParameterConstant;
import org.springframework.stereotype.Component;

@Component
public class MessageValidator implements ValidatorI {

    @Override
    public boolean isValidate(String str) {
        boolean status = false;
        if(str != null && !str.isEmpty() && (str.equals(ParameterConstant.INPUT_MESSAGE)
                || str.equals(ParameterConstant.OUTPUT_MESSAGE))){
            status = true;
        }
        return status;
    }
}
