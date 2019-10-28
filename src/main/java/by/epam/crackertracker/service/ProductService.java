/**
 * service for working with product dao
 * @author Andrey Krupin,  june-august 2019
 */

package by.epam.crackertracker.service;

import by.epam.crackertracker.dao.ProductDao;
import by.epam.crackertracker.dao.ProductDaoJdbc;
import by.epam.crackertracker.entity.Product;
import by.epam.crackertracker.exception.TrackerDBException;
import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.util.ParameterConstant;
import by.epam.crackertracker.validator.*;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {
    private static final Logger LOGGER = Logger.getRootLogger();

    @Autowired
    private ProductDao dao;

    @Autowired
    private MinMaxCaloriesValidator caloriesValidator;

    @Autowired
    private TypeSortedValidator validator;

    @Autowired
    private IdValidator idValidator;

    @Autowired
    private DoubleValidator doubleValidator;

    @Autowired
    private IdValidator intValidator;

    @Autowired
    private ProductNameValidator nameValidator;

    public static final int MAX_TABLE_PRODUCT = 11;



    public List<Product> selectProduct(String min, String max, String intPage, String type, Model model) throws TrackerServiceException {
        List<Product> productList;
        if(type == null){
            type = ParameterConstant.SORTED_NOTHING;
        }
        int pageInt;
        if(intPage == null){
            pageInt = 1;
        } else {
            pageInt = Integer.parseInt(intPage);
        }
        if(!validator.isValidate(type) || !caloriesValidator.isValidate(min) ||
                !caloriesValidator.isValidate(max)){
            LOGGER.warn("Wrong sort type or min-max calories products");
            throw new TrackerServiceException("Wrong sort type or min-max calories products");
        }
        int minCalories = Integer.parseInt(min);
        int maxCalories = Integer.parseInt(max);
        try {
            productList = dao.selectAllByRangeCallories(minCalories, maxCalories, pageInt, type);
            model.addAttribute(ParameterConstant.ATTRIBUTE_RES_PAGE, pageInt);
            if(productList.size() == 11){
                model.addAttribute(ParameterConstant.ATTRIBUTE_NEXT_PAGE, pageInt + 1);
            }
            if(pageInt > 1){
                model.addAttribute(ParameterConstant.ATTRIBUTE_PREV_PAGE, pageInt - 1);
            }
        } catch (TrackerDBException e) {
            LOGGER.error("Wrong service select product by range calories",e);
            throw new TrackerServiceException("Wrong service select product by range calories",e);
        }
        return productList;
    }

    public List<Product> selectAllProduct(Map<String, String> allRequestParams, Model model) throws TrackerServiceException {
        String currentPage = allRequestParams.get(ParameterConstant.ATTRIBUTE_NEXT_PAGE);
        String type = allRequestParams.get(ParameterConstant.PARAM_TYPE);
        List<Product> productList;
        if(type == null){
            type = ParameterConstant.SORTED_NOTHING;
        }
        if(!validator.isValidate(type)){
            LOGGER.warn("Wrong sort type products exception");
            throw new TrackerServiceException("Wrong sort type products exception");
        }
        if(currentPage == null) {
            int intPage = 1;
            try {
                productList = dao.selectAll(intPage, type);
            } catch (TrackerDBException e) {
                throw new TrackerServiceException("Wrong service select product", e);
            }
            model.addAttribute(ParameterConstant.ATTRIBUTE_RES_PAGE, intPage);
            if (productList.size() == MAX_TABLE_PRODUCT) {
                model.addAttribute(ParameterConstant.ATTRIBUTE_NEXT_PAGE, intPage + 1);
            }
        } else {
            int intPage = Integer.parseInt(currentPage);
            try {
                productList = dao.selectAll(intPage, type);
            } catch (Exception e) {
                LOGGER.error("Wrong service  select all products",e);
                throw new TrackerServiceException("Wrong service select all products",e);
            }
            model.addAttribute(ParameterConstant.ATTRIBUTE_RES_PAGE, intPage);
            if(productList.size() == MAX_TABLE_PRODUCT){
                model.addAttribute(ParameterConstant.ATTRIBUTE_NEXT_PAGE, intPage + 1);
            }
            if(intPage > 1){
                model.addAttribute(ParameterConstant.ATTRIBUTE_PREV_PAGE, intPage - 1);
            }
        }
        List<Product> newUserList = null;
        if(!productList.isEmpty() && productList.size() < MAX_TABLE_PRODUCT){
            newUserList = new ArrayList<>(productList.size());
            for(int i = 0 ; i < productList.size(); i++){
                newUserList.add(productList.get(i));
            }
        } else {
            newUserList = new ArrayList<>(productList.size()-1);
            for(int i = 0 ; i < productList.size() - 1; i++){
                newUserList.add(productList.get(i));
            }
        }
        return productList;
    }

    public void updateProduct(String id, String name, String calories, String fats, String carbs,
                              String proteins) throws TrackerServiceException {
        if(doubleValidator.isValidate(fats) && doubleValidator.isValidate(carbs) &&
                doubleValidator.isValidate(proteins) && caloriesValidator.isValidate(calories) &&
                nameValidator.isValidate(name) && intValidator.isValidate(id)){
            int caloriesPr = Integer.parseInt(calories);
            Product product = new Product(name, caloriesPr);
            double carbsPr;
            double fatsPr;
            double proteinsPr;
            if(carbs != null && !carbs.isEmpty()){
                carbsPr = Double.parseDouble(carbs);
            } else {
                carbsPr = -1;
            }
            if(proteins != null && !proteins.isEmpty()){
                proteinsPr = Double.parseDouble(proteins);
            } else {
                proteinsPr = -1;
            }
            if(fats != null && !fats.isEmpty()){
                fatsPr = Double.parseDouble(fats);
            } else {
                fatsPr = -1;
            }
            product.setCarbs(carbsPr);
            product.setFats(fatsPr);
            product.setIdProducts(Integer.parseInt(id));
            product.setProteins(proteinsPr);
            try {
                dao.updateProduct(product);
            } catch (TrackerDBException e) {
                LOGGER.error("Wrong service update product",e);
                throw new TrackerServiceException("Wrong service update product",e);
            }
        } else {
            throw new TrackerServiceException("Wrong service update product");
        }
    }

    public void deleteProduct(String id) throws TrackerServiceException {
        if(idValidator.isValidate(id)){
            int idProd = Integer.parseInt(id);
            try {
                dao.deleteById(idProd);
            } catch (TrackerDBException e) {
                LOGGER.error("Wrong service delete product",e);
                throw new TrackerServiceException("Wrong service delete product",e);
            }
        } else {
            throw new TrackerServiceException("Wrong service delete product");
        }
    }

    public void addProduct(String name, String calories, String fats, String carbs,
                           String proteins) throws TrackerServiceException {
        if (doubleValidator.isValidate(fats) && doubleValidator.isValidate(carbs) &&
                doubleValidator.isValidate(proteins) && caloriesValidator.isValidate(calories) &&
                nameValidator.isValidate(name)) {
            int caloriesPr = Integer.parseInt(calories);
            Product product = new Product(name, caloriesPr);
            double carbsPr;
            double fatsPr;
            double proteinsPr;
            if (carbs != null && !carbs.isEmpty()) {
                carbsPr = Double.parseDouble(carbs);
            } else {
                carbsPr = -1;
            }
            if (proteins != null && !proteins.isEmpty()) {
                proteinsPr = Double.parseDouble(proteins);
            } else {
                proteinsPr = -1;
            }
            if (fats != null && !fats.isEmpty()) {
                fatsPr = Double.parseDouble(fats);
            } else {
                fatsPr = -1;
            }
            product.setCarbs(carbsPr);
            product.setFats(fatsPr);
            product.setProteins(proteinsPr);
            try {
                dao.insert(product);
            } catch (TrackerDBException e) {
                LOGGER.error("Wrong service add product",e);
                throw new TrackerServiceException("Wrong service add product",e);
            }
        } else {
            throw new TrackerServiceException("Wrong service add product");
        }
    }

    public List<Product> searchProducts(String param) throws TrackerServiceException {
        List<Product> list = new ArrayList<>();
        if(param != null && !param.isEmpty()){
            try {
                list = dao.searchProducts(param);
            } catch (TrackerDBException e) {
                LOGGER.error("Wrong service search products",e);
                throw new TrackerServiceException("Wrong service search products",e);
            }
        }
        return list;
    }
}
