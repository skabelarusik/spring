/**
 * it's a class for validate product name
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.validator;

public class ProductNameValidator implements ValidatorI{
    private static final String PRODUCT_NAME_REGEX = "[a-zA-Zà-ÿÀ-ß¸¨]{2,35}";

    @Override
    public boolean isValidate(String name){
        boolean status = false;
        if(name != null && !name.isEmpty()){
            status = name.matches(PRODUCT_NAME_REGEX);
        }
        return status;
    }
}
