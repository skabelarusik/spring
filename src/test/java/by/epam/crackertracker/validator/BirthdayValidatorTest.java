/**
 * it's a class for validate users birthday
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.validator;

import by.epam.crackertracker.resources.ParametresTest;
import org.junit.Assert;
import org.junit.Test;

public class BirthdayValidatorTest {
    BirthdayValidator birthdayValidator = new BirthdayValidator();

    @Test
    public void isValidateNull() {
        Assert.assertFalse(birthdayValidator.isValidate(ParametresTest.NULL));
    }

    @Test
    public void isValidateEmpty() {
        Assert.assertFalse(birthdayValidator.isValidate(ParametresTest.EMPTY));
    }

    @Test
    public void isValidateCorrect() {
        Assert.assertTrue(birthdayValidator.isValidate(ParametresTest.CORRECT_DATE));
    }

    @Test
    public void isValidateUncorrect() {
        Assert.assertFalse(birthdayValidator.isValidate(ParametresTest.UNCORRECT_DATE));
    }

    @Test
    public void isValidateWrongDate() {
        Assert.assertFalse(birthdayValidator.isValidate(ParametresTest.WRONG_DATE));
    }
}
