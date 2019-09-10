/**
 * it's a class for validate role user
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.validator;

import by.epam.crackertracker.resources.ParametresTest;
import org.junit.Assert;
import org.junit.Test;

public class RoleValidatorTest  {
    RoleValidator validator = new RoleValidator();

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
        Assert.assertTrue(validator.isValidate(ParametresTest.CORRECT_ROLE));
    }


    @Test
    public void isValidateUncorrect() {
        Assert.assertFalse(validator.isValidate(ParametresTest.UNCORRECT_ROLE));
    }


}
