package by.epam.crackertracker.controller;

import by.epam.crackertracker.util.PageConstant;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(PageConstant.URI_SUBSCRIPTION)
public class SubscriptionController {

    @GetMapping(PageConstant.URI_SELECT)
    public String select(){
        return null;
    }

    @GetMapping(PageConstant.URI_SELECT_CURATOR)
    public String selectCurator(){
        return null;
    }
}
