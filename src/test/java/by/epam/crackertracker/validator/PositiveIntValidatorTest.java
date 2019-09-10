package by.epam.crackertracker.validator;

import by.epam.crackertracker.resources.ParametresTest;
import org.junit.Assert;
import org.junit.Test;

public class PositiveIntValidatorTest {
    PositiveIntValidator validator = new PositiveIntValidator();


    @Test
    public void isValidateCorrect() {
        Assert.assertTrue(validator.isValidate(ParametresTest.CORRECT_INT));
    }

    @Test
    public void isValidateDouble() {
        Assert.assertFalse(validator.isValidate(ParametresTest.UNCORRECT_INT));
    }


}
