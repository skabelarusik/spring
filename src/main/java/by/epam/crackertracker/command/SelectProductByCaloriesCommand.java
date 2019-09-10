/**
 * command to show table all products(by min-max calories) (multitabling, 10 products in page)
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.command;

import by.epam.crackertracker.entity.Product;
import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.util.ParameterConstant;
import by.epam.crackertracker.service.ProductService;
import by.epam.crackertracker.validator.IdValidator;
import by.epam.crackertracker.validator.MinMaxCaloriesValidator;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class SelectProductByCaloriesCommand implements Command {
    private static final Logger LOGGER = Logger.getRootLogger();
    private ProductService service;
    static final int MAX_TABLE_PRODUCTS = 11;

    public SelectProductByCaloriesCommand(ProductService service){
        this.service = service;
    }

    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String minCalories = request.getParameter(ParameterConstant.PARAM_MIN_CALORIES);
        String maxCalories = request.getParameter(ParameterConstant.PARAM_MAX_CALORIES);
        request.setAttribute(ParameterConstant.PARAM_MIN_CALORIES, minCalories);
        request.setAttribute(ParameterConstant.PARAM_MAX_CALORIES, maxCalories);
        String currentTablePage = request.getParameter(ParameterConstant.ATTRIBUTE_NEXT_PAGE);
        MinMaxCaloriesValidator caloriesValidator = new MinMaxCaloriesValidator();
        List<Product> productList;
        int intPage = 1;
        int min;
        int max;
        try {
            if (caloriesValidator.isValidate(minCalories) && caloriesValidator.isValidate(maxCalories)) {
                min = Integer.parseInt(minCalories);
                max = Integer.parseInt(maxCalories);
                String type = request.getParameter(ParameterConstant.PARAM_TYPE);
                if (currentTablePage == null) {
                    intPage = 1;
                    request.setAttribute(ParameterConstant.ATTRIBUTE_RES_PAGE, intPage);
                } else {
                    intPage = Integer.parseInt(currentTablePage);
                    request.setAttribute(ParameterConstant.ATTRIBUTE_RES_PAGE, intPage);
                    if (intPage > 1) {
                        request.setAttribute(ParameterConstant.ATTRIBUTE_PREV_PAGE, intPage - 1);
                    }
                }
                productList = service.selectProduct(min, max, intPage, type);
            } else {
                request.setAttribute(ParameterConstant.ATTRIBUTE_PAGE_MESSAGE, ParameterConstant.ATTRIBUTE_WRONG_ACTION);
                page = (String) request.getSession(true).getAttribute(ParameterConstant.ATTRIBUTE_CURRENT_PAGE);
                return page;
            }
            List<Product> newProductList = new ArrayList<>();
            if (productList.size() > 0 && productList.size() < MAX_TABLE_PRODUCTS) {
                newProductList = new ArrayList<>(productList.size());
                for (int i = 0; i < productList.size(); i++) {
                    newProductList.add(productList.get(i));
                }
            } else if (productList.size() == MAX_TABLE_PRODUCTS) {
                newProductList = new ArrayList<>(productList.size() - 1);
                for (int i = 0; i < productList.size() - 1; i++) {
                    newProductList.add(productList.get(i));
                }
                request.setAttribute(ParameterConstant.ATTRIBUTE_NEXT_PAGE, intPage + 1);
            }
            if (newProductList != null) {
                request.setAttribute(ParameterConstant.ATTRIBUTE_LIST_PRODUCTS_BY_CALORIES, productList);
                page = PageConstant.PATH_PAGE_RESULT;
            } else {
                request.setAttribute(ParameterConstant.MESSAGE_WRONG_PRODUCTS, ParameterConstant.WRONG_DATA);
                page = (String) request.getSession(true).getAttribute(ParameterConstant.ATTRIBUTE_CURRENT_PAGE);
            }
        } catch (TrackerServiceException e){
            LOGGER.error("Command can't select product by range calories", e);
            page = PageConstant.PATH_PAGE_ERROR;
        }
        return page;
    }
}
