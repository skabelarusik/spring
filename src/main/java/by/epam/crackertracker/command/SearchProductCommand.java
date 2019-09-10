/**
 * command to search and table products that are written in input block(multitabling, 10 products in page)
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.command;

import by.epam.crackertracker.entity.Product;
import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.service.ProductService;
import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.util.ParameterConstant;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

import java.util.List;

public class SearchProductCommand implements Command {
    private static final Logger LOGGER = Logger.getRootLogger();
    ProductService service;

    public SearchProductCommand(ProductService service){
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String searchText = request.getParameter(ParameterConstant.PARAM_TEXT).trim();
        String page;
        try{
            List<Product> list = service.searchProducts(searchText);
            request.setAttribute(ParameterConstant.ATTRIBUTE_LIST_PRODUCTS, list);
            page = PageConstant.PATH_PAGE_RESULT;
        } catch (TrackerServiceException e){
            LOGGER.error("Command can't search product: " + searchText, e);
            page = PageConstant.PATH_PAGE_ERROR;
        }
        return page;
    }
}
