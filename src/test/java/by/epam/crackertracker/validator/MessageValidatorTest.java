/**
 * it's a class for validate type message
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.validator;

import by.epam.crackertracker.resources.ParametresTest;
import org.junit.Assert;
import org.junit.Test;

public class MessageValidatorTest {
    MessageValidator validator = new MessageValidator();

    @Test
    public void testIsValidateNull(){
        Assert.assertFalse(validator.isValidate(ParametresTest.NULL));
    }

    @Test
    public void testIsValidateEmpty(){
        Assert.assertFalse(validator.isValidate(ParametresTest.EMPTY));
    }


    @Test
    public void testIsValidateCorrectType(){
        Assert.assertTrue(validator.isValidate(ParametresTest.CORRECT_MESSAGE_TYPE));
    }

    @Test
    public void testIsValidateUnCorrectType(){
        Assert.assertFalse(validator.isValidate(ParametresTest.UNCORRECT_MESSAGE_TYPE));
    }

}
