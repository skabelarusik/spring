package by.epam.crackertracker.command;

import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.service.BucketService;
import by.epam.crackertracker.util.ParameterConstant;
import by.epam.crackertracker.util.RouteType;

import javax.servlet.http.HttpServletRequest;

public class DeleteProductBucketCommand implements Command {
    BucketService service;
    public DeleteProductBucketCommand(BucketService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String login = (String) request.getSession(true).getAttribute(ParameterConstant.PARAM_LOGIN);
        boolean status;
        try {
            status = service.removeAll(login);
            if(status){
                request.getSession().removeAttribute(ParameterConstant.ATTRIBUTE_LIST_PRODUCTS_BUCKET);
                request.getSession().setAttribute(ParameterConstant.MESSAGE_PRODUCT, ParameterConstant.MESSAGE_CONGRAT);
            } else {
                request.getSession().setAttribute(ParameterConstant.MESSAGE_PRODUCT, ParameterConstant.MESSAGE_ERROR_REGIST);
            }
        }catch (TrackerServiceException e) {
            request.getSession().setAttribute(ParameterConstant.MESSAGE_PRODUCT, ParameterConstant.MESSAGE_ERROR_REGIST);
        }
        String page = (String) request.getSession().getAttribute(ParameterConstant.ATTRIBUTE_CURRENT_PAGE);
        request.setAttribute(ParameterConstant.ATTRIBUTE_ROUTE, RouteType.REDIRECT);
        return page;
    }
}
