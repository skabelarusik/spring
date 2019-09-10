package by.epam.crackertracker.validator;

public class PositiveIntValidator {

    public boolean isValidate(int str) {
        boolean status = true;
        if(str < 0){
            status = false;
        }
        return status;
    }
}
