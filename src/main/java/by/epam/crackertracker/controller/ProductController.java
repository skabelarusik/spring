package by.epam.crackertracker.controller;

import by.epam.crackertracker.entity.Product;
import by.epam.crackertracker.entity.Role;
import by.epam.crackertracker.exception.TrackerDBException;
import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.service.ProductService;
import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.util.PageSelector;
import by.epam.crackertracker.util.ParameterConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
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
            model.addAttribute("wrongSearch", "WRONG");
        }
        return PageConstant.PATH_PAGE_PRODUCT;
    }

    @GetMapping("/select")
    public String selectAll( Model model) throws TrackerServiceException {
        int pageNum = 1;
        model.addAttribute(ParameterConstant.ATTRIBUTE_CURRENT_PAGE, pageNum);
        model.addAttribute(ParameterConstant.ATTRIBUTE_LIST_PRODUCTS, service.selectAllProduct(null,pageNum));
        return PageConstant.PATH_PAGE_PRODUCT;
    }

    @PostMapping("/select")
    public String selectAll(@RequestParam Map<String,String> allRequestParams, Model model) throws TrackerServiceException {
        int page = Integer.parseInt(allRequestParams.get(ParameterConstant.ATTRIBUTE_CURRENT_PAGE));
        model.addAttribute("currPage", page);
        model.addAttribute(ParameterConstant.ATTRIBUTE_LIST_PRODUCTS, service.selectAllProduct(null,page));
        return PageConstant.PATH_PAGE_PRODUCT;
    }

    @PostMapping("/select_by_calories")
    public String selectByCalories(@RequestParam Map<String,String> allRequestParams, Model model) throws TrackerServiceException {
        String page = allRequestParams.get("nextPage");
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

    @PostMapping("/update")
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

    @PostMapping("/delete")
    public String delete(@RequestParam String id, @RequestParam String currentPage,  Model model){
        try {
            service.deleteProduct(id);
            model.addAttribute(ParameterConstant.MESSAGE_DELETE_PRODUCT, ParameterConstant.MESSAGE_CONGRAT);
        } catch (TrackerServiceException e) {
            model.addAttribute(ParameterConstant.MESSAGE_DELETE_PRODUCT, ParameterConstant.MESSAGE_ERROR_REGIST);
        }

        return currentPage;
    }

    @PostMapping("/add")
    public String add(@RequestParam String currentPage, Model model, @RequestParam String nameProduct,
                      @RequestParam String caloriesProduct, @RequestParam String proteinsProduct,
                      @RequestParam String fatsProduct, @RequestParam String carbsProduct){
        try {
            service.addProduct(nameProduct, caloriesProduct, fatsProduct, carbsProduct, proteinsProduct);
            model.addAttribute("messageInsertProduct", ParameterConstant.MESSAGE_CONGRAT);
        } catch (TrackerServiceException e) {
            model.addAttribute("messageInsertProduct", ParameterConstant.MESSAGE_ERROR_REGIST);

        }
        return currentPage;
    }


}
