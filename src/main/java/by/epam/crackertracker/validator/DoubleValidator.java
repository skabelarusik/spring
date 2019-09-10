/**
 * it's a class for validate double parameters
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.validator;

public class DoubleValidator implements ValidatorI {

    public static final String PRODUCT_PARAM_REGEX = "([0-9]*[.]?)?[0-9]{0,1}";

    //null is validate
    @Override
    public boolean isValidate(String str) {
        boolean status = false;

        if(str!= null && !str.isEmpty() && str.length()<6){
            status = str.matches(PRODUCT_PARAM_REGEX);
        } else {
            if(str == null || str.isEmpty()){
                status = true;
            }
        }
        return status;
    }

}
