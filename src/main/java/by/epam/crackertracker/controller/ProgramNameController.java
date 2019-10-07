package by.epam.crackertracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/program_name")
public class ProgramNameController {

    @GetMapping("select")
    public String select(){
        return null;
    }

    @GetMapping("select_del")
    public String selectDeleted(){
        return null;
    }



}
