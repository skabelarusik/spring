package by.epam.crackertracker.controller;

import by.epam.crackertracker.service.AdviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/advice")
public class AdviceController {

   @Autowired
    private AdviceService adviceService;

   @GetMapping("/select")
   public String selectAdvice(){
        return "showAdvice";
    }

   @PostMapping("/add")
   public String addAdvice(){
       return null;
   }

   @PostMapping("/delete")
   public String delete(){
       return null;
   }
}
