package by.epam.crackertracker.validator;

import by.epam.crackertracker.resources.ParametresTest;
import org.junit.Assert;
import org.junit.Test;

public class TypeSortedValidatorTest  {
    TypeSortedValidator validator = new TypeSortedValidator();

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
        Assert.assertTrue(validator.isValidate(ParametresTest.CORRECT_TYPE_SORTED));
    }


    @Test
    public void isValidateUncorrect() {
        Assert.assertFalse(validator.isValidate(ParametresTest.UNCORRECT_TYPE_SORTED));
    }

}
