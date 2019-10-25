package by.epam.crackertracker.controller;


import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.service.ProductService;
import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.util.ParameterConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping(PageConstant.URI_PRODUCT)
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping(PageConstant.URI_SEARCH_PRODUCT)
    public String search(@RequestParam String text, @SessionAttribute String startPage, Model model){
        try {
            model.addAttribute(ParameterConstant.ATTRIBUTE_LIST_PRODUCTS, service.searchProducts(text));
        } catch (TrackerServiceException e) {
            model.addAttribute(ParameterConstant.WRONG_SEARCH, ParameterConstant.WRONG_DATA);
            return startPage;
        }
        return PageConstant.PATH_PAGE_PRODUCT;
    }

    @ExceptionHandler(TrackerServiceException.class)
    @GetMapping(PageConstant.URI_SELECT)
    public String selectAll( Model model) throws TrackerServiceException {
        int pageNum = 1;
        model.addAttribute(ParameterConstant.ATTRIBUTE_CURRENT_PAGE, pageNum);
        model.addAttribute(ParameterConstant.ATTRIBUTE_LIST_PRODUCTS, service.selectAllProduct(null,pageNum));
        return PageConstant.PATH_PAGE_PRODUCT;
    }

    @ExceptionHandler(TrackerServiceException.class)
    @PostMapping(PageConstant.URI_SELECT)
    public String selectAll(@RequestParam Map<String,String> allRequestParams, Model model) throws TrackerServiceException {
        int page = Integer.parseInt(allRequestParams.get(ParameterConstant.ATTRIBUTE_CURRENT_PAGE));
        model.addAttribute(ParameterConstant.ATTRIBUTE_CURR_PAGE, page);
        model.addAttribute(ParameterConstant.ATTRIBUTE_LIST_PRODUCTS, service.selectAllProduct(null,page));
        return PageConstant.PATH_PAGE_PRODUCT;
    }

    @PostMapping(PageConstant.URI_SELECT_BY_CALORIES)
    public String selectByCalories(@RequestParam Map<String,String> allRequestParams, Model model) throws TrackerServiceException {
        String page = allRequestParams.get(ParameterConstant.ATTRIBUTE_NEXT_PAGE);
        String min = allRequestParams.get(ParameterConstant.PARAM_MIN_CALORIES);
        String max = allRequestParams.get(ParameterConstant.PARAM_MAX_CALORIES);
        String type = allRequestParams.get(ParameterConstant.PARAM_TYPE);
        try {
            model.addAttribute(ParameterConstant.ATTRIBUTE_LIST_PRODUCTS, service.selectProduct(min, max, page,type, model));
        } catch (TrackerServiceException e) {
            model.addAttribute(ParameterConstant.MESSAGE_WRONG_PRODUCTS, allRequestParams.get(ParameterConstant.MESSAGE_ERROR_REGIST));
            return allRequestParams.get(ParameterConstant.ATTRIBUTE_CURRENT_PAGE);
        }
        return PageConstant.PATH_PAGE_PRODUCT;
    }

    @PostMapping(PageConstant.URI_UPDATE_USER)
    public String updateProduct(@RequestParam String idproduct, @RequestParam String nameProduct, @RequestParam String caloriesProduct,
                                @RequestParam String proteinsProduct, @RequestParam String fatsProduct,
                                @RequestParam String carbsProduct, @RequestParam String currentPage, Model model){
        try {
            service.updateProduct(idproduct, nameProduct,caloriesProduct, fatsProduct, carbsProduct, proteinsProduct);
            model.addAttribute(ParameterConstant.MESSAGE_UPDATE_PRODUCT, ParameterConstant.MESSAGE_CONGRAT);
        } catch (TrackerServiceException e) {
            model.addAttribute(ParameterConstant.MESSAGE_UPDATE_PRODUCT, ParameterConstant.MESSAGE_ERROR_REGIST);
        }
        return  currentPage;
    }

    @PostMapping(PageConstant.URI_DELETE)
    public String delete(@RequestParam String id, @RequestParam String currentPage,  Model model){
        try {
            service.deleteProduct(id);
            model.addAttribute(ParameterConstant.MESSAGE_DELETE_PRODUCT, ParameterConstant.MESSAGE_CONGRAT);
        } catch (TrackerServiceException e) {
            model.addAttribute(ParameterConstant.MESSAGE_DELETE_PRODUCT, ParameterConstant.MESSAGE_ERROR_REGIST);
        }
        return currentPage;
    }

    @PostMapping(PageConstant.URI_ADD)
    public String add(@RequestParam String currentPage, Model model, @RequestParam String nameProduct,
                      @RequestParam String caloriesProduct, @RequestParam String proteinsProduct,
                      @RequestParam String fatsProduct, @RequestParam String carbsProduct){
        try {
            service.addProduct(nameProduct, caloriesProduct, fatsProduct, carbsProduct, proteinsProduct);
            model.addAttribute(ParameterConstant.MESSAGE_INSERT_PRODUCT, ParameterConstant.MESSAGE_CONGRAT);
        } catch (TrackerServiceException e) {
            model.addAttribute(ParameterConstant.MESSAGE_INSERT_PRODUCT, ParameterConstant.MESSAGE_ERROR_REGIST);
        }
        return currentPage;
    }


}
