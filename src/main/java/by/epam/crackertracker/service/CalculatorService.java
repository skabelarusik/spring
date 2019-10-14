package by.epam.crackertracker.service;

import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.util.ParameterConstant;
import by.epam.crackertracker.validator.AgeValidator;
import by.epam.crackertracker.validator.PositiveIntValidator;
import by.epam.crackertracker.validator.WeightHeightValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CalculatorService {
    public static final int CALCULATE_KEF = 10_000;
    public static final int MAS_KEF_1 = 18;
    public static final int MAS_KEF_2 = 25;
    public static final int MAS_KEF_3 = 30;
    public static final int MAS_KEF_4 = 35;
    public static final int MAS_KEF_5 = 40;
    public static final String MESS_KEF_1 = ": Недостаточная масса тела";
    public static final String MESS_KEF_2 = ": Нормальная масса тела";
    public static final String MESS_KEF_3 = ": Иыточная масса тела (предожирение)";
    public static final String MESS_KEF_4 = ": Ожирение 1-ой степени";
    public static final String MESS_KEF_5 = ": Ожирение 2-ой степени";
    public static final String MESS_ANOTHER = ": Ожирение 3-й степени";

    private static final double KEF_ACTIVE1 = 1.375;
    private static final double KEF_ACTIVE2 = 1.2;
    private static final double KEF_ACTIVE3 = 1.55;
    private static final double KEF_ACTIVE4 = 1.9;

    private static final int CORRECT_KEF_1 = 10;
    private static final double CORRECT_KEF_2 = 6.25;
    private static final int CORRECT_KEF_3 = 5;
    private static final int CORRECT_KEF_4_MALE = 161;
    private static final int CORRECT_KEF_4_FEMALE = 5;

    @Autowired
    private WeightHeightValidator validator;

    @Autowired
    private PositiveIntValidator intValidator;

    @Autowired
    private AgeValidator ageValidator;

    public String calculate(String weight, String height) throws TrackerServiceException {
        if(validator.isValidate(weight) && validator.isValidate(height)){
            int weightInt = Integer.parseInt(weight);
            int heightInt = Integer.parseInt(height);
            int kef = weightInt * CALCULATE_KEF / (heightInt * heightInt);
            String message;
            if(kef <= MAS_KEF_1){
                message = kef + MESS_KEF_1 ;
            } else if(kef < MAS_KEF_2){
                message = kef + MESS_KEF_2;
            } else if(kef < MAS_KEF_3){
                message = kef + MESS_KEF_3;
            } else if(kef < MAS_KEF_4){
                message = kef + MESS_KEF_4;
            } else if(kef < MAS_KEF_5){
                message = kef + MESS_KEF_5;
            } else {
                message = kef + MESS_ANOTHER;
            }
            return message;
        } else {
            throw new TrackerServiceException("Wrong height or weight");
        }
    }

    public String calculateCalories(String height, String weight, String age, String gender, String active) {
        String result = null;
        if (ageValidator.isValidate(age) && validator.isValidate(weight) && validator.isValidate(height)) {
            int ageInt = Integer.parseInt(age);
            int weightInt = Integer.parseInt(weight);
            int heightInt = Integer.parseInt(height);
            double kefActivity;
            if (active.equals(ParameterConstant.PARAM_ACT1)) {
                kefActivity = KEF_ACTIVE1;
            } else {
                if (active.equals(ParameterConstant.PARAM_ACT2)) {
                    kefActivity = KEF_ACTIVE2;
                } else {
                    if (active.equals(ParameterConstant.PARAM_ACT3)) {
                        kefActivity = KEF_ACTIVE3;
                    } else {
                        if (active.equals(ParameterConstant.PARAM_ACT4)) {
                            kefActivity = KEF_ACTIVE4;
                        } else {
                            result = ParameterConstant.WRONG_DATA;
                            return result;
                        }
                    }
                }
            }
            if (gender.equals(ParameterConstant.PARAM_FEMALE)) {
                int res = (int) ((CORRECT_KEF_1 * weightInt + CORRECT_KEF_2 * heightInt - CORRECT_KEF_3 * ageInt -
                        CORRECT_KEF_4_MALE)*kefActivity);
                result = String.valueOf(res);
            } else if (gender.equals(ParameterConstant.PARAM_MALE)){
                int res = (int)((CORRECT_KEF_1 * weightInt + CORRECT_KEF_2 * heightInt - CORRECT_KEF_3 * ageInt +
                        CORRECT_KEF_4_FEMALE)*kefActivity);
                result = String.valueOf(res);
            }
        } else {
            result = ParameterConstant.WRONG_DATA;
        }
        return result;
    }
}
