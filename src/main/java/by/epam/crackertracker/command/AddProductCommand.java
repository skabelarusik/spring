/**
 * command to add product to DataBase
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.command;

import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.util.ParameterConstant;
import by.epam.crackertracker.service.ProductService;
import by.epam.crackertracker.util.RouteType;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

public class AddProductCommand implements Command {
    private static final Logger LOGGER = Logger.getRootLogger();
    private ProductService service;

    public AddProductCommand(ProductService service){
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String name = request.getParameter(ParameterConstant.PARAM_NAME_PRODUCT);
        String calories = request.getParameter(ParameterConstant.PARAM_CALORIES_PRODUCT);
        String fats = request.getParameter(ParameterConstant.PARAM_FATS_PRODUCT);
        String carbs = request.getParameter(ParameterConstant.PARAM_CARBS_PRODUCT);
        String proteins = request.getParameter(ParameterConstant.PARAM_PROTEINS_PRODUCT);
        String page;
        try{
            boolean status = service.addProduct(name, calories, fats, carbs, proteins);
            if(status){
                request.getSession(true).setAttribute(ParameterConstant.MESSAGE_INSERT_PRODUCT,
                    ParameterConstant.MESSAGE_CONGRAT);
            } else {
                request.getSession(true).setAttribute(ParameterConstant.MESSAGE_INSERT_PRODUCT,
                    ParameterConstant.MESSAGE_ERROR_REGIST);
            }
            request.setAttribute(ParameterConstant.ATTRIBUTE_ROUTE, RouteType.REDIRECT);
            page = (String) request.getSession().getAttribute(ParameterConstant.ATTRIBUTE_CURRENT_PAGE);
        } catch (TrackerServiceException e){
            LOGGER.error("Command can't add product: " + name, e);
            page = PageConstant.PATH_PAGE_ERROR;
        }
        return page;
    }
}
