package by.epam.crackertracker.controller;


import by.epam.crackertracker.entity.Message;
import by.epam.crackertracker.entity.User;
import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.service.MessageService;
import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.util.ParameterConstant;
import jdk.jfr.StackTrace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
@ControllerAdvice
@Controller
@RequestMapping(PageConstant.PATH_MESSAGE)
public class MessageController {

    @Autowired
    MessageService service;

    @ExceptionHandler(TrackerServiceException.class)
    public String except(){
        return PageConstant.PATH_PAGE_ERROR;
    }


    @GetMapping(PageConstant.URI_INPUT_MESSAGE)
    public String checkInputMessage(@SessionAttribute User user, Model model) throws TrackerServiceException {
        model.addAttribute(ParameterConstant.MESSAGES, service.checkInputMessage(user.getLogin(), 1));
        return PageConstant.PATH_PAGE_MESSAGE;
    }

    @GetMapping(PageConstant.URI_OUTPUT_MESSAGE)
    public String checkOutputMessage(@SessionAttribute User user, Model model) throws TrackerServiceException {
        model.addAttribute(ParameterConstant.MESSAGES, service.checkOutputMessage(user.getLogin(), 1));
        return PageConstant.PATH_PAGE_MESSAGE;
    }

    @GetMapping(PageConstant.URI_SEND_REVIEW)
    public String writeMessage(@RequestParam String recipient, Model model){
        model.addAttribute(ParameterConstant.PARAM_RECIPIENT, recipient);
        return PageConstant.PATH_SEND_MESSAGE;
    }

    @PostMapping(PageConstant.URI_SEND_MESS)
    public String sendMessage(@RequestParam String recipient, Model model){
        model.addAttribute(ParameterConstant.PARAM_RECIPIENT, recipient);
        return PageConstant.PATH_SEND_MESSAGE;
    }

    @GetMapping(PageConstant.URI_SEND_MESS)
    public String sendMess(){
        return PageConstant.PATH_SEND_MESSAGE;
    }

    @PostMapping(PageConstant.URI_SEND_REVIEW)
    public String sendMessage(@SessionAttribute String login, @RequestParam String recipient, @RequestParam String topic,
                              @RequestParam String text, Model model){
        try {
            service.sendMessage(login, recipient, topic, text);
            model.addAttribute(ParameterConstant.WRONG_DATA_PASS, ParameterConstant.MESSAGE_CONGRAT);
        } catch (TrackerServiceException e) {
            model.addAttribute(ParameterConstant.WRONG_DATA_PASS, ParameterConstant.MESSAGE_ERROR_REGIST);
        }
        return PageConstant.PATH_SEND_MESSAGE;
    }

    @PostMapping(PageConstant.URI_DELETE)
    public String delete(@RequestParam String id, Model model, @SessionAttribute String login, @RequestParam String sender) throws TrackerServiceException {
        String type;
        if(login.equals(sender)){
            type = ParameterConstant.OUTPUT_MESSAGE;
        } else {
            type = ParameterConstant.INPUT_MESSAGE;
        }
        service.deleteMessage(id, type);
        model.addAttribute(ParameterConstant.WRONG_DATA, ParameterConstant.MESSAGE_CONGRAT);
        if(type.equals(ParameterConstant.INPUT_MESSAGE)){
            model.addAttribute(ParameterConstant.MESSAGES, service.checkInputMessage(login, 1));
        } else {
            model.addAttribute(ParameterConstant.MESSAGES, service.checkOutputMessage(login, 1));
        }

        return PageConstant.PATH_PAGE_MESSAGE;
    }


}
