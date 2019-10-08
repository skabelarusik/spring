package by.epam.crackertracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("select_curator")
    public String selectCurator(){
        return null;
    }

    @GetMapping("select_del_curator")
    public String selectDeletedCurator(){
        return null;
    }

    @PostMapping("add")
    public String add(){
        return null;
    }



}
