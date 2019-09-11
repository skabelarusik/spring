/**
 * command to calculate rate of weight
 * @author Andrey Krupin,  june-august 2019
 */


package by.epam.crackertracker.command;

import by.epam.crackertracker.util.ParameterConstant;
import by.epam.crackertracker.validator.IdValidator;

import javax.servlet.http.HttpServletRequest;

public class CalculatorCommand implements Command {
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

    @Override
    public String execute(HttpServletRequest request) {
        IdValidator idValidator = new IdValidator();
        if(idValidator.isValidate(request.getParameter(ParameterConstant.PARAM_WEIGHT)) &&
            idValidator.isValidate(request.getParameter(ParameterConstant.PARAM_HEIGHT))){
        int weight = Integer.parseInt(request.getParameter(ParameterConstant.PARAM_WEIGHT));
        int height = Integer.parseInt(request.getParameter(ParameterConstant.PARAM_HEIGHT));
        int kef = weight * CALCULATE_KEF / (height * height);
        String result = checkMessage(kef);
        request.setAttribute(ParameterConstant.ATTRIBUTE_RESULT, result);
        } else{
            request.setAttribute(ParameterConstant.ATTRIBUTE_RESULT, ParameterConstant.MESSAGE_ERROR_REGIST);
        }
        return (String) request.getSession(true).getAttribute(ParameterConstant.ATTRIBUTE_CURRENT_PAGE);
    }

    private String checkMessage(double result){
        String message;
        if(result <= MAS_KEF_1){
            message = result + MESS_KEF_1 ;
        } else if(result < MAS_KEF_2){
                message = result + MESS_KEF_2;
        } else if(result < MAS_KEF_3){
            message = result + MESS_KEF_3;
        } else if(result < MAS_KEF_4){
            message = result + MESS_KEF_4;
        } else if(result < MAS_KEF_5){
            message = result + MESS_KEF_5;
        } else {
            message = result + MESS_ANOTHER;
        }
        return message;
    }
}
