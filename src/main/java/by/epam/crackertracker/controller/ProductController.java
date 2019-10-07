package by.epam.crackertracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
public class ProductController {

    @PostMapping("/search")
    public String search(){
        return null;
    }

    @GetMapping("/select")
    public String selectAll(){
        return null;
    }

    @PostMapping("/select_by_calories")
    public String selectByCalories(){
        return null;
    }

    @PostMapping("/update")
    public String updateProduct(){
        return null;
    }

    @PostMapping("/delete")
    public String delete(){
        return null;
    }

    @PostMapping("/add")
    public String add(){
        return null;
    }


}
