package by.epam.crackertracker.validator;

public class PortionsValidator implements ValidatorI {
    public static final String PRODUCT_PARAM_REGEX = "([0-9]{1}[.]?)?[0-9]{0,1}";

    //null is validate
    @Override
    public boolean isValidate(String str) {
        boolean status = false;

        if(str!= null && !str.isEmpty()){
            status = str.matches(PRODUCT_PARAM_REGEX);
        }
        return status;
    }
}
