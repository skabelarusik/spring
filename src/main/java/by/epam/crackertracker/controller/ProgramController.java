package by.epam.crackertracker.controller;

import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.service.ProgramService;
import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.util.ParameterConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@ControllerAdvice
@Controller
@RequestMapping("/program")
public class ProgramController {
    @Autowired
    private ProgramService service;

    @GetMapping(PageConstant.URI_SELECT)
    public String selectAll(@RequestParam String name, Model model) throws TrackerServiceException {
        model.addAttribute(ParameterConstant.ATTRIBUTE_LIST_PROGRAMS, service.selectAll(name));
        model.addAttribute("name", name);
        return PageConstant.PATH_RESULT_PROGRAM;
    }

    @GetMapping(PageConstant.URI_DELETE)
    public String delete(@RequestParam String id, @RequestParam String name,  Model model) throws TrackerServiceException {
        service.deleteById(id);
        model.addAttribute(ParameterConstant.ATTRIBUTE_LIST_PROGRAMS, service.selectAll(name));
        return PageConstant.PATH_RESULT_PROGRAM;
    }

    @PostMapping(PageConstant.URI_ADD)
    public String insert(@RequestParam String product, @RequestParam String portions, @RequestParam String day
        , @RequestParam String time, Model model, @RequestParam String name) throws TrackerServiceException {
        try {
            service.insert(day, time, product, name, portions);
            model.addAttribute(ParameterConstant.ATTRIBUTE_LIST_PROGRAMS, service.selectAll(name));
            model.addAttribute("messageAdd", ParameterConstant.MESSAGE_CONGRAT);
        } catch (Exception e){
            model.addAttribute("messageAdd", ParameterConstant.MESSAGE_ERROR_REGIST);
        }
        return PageConstant.PATH_RESULT_PROGRAM;
    }
}
