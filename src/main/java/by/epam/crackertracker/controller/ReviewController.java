package by.epam.crackertracker.controller;

import by.epam.crackertracker.entity.Review;
import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.service.ReviewService;
import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.util.ParameterConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping(PageConstant.PATH_REVIEW)
public class ReviewController {

    @Autowired
    private ReviewService service;

    @GetMapping(PageConstant.URI_SHOW)
    public String show(Model model){
        model.addAttribute(ParameterConstant.ATTRIBUTE_LIST_REVIEW, service.selectAllReview(ParameterConstant.SHOW));
        model.addAttribute(ParameterConstant.ATTRIBUTE_BUTTON_NAME, ParameterConstant.ATTRIBUTE_DELETE_TYPE);
        return PageConstant.PATH_RESULT_REVIEW;
    }

    @GetMapping(PageConstant.URI_SHOW_DEL)
    public String showDeleted(Model model){
       model.addAttribute(ParameterConstant.ATTRIBUTE_LIST_REVIEW,service.selectAllReview(ParameterConstant.SHOW_DELETED));
        model.addAttribute(ParameterConstant.ATTRIBUTE_BUTTON_NAME, ParameterConstant.ATTRIBUTE_RESTORE_TYPE);
        return PageConstant.PATH_RESULT_REVIEW;
    }

    @PostMapping(PageConstant.URI_DELETE)
    public String delete(@RequestParam String id, @RequestParam String buttonName, Model model){
        try {
            service.deleteById(id ,buttonName);
            if(buttonName.equals(ParameterConstant.ATTRIBUTE_DELETE_TYPE)){
                model.addAttribute(ParameterConstant.ATTRIBUTE_LIST_REVIEW, service.selectAllReview(ParameterConstant.SHOW));
                model.addAttribute(ParameterConstant.ATTRIBUTE_BUTTON_NAME, ParameterConstant.ATTRIBUTE_DELETE_TYPE);
            } else {
                model.addAttribute(ParameterConstant.ATTRIBUTE_LIST_REVIEW,service.selectAllReview(ParameterConstant.SHOW_DELETED));
                model.addAttribute(ParameterConstant.ATTRIBUTE_BUTTON_NAME, ParameterConstant.ATTRIBUTE_RESTORE_TYPE);
            }
            model.addAttribute(ParameterConstant.MESSAGE_SEND_REVIEW, ParameterConstant.MESSAGE_CONGRAT);
        } catch (TrackerServiceException e) {
            model.addAttribute(ParameterConstant.MESSAGE_SEND_REVIEW, ParameterConstant.MESSAGE_ERROR_REGIST);
        }
        return PageConstant.PATH_RESULT_REVIEW;
    }

    @PostMapping(PageConstant.URI_DELETE_BY_ID)
    public String delete_by_id(@RequestParam String id, @RequestParam String currentPage, Model model){
        try {
            service.deleteById(id, ParameterConstant.ATTRIBUTE_DELETE_TYPE);
            model.addAttribute(ParameterConstant.MESSAGE_SEND_REVIEW, ParameterConstant.MESSAGE_CONGRAT);
        } catch (TrackerServiceException e) {
            model.addAttribute(ParameterConstant.MESSAGE_SEND_REVIEW, ParameterConstant.MESSAGE_ERROR_REGIST);
            return currentPage;
        }
        return "redirect:" + currentPage;
    }

    @PostMapping(PageConstant.URI_SEND_REVIEW)
    public String send(@SessionAttribute String login, @RequestParam String text, Model model){
        try {
            service.sendReview(login, text);
            model.addAttribute(ParameterConstant.MESSAGE_SEND_REVIEW, ParameterConstant.MESSAGE_CONGRAT);
        } catch (TrackerServiceException e) {
            model.addAttribute(ParameterConstant.MESSAGE_SEND_REVIEW, ParameterConstant.WRONG_DATA);
            return PageConstant.PATH_PAGE_SEND_REVIEW;
        }
        return "redirect:" + PageConstant.PATH_PAGE_SEND_REVIEW;
    }

    @GetMapping(PageConstant.URI_SEND_REVIEW)
    public String sendReview(){
        return PageConstant.PATH_PAGE_SEND_REVIEW;
    }
}
