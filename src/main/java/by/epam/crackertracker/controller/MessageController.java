package by.epam.crackertracker.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/message")
public class MessageController {

    @GetMapping("/input")
    public String checkInputMessage(){
        return null;
    }

    @GetMapping("/output")
    public String checkOutputMessage(){
        return null;
    }

    @GetMapping("/send")
    public String writeMessage(){
        return null;
    }

    @PostMapping("/send")
    public String sendmessage(){
        return null;
    }


}
