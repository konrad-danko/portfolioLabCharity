package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.repository.UserRepository;

import javax.validation.Valid;


@Controller
@RequestMapping(path = "/register")
public class RegisterController {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    public RegisterController(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    //create a user
    @GetMapping(path = "/create")
    public String initiateCreateUser(Model model){
        User user = new User();
        user.setRole(roleRepository.findByRoleName("ROLE_USER"));
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping(path = "/create")
    public String processCreateUser(@ModelAttribute @Valid User user, BindingResult result){
        if(result.hasErrors()){
            return "register";
        }
        userRepository.save(user);
        return "redirect:/";
    }


}
