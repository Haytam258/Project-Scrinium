package com.mbc.clickclinic.service;

import com.mbc.clickclinic.dao.PaymentRepository;
import com.mbc.clickclinic.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class PaymentImple implements PaymentService{

    private final PaymentRepository paymentRepository;

    @Autowired
    public PaymentImple(PaymentRepository paymentRepository){
        this.paymentRepository = paymentRepository;
    }
    @Override
    public Payment savePayment(Payment payment) {
        return paymentRepository.saveAndFlush(payment);
    }

    @Override
    public void deletePayment(Payment payment) {
        paymentRepository.delete(payment);
    }

    @Override
    public Payment updatePayment(Payment payment) {
        return null;
    }

    @Override
    public List<Payment> payments() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment paymentById(Long id) {
        return paymentRepository.findById(id).get();
    }
}
