package by.epam.crackertracker.controller;


import by.epam.crackertracker.entity.Message;
import by.epam.crackertracker.entity.User;
import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.service.MessageService;
import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.util.ParameterConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;

@Controller
@RequestMapping("/message")
public class MessageController {

    @Autowired
    MessageService service;

    @GetMapping("/input")
    public String checkInputMessage(HttpServletRequest request, Model model){
        User user = (User) request.getSession(true).getAttribute(ParameterConstant.USER);
        try {
           model.addAttribute("messages", service.checkInputMessage(user.getLogin(), 1));
        } catch (TrackerServiceException e) {
            e.printStackTrace();
        }
        return PageConstant.PATH_PAGE_MESSAGE;
    }

    @GetMapping("/output")
    public String checkOutputMessage(HttpServletRequest request, Model model){
        User user = (User) request.getSession(true).getAttribute(ParameterConstant.USER);
        try {
            model.addAttribute("messages", service.checkOutputMessage(user.getLogin(), 1));
        } catch (TrackerServiceException e) {
            e.printStackTrace();
        }
        return PageConstant.PATH_PAGE_MESSAGE;
    }

    @GetMapping("/send")
    public String writeMessage(){
        return PageConstant.PATH_SEND_MESSAGE;
    }

    @PostMapping("/send")
    public String sendMessage(HttpServletRequest request){
        String sender = (String) request.getSession(true).getAttribute(ParameterConstant.LOGIN);
        String recipient = request.getParameter(ParameterConstant.PARAM_LOGIN);
        String topic = request.getParameter(ParameterConstant.PARAM_TOPIC);
        String text = request.getParameter(ParameterConstant.TEXT);
        try {
            service.sendMessage(sender, recipient, topic, text);
            request.setAttribute(ParameterConstant.WRONG_DATA_PASS, ParameterConstant.MESSAGE_CONGRAT);
        } catch (TrackerServiceException e) {
            request.setAttribute(ParameterConstant.WRONG_DATA_PASS, ParameterConstant.MESSAGE_ERROR_REGIST);
        }

        return PageConstant.PATH_SEND_MESSAGE;
    }


}
