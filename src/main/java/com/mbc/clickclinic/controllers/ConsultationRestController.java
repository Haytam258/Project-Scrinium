package com.mbc.clickclinic.controllers;

import com.mbc.clickclinic.entities.Consultation;
import com.mbc.clickclinic.entities.Medicament;
import com.mbc.clickclinic.entities.Ordonnance;
import com.mbc.clickclinic.entities.Payment;
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

    @Autowired
    public ConsultationRestController(ConsultationService consultationService, OrdonnanceService ordonnanceService,@Lazy MedecinService medecinService,@Lazy RendezvousService rendezvousService, MedicamentService medicamentService){
        this.consultationService = consultationService;
        this.ordonnanceService = ordonnanceService;
        this.medecinService = medecinService;
        this.rendezvousService = rendezvousService;
        this.medicamentService = medicamentService;
    }

    @GetMapping("/consultations")
    public List<Consultation> getConsultations(){
        return consultationService.Consultations();
    }

    @PostMapping("/createConsultation")
    public String createConsultation(@ModelAttribute(value = "consultation") Consultation consultation,@ModelAttribute(value = "ordonnance") Ordonnance ordonnance, Model model){
        consultation.setOrdonnance(ordonnance);
        consultationService.saveConsultation(consultation);
        ordonnance.setConsultation(consultation);
        ordonnanceService.saveOrdonnance(ordonnance);

        return "consultation/createConsultation";
    }

    @GetMapping("/createConsultation")
    public String createConsultation(Model model){
        model.addAttribute("consultation", new Consultation());
        model.addAttribute("rendezList", rendezvousService.RendezvousByDate(LocalDate.now()));
        model.addAttribute("ordonnance", new Ordonnance());
        model.addAttribute("medicamentList", medicamentService.Medicaments());
        return "consultation/createConsultation";
    }


    @GetMapping("/consultations/{id}")
    public Consultation getConsultation(@PathVariable Long id){
        return consultationService.ConsultationlById(id.intValue());
    }

    @PostMapping("/updateConsultation")
    public Consultation updateConsultation(@RequestBody Consultation consultation){
        return consultationService.updateConsultation(consultation);
    }

    @PostMapping("/deleteConsultation/{id}")
    public void deleteConsultation(@PathVariable Long id){
        consultationService.deleteConsultation(consultationService.ConsultationlById(id.intValue()));
    }

   /* @PostMapping("/consultation/addPayment")
    public Consultation addPaymentToConsultation(@RequestParam Long idp, @RequestParam Long idc){
        paymentService.setConsultationForPaiement(consultationService.ConsultationlById(idc.intValue()), paymentService.paymentById(idp.intValue()));
        return consultationService.ConsultationlById(idc.intValue());
    }*/

}
