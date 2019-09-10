package by.epam.crackertracker.validator;

import by.epam.crackertracker.entity.MealDay;

public class DayValidator implements ValidatorI {
    @Override
    public boolean isValidate(String str) {
        boolean status = false;
        if(str != null && !str.isEmpty()){
            if(str.equalsIgnoreCase(MealDay.MONDAY.toString()) || str.equalsIgnoreCase(MealDay.TUESDAY.toString()) ||
                    str.equalsIgnoreCase(MealDay.WEDNESDAY.toString()) || str.equalsIgnoreCase(MealDay.THURSDAY.toString()) ||
                    str.equalsIgnoreCase(MealDay.FRIDAY.toString()) || str.equalsIgnoreCase(MealDay.SATURDAY.toString()) ||
                    str.equalsIgnoreCase(MealDay.SUNDAY.toString()) || str.equalsIgnoreCase(MealDay.WEEKDAYS.toString()) ||
                    str.equalsIgnoreCase(MealDay.WEEKEND.toString()) || str.equalsIgnoreCase(MealDay.ALL.toString())){
                status = true;
            }
        }
        return status;
    }
}
