package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;


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
    public String homeAction(Model model){

        //to jest ciekawa sprawa:
        //https://docs.spring.io/spring-security/site/docs/3.0.x/reference/taglibs.html
        //Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        int numberOfBags = donationRepository.findAll()
                .stream()
                .mapToInt(Donation::getQuantity)
                .sum();
        model.addAttribute("numberOfBags", numberOfBags);
        model.addAttribute("numberOfDonations", donationRepository.count());
        model.addAttribute("allInstitutions", institutionRepository.findAllOrderedByName());
        return "index";
    }
}
