package com.mbc.clickclinic.controllers;


import com.mbc.clickclinic.entities.Consultation;
import com.mbc.clickclinic.entities.Payment;
import com.mbc.clickclinic.service.ConsultationService;
import com.mbc.clickclinic.service.PatientService;
import com.mbc.clickclinic.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Controller
public class PaymentRestController {

    private final PaymentService paymentService;
    private final ConsultationService consultationService;
    private final PatientService patientService;

    @Autowired
    public PaymentRestController(PaymentService paymentService, ConsultationService consultationService, PatientService patientService){
        this.paymentService = paymentService;
        this.consultationService = consultationService;
        this.patientService = patientService;
    }

    @PreAuthorize("hasAuthority('SECRETAIRE')")
    @GetMapping("/createPaiement")
    public String createPaiement(Model model){
        model.addAttribute("paiement", new Payment());
        model.addAttribute("allConsultations", consultationService.Consultations());
        return "paiement/createPaiement";
    }

    @PreAuthorize("hasAuthority('SECRETAIRE')")
    @PostMapping("/createPaiement")
    public String createPayement(@ModelAttribute(value = "paiement") Payment payment, Model model){
        model.addAttribute("allConsultations", consultationService.Consultations());
        payment.setDatePaiement(LocalDateTime.now());
        Consultation consultation = consultationService.ConsultationlById(payment.getConsultation().getId());
        if(consultation.getPayment() == null){
            if(paymentService.savePayment(payment) != null){
                consultation.setPayment(payment);
                model.addAttribute("paiementSuccess", "Paiement effectué avec succès !");
                consultationService.saveConsultation(consultation);
            }
            else {
                model.addAttribute("paiementFail", "Veuillez vérifiez les informations saisies ");
            }
        }
        else {
            model.addAttribute("paiementExists", "Cette consultation contient déjà un paiement !");
        }
        model.addAttribute("paiement", new Payment());
        return "paiement/createPaiement";
    }

    @PreAuthorize("hasAnyAuthority('SECRETAIRE', 'ADMIN')")
    @GetMapping("/payments")
    public String getPayments(Model model){
        model.addAttribute("allPayments", paymentService.payments());
        return "paiement/paiementList";
    }

    @PreAuthorize("hasAnyAuthority('SECRETAIRE', 'ADMIN')")
    @GetMapping("/payments/modify/{id}")
    public String modifyPayment(@PathVariable("id") Integer id, Model model){
        model.addAttribute("payment",paymentService.paymentById(id));
        return "paiement/modifyPaiement";
    }

    @PreAuthorize("hasAnyAuthority('SECRETAIRE', 'ADMIN')")
    @PostMapping("/modifyPayment")
    public String modifyPayment(@ModelAttribute("payment") Payment payment, Model model){
        payment.setDatePaiement(LocalDateTime.now());
        model.addAttribute("payment",payment);
        if(payment.getTotalBrut() - payment.getMontantDepose() < 0){
            model.addAttribute("paiementImpossible", "Le montant déposé par le client dépasse le total brut de l'opération !");
            return "paiement/modifyPaiement";
        }
        else {
            if(paymentService.updatePayment(payment) != null){
                model.addAttribute("modificationSuccess", "Paiement a été mis en jour !");
            }
            else {
                model.addAttribute("modificationFail", "Veuillez Vérifier les montants et données saisies !");
                return "paiement/modifyPaiement";
            }
        }
        return "redirect:/payments";
    }

}
