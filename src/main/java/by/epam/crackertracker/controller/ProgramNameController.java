package by.epam.crackertracker.controller;

import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.service.ProgramsNameService;
import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.util.ParameterConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.*;

@ControllerAdvice
@Controller
@RequestMapping(PageConstant.PATH_PROGRAM_NAME)
public class ProgramNameController {

    @Autowired
    private ProgramsNameService service;

    @GetMapping(PageConstant.URI_SELECT)
    public String select(Model model) throws TrackerServiceException {
        model.addAttribute(ParameterConstant.ATTRIBUTE_BUTTON_NAME, ParameterConstant.ATTRIBUTE_DELETE_TYPE);
        model.addAttribute(ParameterConstant.ATTRIBUTE_NAME_PROGRAM_NAME, service.selectAllProgramsName(1,1));
        return  PageConstant.PATH_RESULT_PROGRAM_NAME;
    }

    @GetMapping(PageConstant.URI_SELECT_DEL)
    public String selectDeleted(Model model) throws TrackerServiceException {
        model.addAttribute(ParameterConstant.ATTRIBUTE_BUTTON_NAME, ParameterConstant.ATTRIBUTE_RESTORE_TYPE);
        model.addAttribute(ParameterConstant.ATTRIBUTE_NAME_PROGRAM_NAME, service.selectAllProgramsName(1,0));
        return PageConstant.PATH_RESULT_PROGRAM_NAME;
    }

    @GetMapping(PageConstant.URI_SELECT_CURATOR)
    public String selectCurator(@SessionAttribute String login,  Model model) throws TrackerServiceException {
        model.addAttribute(ParameterConstant.ATTRIBUTE_BUTTON_NAME, ParameterConstant.ATTRIBUTE_DELETE_TYPE);
        model.addAttribute(ParameterConstant.ATTRIBUTE_NAME_PROGRAM_NAME, service.selectCuratorProgramsName(login,
                ParameterConstant.STARTING_PAGE, ParameterConstant.SHOW));
        return PageConstant.PATH_RESULT_PROGRAM_NAME;
    }

    @GetMapping(PageConstant.URI_SELECT_CURATOR_DEL)
    public String selectDeletedCurator(@SessionAttribute String login, Model model) throws TrackerServiceException {
        model.addAttribute(ParameterConstant.ATTRIBUTE_BUTTON_NAME, ParameterConstant.ATTRIBUTE_RESTORE_TYPE);
        model.addAttribute(ParameterConstant.ATTRIBUTE_NAME_PROGRAM_NAME, service.selectCuratorProgramsName(login,
                ParameterConstant.STARTING_PAGE, ParameterConstant.SHOW_DELETED));

        return PageConstant.PATH_RESULT_PROGRAM_NAME;
    }

    @PostMapping(ParameterConstant.ADD_PROGRAM)
    public String add(@SessionAttribute String startPage, @RequestParam String nameProgramName,
                      @RequestParam String costProgramName, @RequestParam String durProgramName,
                      @SessionAttribute String login, Model model){
        try{
            service.insert(login, nameProgramName, costProgramName, durProgramName);
            model.addAttribute(ParameterConstant.MESSAGE_PROGRAM_NAME, ParameterConstant.MESSAGE_CONGRAT);
        } catch (TrackerServiceException e){
            model.addAttribute(ParameterConstant.MESSAGE_PROGRAM_NAME, ParameterConstant.WRONG_DATA);
            return startPage;
        }
        return "redirect:" + startPage;
    }

    @PostMapping(PageConstant.URI_DELETE)
    public String delete(@RequestParam String id, @RequestParam String buttonName, Model model) throws TrackerServiceException {
        service.deleteById(id, buttonName);
        if (buttonName.equals(ParameterConstant.ATTRIBUTE_DELETE_TYPE)) {
            model.addAttribute(ParameterConstant.ATTRIBUTE_NAME_PROGRAM_NAME, service.selectAllProgramsName(1, ParameterConstant.SHOW));
            model.addAttribute(ParameterConstant.ATTRIBUTE_BUTTON_NAME, ParameterConstant.ATTRIBUTE_DELETE_TYPE);
        } else {
            model.addAttribute(ParameterConstant.ATTRIBUTE_NAME_PROGRAM_NAME, service.selectAllProgramsName(1, ParameterConstant.SHOW_DELETED));
            model.addAttribute(ParameterConstant.ATTRIBUTE_BUTTON_NAME, ParameterConstant.ATTRIBUTE_RESTORE_TYPE);
        }
        model.addAttribute(ParameterConstant.MESSAGE_SEND_PROGRAM, ParameterConstant.MESSAGE_CONGRAT);
        return PageConstant.PATH_RESULT_PROGRAM_NAME;

    }

    @GetMapping(PageConstant.URI_UPDATE_USER)
    public String editProgram(@RequestParam String cost, @RequestParam String name, @RequestParam String duration,
                              @RequestParam String id,  Model model){
        model.addAttribute(ParameterConstant.PARAM_COST, cost);
        model.addAttribute(ParameterConstant.PARAM_DURATION, duration);
        model.addAttribute(ParameterConstant.PARAM_NAME_PROGR, name);
        model.addAttribute(ParameterConstant.PARAM_ID, id);
        return PageConstant.PATH_PAGE_EDIT_PROGRAM;
    }

    @PostMapping(PageConstant.URI_UPDATE_USER)
    public String editProg(@RequestParam String cost, @RequestParam String name, @RequestParam String duration,
                           @RequestParam String id, @SessionAttribute String login,  Model model){
        try {
            service.update(name, id, duration, cost, login);
            model.addAttribute(ParameterConstant.WRONG_DATA, service.selectCuratorProgramsName(login,
                    ParameterConstant.STARTING_PAGE, ParameterConstant.SHOW));
        } catch (TrackerServiceException e) {
            model.addAttribute(ParameterConstant.ATTRIBUTE_NAME_PROGRAM_NAME, ParameterConstant.MESSAGE_ERROR_REGIST);
            return PageConstant.PATH_PAGE_EDIT_PROGRAM;
        }
        return PageConstant.PATH_RESULT_PROGRAM_NAME;
    }





}
