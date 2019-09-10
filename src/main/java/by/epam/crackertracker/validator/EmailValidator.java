/**
 * it's a class for validate email
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.validator;

public class EmailValidator implements ValidatorI {
    private static final String EMAIL_REGEX = "[-0-9a-zA-Z.+_]{2,64}+@[-0-9a-zA-Z.+_]+\\.[a-zA-Z]{2,4}";
    private static final int MAX_EMAIL_SIZE = 254;

    @Override
    public boolean isValidate(String str) {
        boolean status = false;
        if(str != null && !str.isEmpty() && str.length() <= MAX_EMAIL_SIZE){
            status = str.matches(EMAIL_REGEX);
        }
        return status;
    }
}
