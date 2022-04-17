package com.mbc.clickclinic.controllers;


import com.mbc.clickclinic.entities.Payment;
import com.mbc.clickclinic.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PaymentRestController {

    private final PaymentService paymentService;

    @Autowired
    public PaymentRestController(PaymentService paymentService){
        this.paymentService = paymentService;
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
        return paymentService.paymentById(id);
    }

    @PostMapping("/deletePayment/{id}")
    public void deletePayment(@PathVariable Long id){
        paymentService.deletePayment(paymentService.paymentById(id));
    }
}
