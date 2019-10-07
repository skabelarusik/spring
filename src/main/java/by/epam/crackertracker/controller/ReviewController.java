package by.epam.crackertracker.controller;

import by.epam.crackertracker.entity.Review;
import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.service.ReviewService;
import by.epam.crackertracker.util.ParameterConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService service;


    @GetMapping("/show")
    public String show(Model model){
        model.addAttribute("reviewList", service.selectAllReview(ParameterConstant.SHOW));
        return "resultReview";
    }

    @GetMapping("/show_del")
    public String showDeleted(Model model){
       model.addAttribute("reviewList",service.selectAllReview(ParameterConstant.SHOW_DELETED));
       return "resultReview";
    }

    @PostMapping("/delete")
    public String delete(){
        return null;
    }

    @PostMapping("/send")
    public String send(HttpServletRequest request){
        String login = (String) request.getSession(true).getAttribute(ParameterConstant.LOGIN);
        String text = request.getParameter(ParameterConstant.TEXT);
        try {
            service.sendReview(login, text);
            request.setAttribute(ParameterConstant.MESSAGE_SEND_REVIEW, ParameterConstant.MESSAGE_CONGRAT);
        } catch (TrackerServiceException e) {
            request.setAttribute(ParameterConstant.MESSAGE_SEND_REVIEW, ParameterConstant.WRONG_DATA);
        }
        return "review";
    }
}
