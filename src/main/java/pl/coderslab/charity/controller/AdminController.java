package pl.coderslab.charity.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.model.Role;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.service.CurrentUser;

import java.util.List;

@Controller
@RequestMapping(path = "/admin/user")
public class AdminController {

    private final UserRepository userRepository;
    private final DonationRepository donationRepository;
    private final RoleRepository roleRepository;
    private final DisplayParams displayParams;
    public AdminController(UserRepository userRepository,
                           DonationRepository donationRepository,
                           RoleRepository roleRepository,
                           DisplayParams displayParams) {
        this.userRepository = userRepository;
        this.donationRepository = donationRepository;
        this.roleRepository = roleRepository;
        this.displayParams = displayParams;
    }

    @ModelAttribute("firstName")
    public String getFirstName(@AuthenticationPrincipal CurrentUser customUser){
        return customUser==null ? "" :customUser.getUser().getFirstName();
    }

    @ModelAttribute("allRoles")
    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }

    @GetMapping(path = "/showAllUsers/{roleId}")
    public String showAllUsersByRoleId(Model model, @PathVariable int roleId) {
        String tableTitle = roleId==1 ? "Administratorzy" : "Darczyńcy" ;
        model.addAttribute("tableTitle", tableTitle);
        model.addAttribute("allUsers", userRepository.findAllUsersByRoleIdOrderedByLastName(roleId));
        return "admin/adminAllUsers";
    }

    //show a user
    @GetMapping(path = "/showUser/{id}")
    public String showUser(Model model, @PathVariable long id) {
        User user = userRepository.findById(id).orElse(null);
        model.addAttribute("user", user);

        String headerMessage = "";
        if(user!=null && user.getRole().getId()==1) {
            headerMessage = "Dane administratora";
        }
        if(user!=null && user.getRole().getId()==2) {
            headerMessage = "Dane darczyńcy";
        }
        model.addAttribute("headerMessage", headerMessage);
        displayParams.setShowParams(model);
        return "admin/adminFormUser";
    }

    //edit a user



}
