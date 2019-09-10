/**
 * it's a class for validate int parameters
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.validator;

import by.epam.crackertracker.resources.ParametresTest;
import org.junit.Assert;
import org.junit.Test;

public class IdValidatorTest  {
    IdValidator validator = new IdValidator();

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
        Assert.assertTrue(validator.isValidate(ParametresTest.CORRECT_COST));
    }

    @Test
    public void isValidateDouble() {
        Assert.assertFalse(validator.isValidate(ParametresTest.CORRECT_DOUBLE));
    }

    @Test
    public void isValidateWrongDate() {
        Assert.assertFalse(validator.isValidate(ParametresTest.NEGATIVE_COST));
    }
}
