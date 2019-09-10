package by.epam.crackertracker.validator;

import by.epam.crackertracker.resources.ParametresTest;
import org.junit.Assert;
import org.junit.Test;

public class MinMaxCaloriesValidatorTest{
    MinMaxCaloriesValidator validator = new MinMaxCaloriesValidator();

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
    public void isValidateDouble() {
        Assert.assertFalse(validator.isValidate(ParametresTest.CORRECT_DOUBLE));
    }

    @Test
    public void isValidateBigInt(){
        Assert.assertFalse(validator.isValidate(ParametresTest.BIG_INT));
    }

    @Test
    public void isValidateNegativeDate() {
        Assert.assertFalse(validator.isValidate(ParametresTest.NEGATIVE_COST));
    }
}
