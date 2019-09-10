/**
 * it's a class for validate email
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.validator;

import by.epam.crackertracker.resources.ParametresTest;
import org.junit.Assert;
import org.junit.Test;

public class EmailValidatorTest {
    EmailValidator validator = new EmailValidator();

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
        Assert.assertTrue(validator.isValidate(ParametresTest.GOOD_EMAIL));
    }

    @Test
    public void isValidateUncorrect() {
        Assert.assertFalse(validator.isValidate(ParametresTest.BAD_EMAIL));
    }

    @Test
    public void isValidateUncorrectWithoutAt() {
        Assert.assertFalse(validator.isValidate(ParametresTest.BAD_EMAIL_2));
    }

    @Test
    public void isValidateBiggerSize() {
        Assert.assertFalse(validator.isValidate(ParametresTest.BIG_SIZE_400));
    }
}
