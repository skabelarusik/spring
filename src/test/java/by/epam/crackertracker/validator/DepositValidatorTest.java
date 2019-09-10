/**
 * it's a class for validate deposit type
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.validator;

import by.epam.crackertracker.resources.ParametresTest;
import org.junit.Assert;
import org.junit.Test;

public class DepositValidatorTest  {
    DepositValidator validator = new DepositValidator();

    @Test
    public void isValidateEmpty() {
        Assert.assertFalse(validator.isValidate(ParametresTest.EMPTY));
    }

    @Test
    public void isValidateNull() {
        Assert.assertFalse(validator.isValidate(ParametresTest.NULL));
    }

    @Test
    public void isValidateCorrect() {
        Assert.assertTrue(validator.isValidate(ParametresTest.CORRECT_TYPE_DEPOSIT));
    }


    @Test
    public void isValidateBigSum() {
        Assert.assertFalse(validator.isValidate(ParametresTest.UNCORRECT_TYPE_DEPOSIT));
    }
}
