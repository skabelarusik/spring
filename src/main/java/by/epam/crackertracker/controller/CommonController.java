package by.epam.crackertracker.controller;

import by.epam.crackertracker.entity.Message;
import by.epam.crackertracker.entity.Role;
import by.epam.crackertracker.entity.User;
import by.epam.crackertracker.util.PageSelector;
import by.epam.crackertracker.util.ParameterConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.util.Locale;

@Controller
@RequestMapping("/")
public class CommonController {

    @Autowired
    private LocaleResolver localeResolver;

    @GetMapping("/")
    public String index(){
        return "index";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }

    @GetMapping("/lang/by")
    public String langBy(HttpServletRequest request, HttpServletResponse response){
        localeResolver.setLocale(request, response, new Locale("by", "BY"));
        return "index";
    }

    @GetMapping("/back")
    public String back(HttpServletRequest request){
        return PageSelector.selectHomePage((Role) request.getSession(true).getAttribute(ParameterConstant.ATTRIBUTE_ROLE));
    }

    @GetMapping("/lang/ru")
    public String langRu(HttpServletRequest request, HttpServletResponse response){
        localeResolver.setLocale(request, response, new Locale("ru", "RU"));
        return "index";
    }

    @GetMapping("/lang/en")
    public String langEn(HttpServletRequest request, HttpServletResponse response){
        localeResolver.setLocale(request, response, new Locale("en", "US"));
        return "index";
    }
}
