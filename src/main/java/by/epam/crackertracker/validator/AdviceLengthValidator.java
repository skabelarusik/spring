/**
 * it's a class for validate length advice
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.validator;

public class AdviceLengthValidator implements ValidatorI {
    public static final int MIN_LENGTH = 2;
    public static final int MAX_LENGTH = 100;

    @Override
    public boolean isValidate(String text){
        boolean status = false;
        if(text!= null && !text.isEmpty() && text.length() <= MAX_LENGTH && text.length() >= MIN_LENGTH){
            status = true;
        }
        return status;
    }


}
