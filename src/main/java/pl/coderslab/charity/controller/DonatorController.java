package pl.coderslab.charity.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.model.User;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.UserRepository;
import pl.coderslab.charity.service.CurrentUser;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping(path = "/donator")
public class DonatorController {

    private final UserRepository userRepository;
    private final DonationRepository donationRepository;
    private final DisplayParams displayParams;
    private final BCryptPasswordEncoder passwordEncoder;
    public DonatorController(UserRepository userRepository,
                             DonationRepository donationRepository,
                             DisplayParams displayParams,
                             BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.donationRepository = donationRepository;
        this.displayParams = displayParams;
        this.passwordEncoder = passwordEncoder;
    }

    @ModelAttribute("firstName")
    public String getFirstName(@AuthenticationPrincipal CurrentUser customUser){
        return customUser==null ? "" :userRepository.findById(customUser.getUser().getId()).get().getFirstName();
    }

    //show Donator's home page
    @GetMapping(path = "/home")
    public String showHomePage(Model model, @AuthenticationPrincipal CurrentUser customUser){

        long userId = customUser.getUser().getId();
        model.addAttribute("allDonations", donationRepository.getUserDonationsSorted(userId));
        return "admin/adminMainPageDonator";
    }

    //show a user
    @GetMapping(path = "/userShow")
    public String showUser(Model model, @AuthenticationPrincipal CurrentUser customUser){
        long userId = customUser.getUser().getId();
        User user = userRepository.findById(userId).orElse(null);
        model.addAttribute("user", user);
        model.addAttribute("headerMessage", "Moje dane");
        displayParams.setShowParams(model);
        return "admin/donatorFormUser";
    }

    //edit a user
    @GetMapping(path = "/userEdit")
    public String initiateEditUser(Model model, @AuthenticationPrincipal CurrentUser customUser){
        long userId = customUser.getUser().getId();
        User user = userRepository.findById(userId).orElse(null);
        model.addAttribute("user", user);
        model.addAttribute("headerMessage", "Edytuj dane");
        displayParams.setAddEditParams(model);
        return "admin/donatorFormUser";
    }
    @PostMapping(path = "/userEdit")
    public String processEditUser(@ModelAttribute @Valid User user,
                                  BindingResult result,
                                  Model model) {
        if (result.hasErrors()) {
            model.addAttribute("headerMessage", "Edytuj dane");
            displayParams.setAddEditParams(model);
            return "admin/donatorFormUser";
        }
        userRepository.save(user);
        return "redirect:/donator/userShow";
    }

    //delete a user
    @GetMapping(path = "/userDelete")
    public String initiateDeleteUser(Model model, @AuthenticationPrincipal CurrentUser customUser){
        long userId = customUser.getUser().getId();
        User user = userRepository.findById(userId).orElse(null);
        model.addAttribute("user", user);
        model.addAttribute("headerMessage", "Potwierdź usunięcie swoich danych z aplikacji");
        displayParams.setDelParams(model);
        return "admin/donatorFormUser";
    }
    @PostMapping(path = "/userDelete")
    public String processDeleteUser(@ModelAttribute User user, @AuthenticationPrincipal CurrentUser customUser){
        long userId = user.getId();
        boolean hasAnyDonation = donationRepository.findAllByUser_Id(userId).size()>0;
        if(hasAnyDonation){
            return "redirect:/donator/userShow";
        }
        userRepository.delete(user);
        SecurityContextHolder.clearContext();
        return "redirect:/login";
    }

    //edit user's password
    @GetMapping(path = "/editPassword")
    public String initiateEditUserPassword(Model model){
        model.addAttribute("headerMessage", "Zmień hasło");
        return "admin/donatorFormEditPassword";
    }
    @PostMapping(path = "/editPassword")
    public String processEditUserPassword(HttpServletRequest request,
                                          @AuthenticationPrincipal CurrentUser customUser){
        long userId = customUser.getUser().getId();
        User user = userRepository.findById(userId).orElse(null);
        String oldPassHashed = user.getPassword();

        String oldPasswordForm = request.getParameter("oldPassword");
        String newPassword1Form = request.getParameter("newPassword1");
        String newPassword2Form = request.getParameter("newPassword2");

        boolean notEmptyOldPassword = !oldPasswordForm.isEmpty();
        boolean notEmptyNewPassword1 = !newPassword1Form.isEmpty();
        boolean notEmptyNewPassword2 = !newPassword2Form.isEmpty();
        boolean reinputMatch = newPassword1Form.equals(newPassword2Form);
        boolean oldPassVerify = passwordEncoder.matches(oldPasswordForm, oldPassHashed);
        boolean generalCheck = notEmptyOldPassword && notEmptyNewPassword1 && notEmptyNewPassword2 && reinputMatch && oldPassVerify;
        if (!generalCheck){
            return "redirect:/donator/editPassword";
        }
        user.setPassword(passwordEncoder.encode(newPassword1Form));
        userRepository.save(user);
        return "redirect:/donator/userShow";
    }
}
