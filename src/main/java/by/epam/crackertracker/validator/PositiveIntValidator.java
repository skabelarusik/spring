package by.epam.crackertracker.validator;

import org.springframework.stereotype.Component;

@Component
public class PositiveIntValidator {

    public boolean isValidate(int str) {
        boolean status = true;
        if(str < 0){
            status = false;
        }
        return status;
    }
}
