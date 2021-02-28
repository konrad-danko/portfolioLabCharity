package pl.coderslab.charity.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.service.CurrentUser;

@Controller
public class HomeController {

    private final DonationRepository donationRepository;
    private final InstitutionRepository institutionRepository;
    public HomeController(DonationRepository donationRepository,
                          InstitutionRepository institutionRepository) {
        this.donationRepository = donationRepository;
        this.institutionRepository = institutionRepository;
    }

    @RequestMapping("/")
    public String homeAction(Model model, @AuthenticationPrincipal CurrentUser customUser){

        int userStatusId = customUser==null ? 0 : customUser.getUser().getUserStatus().getId();
        if(userStatusId==2){
            SecurityContextHolder.clearContext();
            return "redirect:/login";
        }

        String firstName = customUser==null ? "" :customUser.getUser().getFirstName();
        int roleId = customUser==null ? 0 :customUser.getUser().getRole().getId();

        //to jest też ciekawa sprawa:
        //https://docs.spring.io/spring-security/site/docs/3.0.x/reference/taglibs.html
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        int numberOfBags = donationRepository.findAll()
                .stream()
                .mapToInt(Donation::getQuantity)
                .sum();
        model.addAttribute("firstName", firstName);
        model.addAttribute("numberOfBags", numberOfBags);
        model.addAttribute("numberOfDonations", donationRepository.count());
        model.addAttribute("allInstitutions", institutionRepository.findAllOrderedByName());

        if (roleId==1) {
            return "admin/adminMainPageAdmin";
        }
        if (roleId==2) {
            return "redirect:/donator/home";
        }
        return "index";
    }
}
