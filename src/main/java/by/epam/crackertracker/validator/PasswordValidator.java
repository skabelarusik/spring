/**
 * it's a class for validate password
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.validator;

public class PasswordValidator implements ValidatorI {
    private static final String PASSWORD_REGEX = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{6,20}";

    @Override
    public boolean isValidate(String pass){
        boolean status = false;

        if(pass!=null) {
            status = pass.matches(PASSWORD_REGEX);
        }

        return status;
    }
}
