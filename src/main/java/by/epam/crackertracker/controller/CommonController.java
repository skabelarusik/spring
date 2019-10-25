package by.epam.crackertracker.controller;

import by.epam.crackertracker.config.UserPrincipal;
import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.service.CalculatorService;
import by.epam.crackertracker.util.LanguageSelector;
import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.util.ParameterConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Controller
@RequestMapping(PageConstant.PATH_PAGE_INDEX)
public class CommonController {

    @Autowired
    private LocaleResolver localeResolver;

    @Autowired
    private LanguageSelector selector;

    @Autowired
    private CalculatorService service;

    @ExceptionHandler(Exception.class)
    @RequestMapping(PageConstant.PATH_PAGE_INDEX)
    public String index(){
        return PageConstant.PATH_PAGE_MAIN_INDEX;
    }

    @ExceptionHandler(Exception.class)
    @GetMapping(PageConstant.URI_ABOUT)
    public String about(){
        return PageConstant.PAGE_ABOUT;
    }


    @ExceptionHandler(Exception.class)
    @GetMapping(PageConstant.URI_BACK)
    public String back(@AuthenticationPrincipal UserPrincipal user){
        return user.getRole().toLowerCase();
    }

    @ExceptionHandler(Exception.class)
    @RequestMapping(PageConstant.URI_LANG_BY)
    public String langBy(HttpServletRequest request, HttpServletResponse response){
        localeResolver.setLocale(request, response, new Locale(ParameterConstant.BY, ParameterConstant.SUBMIT_BY));
        return request.getParameter(ParameterConstant.ATTRIBUTE_CURRENT_PAGE);
    }

    @ExceptionHandler(Exception.class)
    @RequestMapping(PageConstant.URI_LANG_RU)
    public String langRu(HttpServletRequest request, HttpServletResponse response){
        localeResolver.setLocale(request, response, new Locale(ParameterConstant.RU, ParameterConstant.SUBMIT_RU));
        return request.getParameter(ParameterConstant.ATTRIBUTE_CURRENT_PAGE);
    }

    @ExceptionHandler(Exception.class)
    @RequestMapping(PageConstant.URI_LANG_EN)
    public String langEn(HttpServletRequest request, HttpServletResponse response){
        localeResolver.setLocale(request, response, new Locale(ParameterConstant.EN, ParameterConstant.SUBMIT_EN));
        return request.getParameter(ParameterConstant.ATTRIBUTE_CURRENT_PAGE);
    }

    @ExceptionHandler(Exception.class)
    @RequestMapping(PageConstant.URI_LANG)
    public String lang(@RequestParam String langv,  HttpServletRequest request, HttpServletResponse response){
        localeResolver.setLocale(request, response, new Locale(selector.select(langv), langv));
        return request.getParameter(ParameterConstant.ATTRIBUTE_CURRENT_PAGE);
    }

    @ExceptionHandler(Exception.class)
    @RequestMapping(PageConstant.LOGOUT)
    public String logout(){
        return PageConstant.PATH_PAGE_MAIN_INDEX;
    }

    @ExceptionHandler(Exception.class)
    @RequestMapping(PageConstant.URI_LOGIN)
    public String login2(){
        return ParameterConstant.LOGIN;
    }

    @ExceptionHandler(Exception.class)
    @RequestMapping(PageConstant.LOGOUT_SUCCESS)
    public String logoutt(){
        return ParameterConstant.LOGIN;
    }


    @PostMapping(PageConstant.CALCULATE)
    public String calculate(@SessionAttribute String startPage, @RequestParam String weight,
                            @RequestParam String height, Model model){
        try {
            model.addAttribute(ParameterConstant.ATTRIBUTE_RESULT, service.calculate(weight, height));
        } catch (TrackerServiceException e) {
            model.addAttribute(ParameterConstant.ATTRIBUTE_RESULT, ParameterConstant.MESSAGE_ERROR_REGIST);
        }
        return startPage;
    }

    @ExceptionHandler(TrackerServiceException.class)
    @PostMapping(PageConstant.CALCULATE_CALORIES)
    public String calculate_calories(@SessionAttribute String startPage, @RequestParam String age,
                                     @RequestParam String height, @RequestParam String weight, @RequestParam String gender,
                                     @RequestParam String active, Model model){
        String result = service.calculateCalories(height, weight, age, gender, active);
        model.addAttribute(ParameterConstant.ATTRIBUTE_RESULT_CALORIES, result);
        return startPage;
    }

    @ExceptionHandler(Exception.class)
    @GetMapping("/news")
    public String news(){
        return PageConstant.PAGE_NEWS;
    }


}
