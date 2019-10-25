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

@ControllerAdvice
@Controller
@RequestMapping(PageConstant.URI_SUBSCRIPTION)
public class SubscriptionController {

    @Autowired
    private SubscriptionService service;

    @ExceptionHandler(TrackerServiceException.class)
    public String except(){
        return PageConstant.PATH_PAGE_ERROR;
    }

    @GetMapping(PageConstant.URI_SELECT)
    public String select(Model model) throws TrackerServiceException {
        model.addAttribute(ParameterConstant.MESSAGE_SUBSCRIBERS, service.selectAllSubscriptions());
        model.addAttribute(ParameterConstant.MESSAGE_HAVE_SUBS, ParameterConstant.MESSAGE_CONGRAT);
        return PageConstant.PATH_PAGE_SUBSCRIPTION;
    }

    @GetMapping(PageConstant.URI_SELECT_CURATOR)
    public String selectCurator(@AuthenticationPrincipal UserPrincipal user, Model model) throws TrackerServiceException {
        model.addAttribute(ParameterConstant.MESSAGE_SUBSCRIBERS,service.selectSubscribersCurator(user.getUsername()));
        return PageConstant.PATH_PAGE_SUBSCRIPTION;
    }

    @RequestMapping(PageConstant.URI_SHOW_HISTORY)
    public String showHistory(@AuthenticationPrincipal UserPrincipal user, Model model) throws TrackerServiceException {
        model.addAttribute(ParameterConstant.MESSAGE_SUBSCRIBERS,service.selectHistorySubs(user.getUsername()));
        return PageConstant.PATH_PAGE_SUBSCRIPTION;
    }

    @PostMapping(PageConstant.URI_BUY)
    public String buy(@SessionAttribute String startPage, @AuthenticationPrincipal UserPrincipal user, @RequestParam String id,
                      @RequestParam String cost, @RequestParam String duration, Model model) {
        try {
           service.buySubscribe(id, cost, duration, user.getUsername(),
                    user.getBalance(), user.getRole());
            model.addAttribute("messageProgram", "GRATZIE. YOU MUST MAKE LOGOUT/LOGIN FOR TAKE NEW STATUS!!! ");
        } catch (TrackerServiceException e) {
            model.addAttribute("messageProgram", "WRONG DATA!!!");
        }
        return PageConstant.PATH_RESULT_PROGRAM_NAME;
    }
}
