package by.epam.crackertracker.controller;

import by.epam.crackertracker.config.UserPrincipal;
import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.service.BucketService;
import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.util.ParameterConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(PageConstant.PATH_BUCKET)
public class BucketController {

    @Autowired
    private BucketService service;

    @PostMapping(ParameterConstant.ADD_PROGRAM)
    public String add(@SessionAttribute String startPage, Model model, @RequestParam String portions,
                      @AuthenticationPrincipal UserPrincipal user, @RequestParam String nameProduct){
        try {
            model.addAttribute(ParameterConstant.ATTRIBUTE_LIST_PRODUCTS_BUCKET ,
                    service.addProduct(user.getUsername(), nameProduct, portions));
        } catch (TrackerServiceException e) {
            model.addAttribute(ParameterConstant.MESSAGE_PRODUCT, ParameterConstant.MESSAGE_ERROR_REGIST);
        }
        return startPage;
    }

    @ExceptionHandler(TrackerServiceException.class)
    @PostMapping(ParameterConstant.CLEAR_BUCKET)
    public String clear(@SessionAttribute String startPage, Model model, @AuthenticationPrincipal UserPrincipal user) throws TrackerServiceException {
        service.removeAll(user.getUsername());
        model.addAttribute(ParameterConstant.MESSAGE_PRODUCT, ParameterConstant.MESSAGE_CONGRAT);
        model.addAttribute(ParameterConstant.ATTRIBUTE_LIST_PRODUCTS_BUCKET ,
                service.selectAll(user.getUsername()));
        return startPage;
    }

    @ExceptionHandler(TrackerServiceException.class)
    @PostMapping(PageConstant.URI_DELETE)
    public String delete_by_id(@SessionAttribute String startPage, @RequestParam String id,
                               @SessionAttribute String login, Model model) throws TrackerServiceException {
        model.addAttribute(ParameterConstant.ATTRIBUTE_LIST_PRODUCTS_BUCKET, service.removeId(id, login));
        model.addAttribute(ParameterConstant.ATTRIBUTE_RESULT, ParameterConstant.MESSAGE_CONGRAT);
        return startPage;
    }

    @ExceptionHandler(TrackerServiceException.class)
    @PostMapping(PageConstant.CALCULATE)
    public String calculate(@SessionAttribute String startPage, @SessionAttribute String login, Model model) throws TrackerServiceException {
        model.addAttribute(ParameterConstant.ATTRIBUTE_RESULT, service.calculate(login));
        return startPage;
    }
}
