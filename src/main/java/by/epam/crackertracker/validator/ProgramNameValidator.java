package by.epam.crackertracker.validator;

public class ProgramNameValidator implements ValidatorI {
    public static final int MAX_LENGTH = 40;
    public static final int MIN_LENGTH = 2;

    @Override
    public boolean isValidate(String text){
        boolean status = false;

        if(text!= null && !text.isEmpty()){
            if(text.length() <= MAX_LENGTH && text.length() >= MIN_LENGTH){
                status = true;
            }
        }
        return status;
    }


}
