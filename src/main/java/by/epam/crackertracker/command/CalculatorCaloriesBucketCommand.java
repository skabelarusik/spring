package by.epam.crackertracker.command;

import by.epam.crackertracker.entity.Bucket;
import by.epam.crackertracker.util.ParameterConstant;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class CalculatorCaloriesBucketCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        String page = (String) request.getSession(true).getAttribute(ParameterConstant.ATTRIBUTE_CURRENT_PAGE);
        List<Bucket> list = (List<Bucket>) request.getSession().getAttribute(ParameterConstant.ATTRIBUTE_LIST_PRODUCTS_BUCKET);
        int sum = calculateCallories(list);
        request.setAttribute(ParameterConstant.ATTRIBUTE_RESULT, sum);
        return page;
    }

    private int calculateCallories(List<Bucket> list){
        int sum = 0;
        if(list != null && !list.isEmpty()){
            for(Bucket x : list){
                sum += (int)(x.getCalories() * x.getPortions());
            }
        }
        return sum;
    }
}
