/**
 * it's a class for validate users name or surname
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.validator;

import by.epam.crackertracker.resources.ParametresTest;
import org.junit.Assert;
import org.junit.Test;

public class NameSurnameValidatorTest {
    NameSurnameValidator validator = new NameSurnameValidator();

    @Test
    public void testIsValidateNull(){
        Assert.assertTrue(validator.isValidate(ParametresTest.NULL));
    }

    @Test
    public void testIsValidateEmpty(){
        Assert.assertTrue(validator.isValidate(ParametresTest.EMPTY));
    }

    @Test
    public void testIsValidateOneSize(){
        Assert.assertFalse(validator.isValidate(ParametresTest.ONE_SIZE));
    }

    @Test
    public void testIsValidateBiggerSize(){
        Assert.assertFalse(validator.isValidate(ParametresTest.BIG_SIZE_400));
    }

    @Test
    public void testIsValidateCorrectSize(){
        Assert.assertTrue(validator.isValidate(ParametresTest.TEN_SIZE));
    }
}
