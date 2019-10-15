package by.epam.crackertracker.controller;

import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.util.ParameterConstant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@RequestMapping(PageConstant.PATH_BUCKET)
public class BucketController {

    @PostMapping(ParameterConstant.ADD_PROGRAM)
    public String add(@SessionAttribute String startPage, Model model){
        model.addAttribute("messageProduct", ParameterConstant.MESSAGE_CONGRAT);
        return startPage;
    }
}
