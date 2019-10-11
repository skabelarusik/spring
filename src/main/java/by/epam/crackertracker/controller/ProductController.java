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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.awt.*;
import java.util.Map;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping("/search")
    public String search(){
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
           model.addAttribute(ParameterConstant.MESSAGE_WRONG_PRODUCTS, allRequestParams.get(ParameterConstant.ATTRIBUTE_CURRENT_PAGE));
           return allRequestParams.get(ParameterConstant.ATTRIBUTE_CURRENT_PAGE);
        }
        return PageConstant.PATH_PAGE_PRODUCT;
    }

    @PostMapping("/update")
    public String updateProduct(@RequestParam Map<String, String> allRequestParam, Model model){
        try {
            service.updateProduct(allRequestParam.get(ParameterConstant.PRODUCT_ID), allRequestParam.get(ParameterConstant.PARAM_NAME_PRODUCT),
                    allRequestParam.get(ParameterConstant.PARAM_CALORIES_PRODUCT), allRequestParam.get(ParameterConstant.PARAM_FATS_PRODUCT),
                    allRequestParam.get(ParameterConstant.PARAM_CARBS_PRODUCT), allRequestParam.get(ParameterConstant.PARAM_PROTEINS_PRODUCT));
            model.addAttribute(ParameterConstant.MESSAGE_UPDATE_PRODUCT, ParameterConstant.MESSAGE_CONGRAT);
        } catch (TrackerServiceException e) {
            model.addAttribute(ParameterConstant.MESSAGE_UPDATE_PRODUCT, ParameterConstant.MESSAGE_ERROR_REGIST);
        }
        return  allRequestParam.get(ParameterConstant.ATTRIBUTE_CURRENT_PAGE);
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
    public String add(){
        return null;
    }


}
