package by.epam.crackertracker.validator;

public class ReviewLengthValidator implements ValidatorI{
    public static final int MIN_LENGTH = 0;
    public static final int MAX_LENGTH = 300;
    @Override
    public boolean isValidate(String str) {
        boolean status = false;
        if((str != null && !str.isEmpty()) && (str.length() > MIN_LENGTH && str.length()<=MAX_LENGTH)){
                status = true;
        }
        return status;
    }
}
