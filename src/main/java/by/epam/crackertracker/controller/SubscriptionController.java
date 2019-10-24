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

    @GetMapping(PageConstant.URI_SELECT)
    public String select(Model model){
        try {
            model.addAttribute(ParameterConstant.MESSAGE_SUBSCRIBERS, service.selectAllSubscriptions());
            model.addAttribute(ParameterConstant.MESSAGE_HAVE_SUBS, ParameterConstant.MESSAGE_CONGRAT);
        } catch (TrackerServiceException e) {
            model.addAttribute(ParameterConstant.MESSAGE_HAVE_SUBS, ParameterConstant.MESSAGE_ERROR_REGIST);
        }
        return PageConstant.PATH_PAGE_SUBSCRIPTION;
    }

    @GetMapping(PageConstant.URI_SELECT_CURATOR)
    public String selectCurator(@AuthenticationPrincipal UserPrincipal user, Model model){
        try {
            model.addAttribute(ParameterConstant.MESSAGE_SUBSCRIBERS,service.selectSubscribersCurator(user.getUsername()));
        } catch (TrackerServiceException e) {
            return PageConstant.PATH_PAGE_ERROR;
        }
        return PageConstant.PATH_PAGE_SUBSCRIPTION;
    }

    @RequestMapping(PageConstant.URI_SHOW_HISTORY)
    public String showHistory(@AuthenticationPrincipal UserPrincipal user, Model model){
        try {
            model.addAttribute(ParameterConstant.MESSAGE_SUBSCRIBERS,service.selectHistorySubs(user.getUsername()));
        } catch (TrackerServiceException e) {
            return PageConstant.PATH_PAGE_ERROR;
        }
        return PageConstant.PATH_PAGE_SUBSCRIPTION;
    }

    @PostMapping(PageConstant.URI_BUY)
    public String buy(@SessionAttribute String startPage, @AuthenticationPrincipal UserPrincipal user, @RequestParam String id,
                      @RequestParam String cost, @RequestParam String duration, Model model){
        try {
            model.addAttribute("messageProgram",             service.buySubscribe(id, cost, duration, user.getUsername(), user.getBalance(), user.getRole()));

            return PageConstant.PATH_RESULT_PROGRAM_NAME;
        } catch (TrackerServiceException e) {
            return PageConstant.PATH_PAGE_ERROR;
        }
      //  return startPage;
    }
}
