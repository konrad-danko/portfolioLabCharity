package pl.coderslab.charity.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.coderslab.charity.model.Category;
import pl.coderslab.charity.model.Donation;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.service.CurrentUser;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(path = "/donation")
public class DonationController {

    private final DonationRepository donationRepository;
    private final InstitutionRepository institutionRepository;
    private final CategoryRepository categoryRepository;
    public DonationController(DonationRepository donationRepository,
                              InstitutionRepository institutionRepository,
                              CategoryRepository categoryRepository) {
        this.donationRepository = donationRepository;
        this.institutionRepository = institutionRepository;
        this.categoryRepository = categoryRepository;
    }

    @ModelAttribute("allCategories")
    public List<Category> getAllCategories(){
        return this.categoryRepository.findAllOrderedByName();
    }

    @ModelAttribute("allInstitutions")
    public List<Institution> getAllInstitutions(){
        return this.institutionRepository.findAllOrderedByName();
    }

    @ModelAttribute("firstName")
    public String getFirstName(@AuthenticationPrincipal CurrentUser customUser){
        return customUser==null ? "" :customUser.getUser().getFirstName();
    }

    //add a donation
    @GetMapping(path = "/addDonation")
    public String initiateAddDonation(Model model) {
        model.addAttribute("donation", new Donation());
        return "form";
    }
    @PostMapping(path = "/addDonation")
    public String processAddDonation(@ModelAttribute @Valid Donation donation,
                                     BindingResult result){
        if(result.hasErrors()){
            return "form";
        }
        donationRepository.save(donation);
        return "form-confirmation";
    }
}
