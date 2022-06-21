package com.mbc.clickclinic.controllers;

import com.mbc.clickclinic.entities.*;
import com.mbc.clickclinic.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ConsultationRestController {

    private final ConsultationService consultationService;
    private final OrdonnanceService ordonnanceService;
    private final MedecinService medecinService;
    private final RendezvousService rendezvousService;
    private final MedicamentService medicamentService;
    private final DossierMedicalService dossierMedicalService;
    private final PatientService patientService;

    @Autowired
    public ConsultationRestController(ConsultationService consultationService, OrdonnanceService ordonnanceService,@Lazy MedecinService medecinService,@Lazy RendezvousService rendezvousService, MedicamentService medicamentService,DossierMedicalService dossierMedicalService, @Lazy PatientService patientService){
        this.consultationService = consultationService;
        this.ordonnanceService = ordonnanceService;
        this.medecinService = medecinService;
        this.rendezvousService = rendezvousService;
        this.medicamentService = medicamentService;
        this.dossierMedicalService = dossierMedicalService;
        this.patientService = patientService;
    }


    @PreAuthorize("hasAnyAuthority('ADMIN','MEDECIN')")
    @GetMapping("/consultations")
    public String getConsultations(Model model){
        CustomUser customUser = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Consultation> consultations = consultationService.Consultations();
        Medecin medecin = medecinService.getMedecinByEmail(customUser.getUsername());
        if(medecin != null){
            consultations.removeIf(consultation -> consultation.getRendezvous().getMedecin() != medecin);
        }
        model.addAttribute("allConsultations",consultations);
        return "consultation/consultationList";
    }

    @PreAuthorize("hasAuthority('MEDECIN')")
    @PostMapping("/createConsultation")
    public String createConsultation(@ModelAttribute(value = "consultation") Consultation consultation, Model model){
        if(consultation.getRendezvous().getPatient().getDossierMedicale() == null){
            return "redirect:/dossier/create";
        }
        Patient patient = consultation.getRendezvous().getPatient();
        patient.setSalleDattente(null);
        consultation.setDossierMedicale(consultation.getRendezvous().getPatient().getDossierMedicale());
        consultationService.saveConsultation(consultation);
        Rendezvous rendezvous = rendezvousService.RendezvousById(consultation.getRendezvous().getId());
        rendezvous.setConsultation(consultation);
        rendezvous.setStatut(1);
        patientService.savePatient(patient);
        rendezvousService.updateRendezvous(rendezvous);
        return "redirect:/createOrdonnance";
    }

    @PreAuthorize("hasAuthority('MEDECIN')")
    @GetMapping("/createOrdonnance")
    public String createOrdonnance(Model model){
        Ordonnance ordonnance = new Ordonnance();
        for(int i = 0; i < 4; i++){
            ordonnance.add(new OrdonnanceItems());
        }
        model.addAttribute("ordonnance", ordonnance);
        model.addAttribute("consultations", consultationService.getTodayConsultation());
        model.addAttribute("medicaments", medicamentService.Medicaments());
        return "ordonnance/createOrdonnance";
    }

    @PreAuthorize("hasAuthority('MEDECIN')")
    @PostMapping("/createOrdonnance")
    public String createOrdonnance(@ModelAttribute("ordonnance") Ordonnance ordonnance, Model model){
        List<OrdonnanceItems> ordonnanceItems = ordonnance.getOrdonnanceItemsList();
        ordonnanceItems.removeIf(ordonnanceItems1 -> ordonnanceItems1.getMedicament() == null);
        if(ordonnanceItems.size() == 0){
            model.addAttribute("ajouterMedicament","Veuillez ajouter un médicament au minimum !");
            for(int i = 0; i < 4; i++){
                ordonnance.add(new OrdonnanceItems());
            }
            model.addAttribute("ordonnance", ordonnance);
            model.addAttribute("consultations", consultationService.getTodayConsultation());
            model.addAttribute("medicaments", medicamentService.Medicaments());
            return "ordonnance/createOrdonnance";
        }
        ordonnance.setOrdonnanceItemsList(ordonnanceItems);
        for(OrdonnanceItems ordonnanceItem : ordonnanceItems){
            ordonnanceItem.setOrdonnance(ordonnance);
        }
        ordonnanceService.saveOrdonnance(ordonnance);
        Consultation consultation = ordonnance.getConsultation();
        consultation.setOrdonnance(ordonnance);
        consultationService.saveConsultation(consultation);
        return "redirect:/consultations";
    }

    @PreAuthorize("hasAuthority('MEDECIN')")
    @GetMapping("/createConsultation")
    public String createConsultation(Model model){
        model.addAttribute("consultation", new Consultation());
        model.addAttribute("rendezList", rendezvousService.rendezvousByDateAndStatut(LocalDate.now(),0));
        return "consultation/createConsultation";
    }


    @PreAuthorize("hasAnyAuthority('MEDECIN')")
    @GetMapping("/consultations/{id}")
    public String getConsultation(@PathVariable Integer id, Model model){
        model.addAttribute("consultationAsked",consultationService.ConsultationlById(id));
        return "consultation/showConsultation";
    }


    @PreAuthorize("hasAnyAuthority('ADMIN','MEDECIN')")
    @GetMapping("/deleteConsultation/{id}")
    public String deleteConsultation(@PathVariable Integer id){
        consultationService.deleteConsultation(consultationService.ConsultationlById(id));
        return "redirect:/consultations";
    }

    @GetMapping("/consultations/dossier/{id}")
    public String indexConsultations(@PathVariable(value = "id") int id, Model model){
        List<Consultation> consultations = consultationService.getAllConsultationsByDossierMedicale(dossierMedicalService.getDossierById(id));
        model.addAttribute("consultations", consultations);
        return "consultation/indexConsultations";
    }

    @GetMapping("/consultations/show/{id}")
    public String showConsultation(@PathVariable(value = "id") int id, Model model){
        Consultation consultation = consultationService.ConsultationlById(id);
        if(consultation.getOrdonnance() == null){
            model.addAttribute("nulled", false);
        }
        else if(consultation.getOrdonnance().getOrdonnanceItemsList() == null){
            model.addAttribute("listnull", false);
        }
        else {
            model.addAttribute("nulled", true);
        }
        model.addAttribute("consultation", consultation);
        return "consultation/showConsultation";
    }


}
