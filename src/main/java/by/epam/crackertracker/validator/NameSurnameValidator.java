/**
 * it's a class for validate users name or surname
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.validator;

public class NameSurnameValidator implements ValidatorI {

    private static final String NAME_REGEX = "[a-zA-Zà-ÿÀ-ß¸¨]{2,35}";

    @Override
    public boolean isValidate(String name){
        boolean status = false;
        // name == null is validate
        if(name == null || name.isEmpty()){
            status = true;
        } else {
        status = name.matches(NAME_REGEX);
        }
        return status;
    }
}
