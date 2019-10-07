package by.epam.crackertracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/subscription")
public class SubscriptionController {

    @GetMapping("/select")
    public String select(){
        return null;
    }
}
