package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.service.UserService;

import javax.validation.Valid;


@Controller
@RequestMapping(path = "/register")
public class RegisterController {

    private final UserService userService;
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    //create a user
    @GetMapping(path = "/create")
    public String initiateCreateUser(Model model){
        User user = new User();
        model.addAttribute("user", user);
        return "register";
    }
    @PostMapping(path = "/create")
    public String processCreateUser(@ModelAttribute @Valid User user, BindingResult result){
        if(result.hasErrors()){
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/login";
    }
}
