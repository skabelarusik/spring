package by.epam.crackertracker.validator;

import by.epam.crackertracker.resources.ParametresTest;
import org.junit.Assert;
import org.junit.Test;

public class TimeValidatorTest  {
    TimeValidator validator = new TimeValidator();

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
        Assert.assertTrue(validator.isValidate(ParametresTest.CORRECT_TIME));
    }


    @Test
    public void isValidateUncorrect() {
        Assert.assertFalse(validator.isValidate(ParametresTest.UNCORRECT_TIME));
    }

}
