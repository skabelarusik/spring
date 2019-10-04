package by.epam.crackertracker.controller;

import by.epam.crackertracker.entity.Role;
import by.epam.crackertracker.entity.User;
import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.service.UserService;
import by.epam.crackertracker.util.ParameterConstant;
import freemarker.ext.beans.MapModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpRequest;
import java.util.Locale;

@Controller
@RequestMapping("/")
public class UserController {

    @Autowired
    public UserService userService;

    @Autowired
    private MessageSource messageSource;


    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/users")
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.selectAllUsers());
        return "usersList";
    }

    @GetMapping("/user/{id}")
    public String getById(@PathVariable("id") int id, Model model) {
  //      model.addAttribute("user", userService.getById(id));
        return "showUser";
    }
/*
    @GetMapping("/user2/{id}")
    public @ResponseBody User getById2(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return userService.getById(id);
    }


 */
    @GetMapping("/addUser")
    public String createUserPage() {
        return "createUser";
    }


    @PostMapping("/addUser")
    public String addUser(@ModelAttribute("user") User user) throws Exception {
   //     userService.save(user);
        return "redirect:/users";
    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute("user") User user) {
    //    userService.update(user);
        return "redirect:/user/" + user.getId();
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") int id, Model model) {
   //     model.addAttribute("user", userService.getById(id));
        return "editUser";
    }

    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
    //    userService.delete(id);
        return "redirect:/users";
    }

    @PostMapping("/login")
    public String loginUser(HttpServletRequest request) {

        User user = null;
        String page = null;
        try {
            user = userService.checkUser(request.getParameter(ParameterConstant.PARAM_LOGIN),
                    request.getParameter(ParameterConstant.PARAM_PASSWORD));
            if(user.getActive() == 0){
                request.setAttribute("messageException", "YOU WAS BANNED");
                return  "index";
            }
            request.getSession(true).setAttribute("login", user.getLogin());
            request.getSession().setAttribute("name", user.getName());
            request.getSession().setAttribute("balance", user.getBalance());
            if(user.getRole() == Role.ADMIN){
                page = "admin";
            } else{
                return null;
            }
        } catch (TrackerServiceException e) {
            request.setAttribute("messageException", "WRONG PASSWORD OR LOGIN");
            page = "index";
        }
        return page;
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }

    @GetMapping("/registration")
    public String registration(){
        return "register";
    }

}