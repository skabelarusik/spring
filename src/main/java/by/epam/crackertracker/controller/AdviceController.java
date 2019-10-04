package by.epam.crackertracker.controller;

import by.epam.crackertracker.service.AdviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AdviceController {

   @Autowired
    private AdviceService adviceService;

   @GetMapping("/advice/show")
   public String addAdvice(){
        return "showAdvice";
    }
}
