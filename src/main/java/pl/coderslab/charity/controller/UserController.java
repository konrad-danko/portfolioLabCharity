package pl.coderslab.charity.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.model.Role;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.model.UserStatus;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.RoleRepository;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.repository.UserStatusRepository;
import pl.coderslab.charity.service.CurrentUser;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "/admin/user")
public class UserController {

    private final UserRepository userRepository;
    private final DonationRepository donationRepository;
    private final RoleRepository roleRepository;
    private final DisplayParams displayParams;
    private final UserStatusRepository userStatusRepository;
    public UserController(UserRepository userRepository,
                          DonationRepository donationRepository,
                          RoleRepository roleRepository,
                          DisplayParams displayParams,
                          UserStatusRepository userStatusRepository) {
        this.userRepository = userRepository;
        this.donationRepository = donationRepository;
        this.roleRepository = roleRepository;
        this.displayParams = displayParams;
        this.userStatusRepository = userStatusRepository;
    }

    private final static String SHOW_ACTION_MSG = "Dane ";
    private final static String EDIT_ACTION_MSG = "Edytuj dane ";
    private final static String DEL_ACTION_MSG = "Potwierdź usunięcie danych ";

    private String getHeaderMessage (User user, String message){
        String userRoleDesc = "";
        if(user!=null && user.getRole().getId()==1) {
            userRoleDesc = "administratora";
        }
        if(user!=null && user.getRole().getId()==2) {
            userRoleDesc = "darczyńcy";
        }
        return message + userRoleDesc;
    }

    @ModelAttribute("firstName")
    public String getFirstName(@AuthenticationPrincipal CurrentUser customUser){
        return customUser==null ? "" :userRepository.findById(customUser.getUser().getId()).get().getFirstName();
    }

    @ModelAttribute("allRoles")
    public List<Role> getAllRoles(){
        return roleRepository.findAll();
    }

    @ModelAttribute("allUserStatuses")
    public List<UserStatus> getAllUserStatuses() {
        return userStatusRepository.findAll();
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
        model.addAttribute("headerMessage", getHeaderMessage(user, SHOW_ACTION_MSG));
        displayParams.setShowParams(model);
        return "admin/adminFormUser";
    }

    //edit a user
    @GetMapping(path = "/editUser/{id}")
    public String initiateEditUser(Model model,
                                   @PathVariable long id) {
        User user = userRepository.findById(id).orElse(null);
        model.addAttribute("user", user);
        model.addAttribute("headerMessage", getHeaderMessage(user, EDIT_ACTION_MSG));
        displayParams.setAddEditParams(model);
        return "admin/adminFormUser";
    }
    @PostMapping(path = "/editUser/{id}")
    public String processEditUser(@ModelAttribute @Valid User user,
                                  BindingResult result,
                                  Model model,
                                  @PathVariable long id) {
        if (result.hasErrors()) {
            model.addAttribute("headerMessage", getHeaderMessage(user, EDIT_ACTION_MSG));
            displayParams.setAddEditParams(model);
            return "admin/adminFormUser";
        }
        //jeśli w bazie jest jeden jedyny admin to nie można odebrać mu praw admina:
        boolean isOnlyOneAdmin = userRepository.findAllUsersByRoleIdOrderedByLastName(1).size()==1;
        boolean isAnAdmin = userRepository.findById(id).get().getRole().getId()==1;
        boolean toBeNonAdmin = user.getRole().getId()!=1;
        if(isOnlyOneAdmin && isAnAdmin && toBeNonAdmin){
            return "redirect:/admin/user/showUser/"+user.getId();
        }
        userRepository.save(user);
        return "redirect:/admin/user/showUser/"+user.getId();
    }

    //delete a user
    @GetMapping(path = "/deleteUser/{id}")
    public String initiateDeleteUser(Model model, @PathVariable long id) {
        User user = userRepository.findById(id).orElse(null);
        model.addAttribute("user", user);
        model.addAttribute("headerMessage", getHeaderMessage(user, DEL_ACTION_MSG));
        displayParams.setDelParams(model);
        return "admin/adminFormUser";
    }
    @PostMapping(path = "/deleteUser/{id}")
    public String processDeleteUser(@ModelAttribute User user, 
                                    @PathVariable long id,
                                    @AuthenticationPrincipal CurrentUser customUser) {
        boolean selfDeletion = customUser.getUser().getId()== userRepository.findById(id).get().getId();
        int userRoleId = userRepository.findById(id).get().getRole().getId();
        boolean hasAnyDonation = donationRepository.findAllByUser_Id(id).size()>0;
        //jeśli w bazie jest jeden jedyny admin to nie można go usunąć:
        boolean isAnAdmin = userRoleId==1;
        boolean isOnlyOneAdmin = userRepository.findAllUsersByRoleIdOrderedByLastName(1).size()==1;
        if(selfDeletion || hasAnyDonation || (isAnAdmin && isOnlyOneAdmin)){
            return "redirect:/admin/user/showUser/"+id;
        }
        userRepository.delete(user);
        return "redirect:/admin/user/showAllUsers/"+userRoleId;
    }
}
