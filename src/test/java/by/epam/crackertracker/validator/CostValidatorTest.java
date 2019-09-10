/**
 * it's a class for validate cost
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.validator;

import by.epam.crackertracker.resources.ParametresTest;
import org.junit.Assert;
import org.junit.Test;

public class CostValidatorTest {
    CostValidator validator = new CostValidator();
    public static final String COST_PARAM_REGEX = "([0-9]{1,5}[.]?)?[0-9]{0,1}";

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
        Assert.assertTrue(validator.isValidate(ParametresTest.CORRECT_COST));
    }

    @Test
    public void isValidateUncorrect() {
        Assert.assertFalse(validator.isValidate(ParametresTest.UNCORRECT_COST));
    }

    @Test
    public void isValidateWrongDate() {
        Assert.assertFalse(validator.isValidate(ParametresTest.NEGATIVE_COST));
    }
}



