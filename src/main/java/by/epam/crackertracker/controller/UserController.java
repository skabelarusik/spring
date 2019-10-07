package by.epam.crackertracker.controller;

import by.epam.crackertracker.entity.Gender;
import by.epam.crackertracker.entity.Role;
import by.epam.crackertracker.entity.User;
import by.epam.crackertracker.exception.TrackerServiceException;
import by.epam.crackertracker.service.UserService;
import by.epam.crackertracker.util.PageConstant;
import by.epam.crackertracker.util.ParameterConstant;
import freemarker.ext.beans.MapModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.net.http.HttpRequest;
import java.time.LocalDate;
import java.util.Locale;

@Controller
@RequestMapping(PageConstant.REQUEST_USER)
public class UserController {

    @Autowired
    public UserService userService;

    @GetMapping("/select")
    public String selectAllUsers(Model model) {
      //  model.addAttribute("users", userService.selectAllUsers());
        return "resultUser";
    }

    @PostMapping("/select_by_status")
    public String selectAllUsersByStatus(Model model) {
        model.addAttribute("users", userService.selectAllUsers());
        return "resultUser";
    }

    @PostMapping("/select_by_gender")
    public String selectAllUsersByGender(Model model) {
        model.addAttribute("users", userService.selectAllUsers());
        return "resultUser";
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

    @PostMapping("/update")
    public String updateUser(@ModelAttribute("user") User user) {
    //    userService.update(user);
        return "redirect:/user/" + user.getId();
    }

    @PostMapping("/update_role")
    public String updateUserRole(@ModelAttribute("user") User user) {
        //    userService.update(user);
        return "redirect:/user/" + user.getId();
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") int id, Model model) {
   //     model.addAttribute("user", userService.getById(id));
        return "editUser";
    }

    @PostMapping("/delete")
    public String deleteUser(@PathVariable("id") int id) {
    //    userService.delete(id);
        return "redirect:/users";
    }

    @PostMapping(PageConstant.URI_LOGIN)
    public String loginUser(HttpServletRequest request) {
/*
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

 */
User user = new User();
        user.setId(1);
        user.setName("Andrey");
        user.setSurname("Krupin");
        user.setLogin("clockworktimes");
        user.setRole(Role.ADMIN);
        user.setBalance(new BigDecimal(11));
        user.setPath("asd");
        request.getSession(true).setAttribute("user", user);
        request.getSession().setAttribute(ParameterConstant.START_PAGE, "admin");
        return "user";
    }

    @GetMapping("admin")
    public String adminPage(){
        return "admin";
    }

    @PostMapping(PageConstant.URI_REGISTER)
    public String registration(){
        return PageConstant.PATH_PAGE_REGISTER;
    }

    @PostMapping(PageConstant.URI_DEP)
    public String deposit(){
        return PageConstant.PATH_DEPOSIT;
    }

    @GetMapping(PageConstant.URI_REQUEST)
    public String request(){
        return PageConstant.PATH_LIST_USER;
    }

}