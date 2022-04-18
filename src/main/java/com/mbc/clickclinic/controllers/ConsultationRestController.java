package com.mbc.clickclinic.controllers;

import com.mbc.clickclinic.entities.Consultation;
import com.mbc.clickclinic.entities.Payment;
import com.mbc.clickclinic.service.ConsultationService;
import com.mbc.clickclinic.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ConsultationRestController {

    private final ConsultationService consultationService;
    private final PaymentService paymentService;

    @Autowired
    public ConsultationRestController(ConsultationService consultationService, PaymentService paymentService){
        this.consultationService = consultationService;
        this.paymentService = paymentService;
    }

    @GetMapping("/consultations")
    public List<Consultation> getConsultations(){
        return consultationService.Consultations();
    }

    @PostMapping("/createConsultation")
    public Consultation createConsultation(@RequestBody Consultation consultation){
        return consultationService.saveConsultation(consultation);
    }

    @GetMapping("/consultations/{id}")
    public Consultation getConsultation(@PathVariable Long id){
        return consultationService.ConsultationlById(id);
    }

    @PostMapping("/updateConsultation")
    public Consultation updateConsultation(@RequestBody Consultation consultation){
        return consultationService.updateConsultation(consultation);
    }

    @PostMapping("/deleteConsultation/{id}")
    public void deleteConsultation(@PathVariable Long id){
        consultationService.deleteConsultation(consultationService.ConsultationlById(id));
    }

    @PostMapping("/consultation/addPayment")
    public Consultation addPaymentToConsultation(@RequestParam Long idp, @RequestParam Long idc){
        paymentService.setConsultationForPaiement(consultationService.ConsultationlById(idc), paymentService.paymentById(idp));
        return consultationService.ConsultationlById(idc);
    }

}
