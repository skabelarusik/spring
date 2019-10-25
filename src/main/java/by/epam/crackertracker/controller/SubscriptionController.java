package by.epam.crackertracker.controller;

import by.epam.crackertracker.config.UserPrincipal;
import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.service.SubscriptionService;
import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.util.ParameterConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(PageConstant.URI_SUBSCRIPTION)
public class SubscriptionController {

    @Autowired
    private SubscriptionService service;

    @ExceptionHandler(TrackerServiceException.class)
    @GetMapping(PageConstant.URI_SELECT)
    public String select(Model model) throws TrackerServiceException {
        model.addAttribute(ParameterConstant.MESSAGE_SUBSCRIBERS, service.selectAllSubscriptions());
        model.addAttribute(ParameterConstant.MESSAGE_HAVE_SUBS, ParameterConstant.MESSAGE_CONGRAT);
        return PageConstant.PATH_PAGE_SUBSCRIPTION;
    }

    @ExceptionHandler(TrackerServiceException.class)
    @GetMapping(PageConstant.URI_SELECT_CURATOR)
    public String selectCurator(@AuthenticationPrincipal UserPrincipal user, Model model) throws TrackerServiceException {
        model.addAttribute(ParameterConstant.MESSAGE_SUBSCRIBERS,service.selectSubscribersCurator(user.getUsername()));
        return PageConstant.PATH_PAGE_SUBSCRIPTION;
    }

    @ExceptionHandler(TrackerServiceException.class)
    @RequestMapping(PageConstant.URI_SHOW_HISTORY)
    public String showHistory(@AuthenticationPrincipal UserPrincipal user, Model model) throws TrackerServiceException {
        model.addAttribute(ParameterConstant.MESSAGE_SUBSCRIBERS,service.selectHistorySubs(user.getUsername()));
        return PageConstant.PATH_PAGE_SUBSCRIPTION;
    }

    @ExceptionHandler(TrackerServiceException.class)
    @PostMapping(PageConstant.URI_BUY)
    public String buy(@SessionAttribute String startPage, @AuthenticationPrincipal UserPrincipal user, @RequestParam String id,
                      @RequestParam String cost, @RequestParam String duration, Model model) throws TrackerServiceException {
        model.addAttribute("messageProgram",             service.buySubscribe(id, cost, duration, user.getUsername(), user.getBalance(), user.getRole()));

        return PageConstant.PATH_RESULT_PROGRAM_NAME;
        //  return startPage;
    }
}
