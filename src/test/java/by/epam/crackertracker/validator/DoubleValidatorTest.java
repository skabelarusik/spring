/**
 * it's a class for validate double parameters
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.validator;

import by.epam.crackertracker.resources.ParametresTest;
import org.junit.Assert;
import org.junit.Test;

public class DoubleValidatorTest {
    DoubleValidator validator = new DoubleValidator();

    //empty is validate


    @Test
    public void isValidateEmpty() {
        Assert.assertTrue(validator.isValidate(ParametresTest.EMPTY));
    }

    @Test
    public void isValidateNull() {
        Assert.assertTrue(validator.isValidate(ParametresTest.NULL));
    }

    @Test
    public void isValidateCorrect() {
        Assert.assertTrue(validator.isValidate(ParametresTest.CORRECT_DOUBLE));
    }

    @Test
    public void isValidateBigSum() {
        Assert.assertFalse(validator.isValidate(ParametresTest.UNCORRECT_DOUBLE));
    }

    @Test
    public void isValidateNegatDouble() {
        Assert.assertFalse(validator.isValidate(ParametresTest.NEGATIVE_DOUBLE));
    }
}
