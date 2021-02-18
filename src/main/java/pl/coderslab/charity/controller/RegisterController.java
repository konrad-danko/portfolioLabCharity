package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.repository.RoleRepository;


@Controller
@RequestMapping(path = "/register")
public class RegisterController {

    private final RoleRepository roleRepository;
    public RegisterController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    //create a user
    @GetMapping(path = "/create")
    public String initiateCreateUser(Model model){
        User user = new User();
        user.setRole(roleRepository.findByRoleName("ROLE_USER"));
        model.addAttribute("user", user);
        return "register";
    }


}
