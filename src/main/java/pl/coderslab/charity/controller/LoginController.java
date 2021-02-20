package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    //login a user
    @GetMapping(path = "/login")
    public String initiateLoginVerification(){
        return "login";
    }
}
