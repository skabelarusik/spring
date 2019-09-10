/**
 * it's a class for validate users birthday
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.validator;

import by.epam.crackertracker.resources.ParametresTest;
import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

public class BirthdayValidatorTest {
    BirthdayValidator birthdayValidatorTest = new BirthdayValidator();

    @Test
    public void isValidateNull() {
        Assert.assertFalse(birthdayValidatorTest.isValidate(ParametresTest.NULL));
    }

    @Test
    public void isValidateEmpty() {
        Assert.assertFalse(birthdayValidatorTest.isValidate(ParametresTest.EMPTY));
    }

    @Test
    public void isValidateCorrect() {
        Assert.assertTrue(birthdayValidatorTest.isValidate(ParametresTest.CORRECT_DATE));
    }

    @Test
    public void isValidateUncorrect() {
        Assert.assertFalse(birthdayValidatorTest.isValidate(ParametresTest.UNCORRECT_DATE));
    }

    @Test
    public void isValidateWrongDate() {
        Assert.assertFalse(birthdayValidatorTest.isValidate(ParametresTest.WRONG_DATE));
    }
}
