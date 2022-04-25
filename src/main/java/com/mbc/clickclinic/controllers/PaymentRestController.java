package com.mbc.clickclinic.controllers;


import com.mbc.clickclinic.entities.Payment;
import com.mbc.clickclinic.service.ConsultationService;
import com.mbc.clickclinic.service.PatientService;
import com.mbc.clickclinic.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
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

    @PostMapping("/createPayement")
    public Payment createPayement(@RequestBody Payment payment){
        return paymentService.savePayment(payment);
    }

    @GetMapping("/payments")
    public List<Payment> getPayments(){
        return paymentService.payments();
    }

    @GetMapping("/payments/{id}")
    public Payment getPayment(@PathVariable Long id){
        return paymentService.paymentById(id.intValue());
    }

    @PostMapping("/deletePayment/{id}")
    public void deletePayment(@PathVariable Long id){
        paymentService.deletePayment(paymentService.paymentById(id.intValue()));
    }

    @GetMapping("/payment/consultation/{id}")
    public Payment getPayementByConsultation(@PathVariable Long id){
        return paymentService.getPaymentByConsultation(consultationService.ConsultationlById(id.intValue()));
    }

    @GetMapping("/payment/patient/{id}")
    public Optional<Payment> getPaymentOfPatient(@PathVariable Long id){
        return paymentService.getPayementOfPatient(patientService.PatientById(id.intValue()));
    }
}
