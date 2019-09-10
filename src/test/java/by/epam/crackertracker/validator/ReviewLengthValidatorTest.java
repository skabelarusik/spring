package by.epam.crackertracker.validator;

import by.epam.crackertracker.resources.ParametresTest;
import org.junit.Assert;
import org.junit.Test;

public class ReviewLengthValidatorTest  {
    ReviewLengthValidator lengthValidator = new ReviewLengthValidator();

    @Test
    public void testIsValidateNull(){
        Assert.assertFalse(lengthValidator.isValidate(ParametresTest.NULL));
    }

    @Test
    public void testIsValidateEmpty(){
        Assert.assertFalse(lengthValidator.isValidate(ParametresTest.EMPTY));
    }

    @Test
    public void testIsValidateOneSize(){
        Assert.assertTrue(lengthValidator.isValidate(ParametresTest.ONE_SIZE));
    }

    @Test
    public void testIsValidateBiggerSize(){
        Assert.assertFalse(lengthValidator.isValidate(ParametresTest.BIG_SIZE_400));
    }

    @Test
    public void testIsValidateCorrectSize(){
        Assert.assertTrue(lengthValidator.isValidate(ParametresTest.TEN_SIZE));
    }


}
