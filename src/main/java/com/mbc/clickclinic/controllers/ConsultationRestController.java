package com.mbc.clickclinic.controllers;

import com.mbc.clickclinic.entities.*;
import com.mbc.clickclinic.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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

    @Autowired
    public ConsultationRestController(ConsultationService consultationService, OrdonnanceService ordonnanceService,@Lazy MedecinService medecinService,@Lazy RendezvousService rendezvousService, MedicamentService medicamentService,DossierMedicalService dossierMedicalService){
        this.consultationService = consultationService;
        this.ordonnanceService = ordonnanceService;
        this.medecinService = medecinService;
        this.rendezvousService = rendezvousService;
        this.medicamentService = medicamentService;
        this.dossierMedicalService = dossierMedicalService;
    }

    @GetMapping("/consultations")
    public String getConsultations(Model model){
        model.addAttribute("allConsultations",consultationService.Consultations());
        return "consultation/consultationList";
    }

    @PostMapping("/createConsultation")
    public String createConsultation(@ModelAttribute(value = "consultation") Consultation consultation,@ModelAttribute(value = "ordonnance") Ordonnance ordonnance, Model model){
        if(consultation.getRendezvous().getPatient().getDossierMedicale() == null){
            return "redirect:/dossier/create";
        }
        consultation.setOrdonnance(ordonnance);
        consultation.setDossierMedicale(consultation.getRendezvous().getPatient().getDossierMedicale());
        consultationService.saveConsultation(consultation);
        ordonnance.setConsultation(consultation);
        ordonnanceService.saveOrdonnance(ordonnance);
        Rendezvous rendezvous = rendezvousService.RendezvousById(consultation.getRendezvous().getId());
        rendezvous.setConsultation(consultation);
        rendezvous.setStatut(1);
        rendezvousService.updateRendezvous(rendezvous);
        return "consultation/createConsultation";
    }

    @GetMapping("/createConsultation")
    public String createConsultation(Model model){
        model.addAttribute("consultation", new Consultation());
        model.addAttribute("rendezList", rendezvousService.rendezvousByDateAndStatut(LocalDate.now(),0));
        model.addAttribute("ordonnance", new Ordonnance());
        model.addAttribute("medicamentList", medicamentService.Medicaments());
        return "consultation/createConsultation";
    }


    @GetMapping("/consultations/{id}")
    public String getConsultation(@PathVariable Integer id, Model model){
        model.addAttribute("consultationAsked",consultationService.ConsultationlById(id));
        return "consultation/showConsultation";
    }

    @PostMapping("/updateConsultation")
    public Consultation updateConsultation(@RequestBody Consultation consultation){
        return consultationService.updateConsultation(consultation);
    }

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
        model.addAttribute("consultation", consultation);
        return "consultation/showConsultation";
    }

   /* @PostMapping("/consultation/addPayment")
    public Consultation addPaymentToConsultation(@RequestParam Long idp, @RequestParam Long idc){
        paymentService.setConsultationForPaiement(consultationService.ConsultationlById(idc.intValue()), paymentService.paymentById(idp.intValue()));
        return consultationService.ConsultationlById(idc.intValue());
    }*/

}
