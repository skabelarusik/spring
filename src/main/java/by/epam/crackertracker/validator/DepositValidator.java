/**
 * it's a class for validate deposit type
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.validator;

import by.epam.crackertracker.entity.Payment;

public class DepositValidator implements ValidatorI {
    @Override
    public boolean isValidate(String str) {
        boolean status = false;

        if(str != null && !str.isEmpty()){
            if(str.equalsIgnoreCase(Payment.VISA.toString()) || str.equalsIgnoreCase(Payment.KIWI.toString()) ||
                str.equalsIgnoreCase(Payment.SKRILL.toString()) || str.equalsIgnoreCase(Payment.WEBMONEY.toString())){
                status = true;
            }
        }
        return status;
    }
}
