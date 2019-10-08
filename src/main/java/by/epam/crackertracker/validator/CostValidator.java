/**
 * it's a class for validate cost
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.validator;

import org.springframework.stereotype.Component;

@Component
public class CostValidator implements ValidatorI {
    public static final String COST_PARAM_REGEX = "([0-9]{1,5}[.]?)?[0-9]{0,1}";

    @Override
    public boolean isValidate(String str) {
        boolean status = false;
        if(str!=null && !str.isEmpty()){
            status = str.matches(COST_PARAM_REGEX);
        }
        return status;
    }

}
