/**
 * command to make invisible product
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

public class DeleteProductCommand implements Command {
    private static final Logger LOGGER = Logger.getRootLogger();
    ProductService service;

    public DeleteProductCommand(ProductService service){
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String id = request.getParameter(ParameterConstant.PARAM_ID);
        String name = request.getParameter(ParameterConstant.PARAM_NAME_PRODUCT);
        String page;
        try{
            boolean status = service.deleteProduct(name, id);

            if(status){
                request.getSession(true).setAttribute(ParameterConstant.MESSAGE_DELETE_PRODUCT,
                        ParameterConstant.MESSAGE_CONGRAT);
            } else {
                request.getSession(true).setAttribute(ParameterConstant.MESSAGE_DELETE_PRODUCT,
                        ParameterConstant.MESSAGE_ERROR_REGIST);
            }
            request.setAttribute(ParameterConstant.ATTRIBUTE_ROUTE, RouteType.REDIRECT);
            page = (String) request.getSession().getAttribute(ParameterConstant.ATTRIBUTE_CURRENT_PAGE);
        } catch (TrackerServiceException e){
            LOGGER.error("Command can't delete product: " + name, e);
            page = PageConstant.PATH_PAGE_ERROR;
        }
        return page;
    }
}
