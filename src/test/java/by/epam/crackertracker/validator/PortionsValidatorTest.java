package by.epam.crackertracker.validator;

import by.epam.crackertracker.resources.ParametresTest;
import org.junit.Assert;
import org.junit.Test;

public class PortionsValidatorTest {
    PortionsValidator validator = new PortionsValidator();

    @Test
    public void testIsValidateNull(){
        Assert.assertFalse(validator.isValidate(ParametresTest.NULL));
    }

    @Test
    public void testIsValidateEmpty(){
        Assert.assertFalse(validator.isValidate(ParametresTest.EMPTY));
    }

    @Test
    public void testIsValidateCorrectDouble(){
        Assert.assertTrue(validator.isValidate(ParametresTest.CORRECT_DOUBLE_2));
    }

    @Test
    public void testIsValidateUncorrectDouble(){
        Assert.assertFalse(validator.isValidate(ParametresTest.UNCORRECT_DOUBLE));
    }

    @Test
    public void testIsValidateNegativDouble(){
        Assert.assertFalse(validator.isValidate(ParametresTest.NEGATIVE_DOUBLE));
    }
}
