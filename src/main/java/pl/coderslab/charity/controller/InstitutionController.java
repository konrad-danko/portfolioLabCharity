package pl.coderslab.charity.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.charity.model.Institution;
import pl.coderslab.charity.repository.DonationRepository;
import pl.coderslab.charity.repository.InstitutionRepository;
import pl.coderslab.charity.service.CurrentUser;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/admin/institution")
public class InstitutionController {

    private final InstitutionRepository institutionRepository;
    private final DonationRepository donationRepository;
    private final DisplayParams displayParams;
    public InstitutionController(InstitutionRepository institutionRepository,
                                 DonationRepository donationRepository,
                                 DisplayParams displayParams) {
        this.institutionRepository = institutionRepository;
        this.donationRepository = donationRepository;
        this.displayParams = displayParams;
    }

    @ModelAttribute("firstName")
    public String getFirstName(@AuthenticationPrincipal CurrentUser customUser){
        return customUser==null ? "" :customUser.getUser().getFirstName();
    }

    @GetMapping(path = "/showAllInstitutions")
    public String showAllInstitutions(Model model) {
        model.addAttribute("allInstitutions", institutionRepository.findAllOrderedByName());
        return "admin/adminAllInstitutions";
    }

    //show an institution
    @GetMapping(path = "/showInstitution/{id}")
    public String showInstitution(Model model, @PathVariable long id) {
        Institution institution = institutionRepository.findById(id).orElse(null);
        model.addAttribute("institution", institution);
        model.addAttribute("headerMessage", "Dane fundacji");
        displayParams.setShowParams(model);
        return "admin/adminFormInstitution";
    }

    //add an institution
    @GetMapping(path = "/addInstitution")
    public String initiateAddInstitution(Model model) {
        model.addAttribute("institution", new Institution());
        model.addAttribute("headerMessage", "Dodaj nową fundację");
        displayParams.setAddEditParams(model);
        return "admin/adminFormInstitution";
    }
    @PostMapping(path = "/addInstitution")
    public String processAddInstitution(@ModelAttribute @Valid Institution institution,
                                        BindingResult result,
                                        Model model) {
        if (result.hasErrors()) {
            model.addAttribute("headerMessage", "Dodaj nową fundację");
            displayParams.setAddEditParams(model);
            return "admin/adminFormInstitution";
        }
        institutionRepository.save(institution);
        return "redirect:/admin/institution/showInstitution/"+institution.getId();
    }

    //edit an institution
    @GetMapping(path = "/editInstitution/{id}")
    public String initiateEditInstitution(Model model,
                                          @PathVariable long id) {
        Institution institution = institutionRepository.findById(id).orElse(null);
        model.addAttribute("institution", institution);
        model.addAttribute("headerMessage", "Edytuj dane fundacji");
        displayParams.setAddEditParams(model);
        return "admin/adminFormInstitution";
    }
    @PostMapping(path = "/editInstitution/{id}")
    public String processEditInstitution(@ModelAttribute @Valid Institution institution,
                                         BindingResult result,
                                         Model model) {
        if (result.hasErrors()) {
            model.addAttribute("headerMessage", "Edytuj dane fundacji");
            displayParams.setAddEditParams(model);
            return "admin/adminFormInstitution";
        }
        institutionRepository.save(institution);
        return "redirect:/admin/institution/showInstitution/"+institution.getId();
    }

    //delete an institution
    @GetMapping(path = "/deleteInstitution/{id}")
    public String initiateDeleteInstitution(Model model, @PathVariable long id) {
        Institution institution = institutionRepository.findById(id).orElse(null);
        model.addAttribute("institution", institution);
        model.addAttribute("headerMessage", "Potwierdź usunięcie danych fundacji");
        displayParams.setDelParams(model);
        return "admin/adminFormInstitution";
    }
    @PostMapping(path = "/deleteInstitution/{id}")
    public String processDeleteInstitution(@ModelAttribute Institution institution, @PathVariable long id) {
        if(donationRepository.findAllByInstitution_Id(id).size()>0){
            return "redirect:/admin/institution/showInstitution/"+institution.getId();
        }
        institutionRepository.delete(institution);
        return "redirect:/admin/institution/showAllInstitutions";
    }
}
