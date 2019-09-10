/**
 * command to show table all products (multitabling, 10 products in page)
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.command;

import by.epam.crackertracker.entity.Product;
import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.util.ParameterConstant;
import by.epam.crackertracker.service.ProductService;
import by.epam.crackertracker.validator.IdValidator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class SelectAllProductsCommand implements Command {
    private static final Logger LOGGER = Logger.getRootLogger();
    public static final int MAX_TABLE_PRODUCTS = 11;
    public static final int START_PAGE = 1;
    ProductService receiver;

    public SelectAllProductsCommand(ProductService receiver){
        this.receiver = receiver;
    }


    @Override
    public String execute(HttpServletRequest request) {
        String currentTablePage = request.getParameter(ParameterConstant.ATTRIBUTE_NEXT_PAGE);
        String type = request.getParameter(ParameterConstant.PARAM_TYPE);
        IdValidator validator = new IdValidator();
        List<Product> productList;
        int pageNum = START_PAGE;
        if(currentTablePage == null){
            request.setAttribute(ParameterConstant.ATTRIBUTE_RES_PAGE, pageNum);
        } else if(validator.isValidate(currentTablePage)){
            {
                pageNum = Integer.parseInt(currentTablePage);
                request.setAttribute(ParameterConstant.ATTRIBUTE_RES_PAGE, pageNum);
                if (pageNum > 1) {
                    request.setAttribute(ParameterConstant.ATTRIBUTE_PREV_PAGE, pageNum - 1);
                }
            }
        }
        List<Product> newProductList;
        String page;
        try{
            productList = receiver.selectAllProduct(type, pageNum);
            if(productList.size() > 0 && productList.size() < MAX_TABLE_PRODUCTS){
                newProductList = new ArrayList<>(productList.size());
                for(int i = 0 ; i < productList.size(); i++){
                    newProductList.add(productList.get(i));
                }
            } else {
                newProductList = new ArrayList<>(productList.size()-1);
                for(int i = 0 ; i < productList.size() - 1; i++){
                    newProductList.add(productList.get(i));
                }
                request.setAttribute(ParameterConstant.ATTRIBUTE_NEXT_PAGE, pageNum + 1);
            }
            request.setAttribute(ParameterConstant.ATTRIBUTE_LIST_PRODUCTS, newProductList);
            page = PageConstant.PATH_PAGE_RESULT;
        } catch (TrackerServiceException e){
            LOGGER.error("Command can't select all products", e);
            page = PageConstant.PATH_PAGE_ERROR;
        }
        return page;
    }
}
