package pl.coderslab.charity.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.model.*;
import pl.coderslab.charity.repository.CategoryRepository;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.DonationStatusRepository;
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
    private final DonationStatusRepository donationStatusRepository;
    private final DisplayParams displayParams;
    public DonationController(DonationRepository donationRepository,
                              InstitutionRepository institutionRepository,
                              CategoryRepository categoryRepository,
                              DonationStatusRepository donationStatusRepository,
                              DisplayParams displayParams) {
        this.donationRepository = donationRepository;
        this.institutionRepository = institutionRepository;
        this.categoryRepository = categoryRepository;
        this.donationStatusRepository = donationStatusRepository;
        this.displayParams = displayParams;
    }

    @ModelAttribute("allCategories")
    public List<Category> getAllCategories(){
        return this.categoryRepository.findAllOrderedByName();
    }

    @ModelAttribute("allInstitutions")
    public List<Institution> getAllInstitutions(){
        return this.institutionRepository.findAllOrderedByName();
    }

    @ModelAttribute("allDonationStatuses")
    public List<DonationStatus> getAllDonationStatuses() {
        return this.donationStatusRepository.findAll();
    }

    @ModelAttribute("firstName")
    public String getFirstName(@AuthenticationPrincipal CurrentUser customUser){
        return customUser==null ? "" :customUser.getUser().getFirstName();
    }

    //show a donation
    @GetMapping(path = "/showDonation/{id}")
    public String showDonation(Model model, @PathVariable long id){
        Donation donation = donationRepository.findById(id).orElse(null);
        model.addAttribute("donation", donation);
        model.addAttribute("headerMessage", "Szczegóły zbiórki");
        displayParams.setShowParams(model);
        return "admin/donatorFormDonation";
    }

    //add a donation
    @GetMapping(path = "/addDonation")
    public String initiateAddDonation(Model model,
                                      @AuthenticationPrincipal CurrentUser customUser) {
        Donation donation = new Donation();
        donation.setUser(customUser.getUser());
        donation.setDonationStatus(donationStatusRepository.findById(1));
        model.addAttribute("donation", donation);
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

    //edit a donation
    @GetMapping(path = "/editDonation/{id}")
    public String initiateEditDonation(Model model, @PathVariable long id){
        Donation donation = donationRepository.findById(id).orElse(null);
        model.addAttribute("donation", donation);
        model.addAttribute("headerMessage", "Edytuj status zbiórki");
        displayParams.setAddEditParams(model);
        return "admin/donatorFormDonation";
    }
    @PostMapping(path = "/editDonation/{id}")
    public String processEditDonation(@ModelAttribute @Valid Donation donation,
                                      BindingResult result,
                                      Model model,
                                      @PathVariable long id){
        if(result.hasErrors()){
            model.addAttribute("headerMessage", "Edytuj status zbiórki");
            displayParams.setAddEditParams(model);
            return "admin/donatorFormDonation";
        }
        donationRepository.save(donation);
        return "redirect:/donation/showDonation/"+id;
    }
}
