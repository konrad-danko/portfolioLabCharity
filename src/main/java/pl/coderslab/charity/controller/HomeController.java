package pl.coderslab.charity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;

import java.util.List;


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

        int numberOfBags = donationRepository.findAll()
                .stream()
                .mapToInt(Donation::getQuantity)
                .sum();
        model.addAttribute("numberOfBags", numberOfBags);
        model.addAttribute("numberOfDonations", donationRepository.count());
        model.addAttribute("institutionList", institutionRepository.findAll());
        return "index";
    }
}
