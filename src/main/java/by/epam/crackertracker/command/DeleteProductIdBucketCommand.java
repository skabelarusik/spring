package by.epam.crackertracker.command;

import by.epam.crackertracker.entity.Bucket;
import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.service.BucketService;
import by.epam.crackertracker.util.ParameterConstant;
import by.epam.crackertracker.util.RouteType;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class DeleteProductIdBucketCommand implements Command {
    BucketService service;
    public DeleteProductIdBucketCommand(BucketService service) {
        this.service = service;
    }

        @Override
        public String execute(HttpServletRequest request) {
            String id = request.getParameter(ParameterConstant.PARAM_ID);
            String login = (String) request.getSession().getAttribute(ParameterConstant.PARAM_LOGIN);
            List<Bucket> list;
            try {
                list = service.removeId(id, login);
                request.getSession().setAttribute(ParameterConstant.ATTRIBUTE_LIST_PRODUCTS_BUCKET, list);
                request.getSession().setAttribute(ParameterConstant.MESSAGE_PRODUCT, ParameterConstant.MESSAGE_CONGRAT);
            } catch (TrackerServiceException e) {
                request.getSession().setAttribute(ParameterConstant.MESSAGE_PRODUCT, ParameterConstant.MESSAGE_ERROR_REGIST);
            }
            String page = (String) request.getSession().getAttribute(ParameterConstant.ATTRIBUTE_CURRENT_PAGE);
            request.setAttribute(ParameterConstant.ATTRIBUTE_ROUTE, RouteType.REDIRECT);
            return page;
    }
}
