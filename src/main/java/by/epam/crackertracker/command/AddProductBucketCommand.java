package by.epam.crackertracker.command;

import by.epam.crackertracker.entity.Bucket;
import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.service.BucketService;
import by.epam.crackertracker.util.ParameterConstant;
import by.epam.crackertracker.util.RouteType;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class AddProductBucketCommand implements Command {
    private BucketService service;
    public AddProductBucketCommand(BucketService service) {
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String login = (String) request.getSession(true).getAttribute(ParameterConstant.PARAM_LOGIN);
        String product = request.getParameter(ParameterConstant.PARAM_NAME_PRODUCT);
        String portions = request.getParameter(ParameterConstant.PARAM_PORTIONS);
        String page = (String) request.getSession().getAttribute(ParameterConstant.ATTRIBUTE_CURRENT_PAGE);
        List<Bucket> bucketList;
        try {
            bucketList = service.addProduct(login, product, portions);
            request.getSession().setAttribute(ParameterConstant.ATTRIBUTE_LIST_PRODUCTS_BUCKET, bucketList);
            request.getSession().setAttribute(ParameterConstant.MESSAGE_PRODUCT, ParameterConstant.MESSAGE_CONGRAT);
        } catch (TrackerServiceException e) {
            request.getSession().setAttribute(ParameterConstant.MESSAGE_PRODUCT, ParameterConstant.MESSAGE_ERROR_REGIST);
        }
        request.setAttribute(ParameterConstant.ATTRIBUTE_ROUTE, RouteType.REDIRECT);
        return page;
    }
}
