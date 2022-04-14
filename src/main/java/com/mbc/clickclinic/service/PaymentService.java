package com.mbc.clickclinic.service;



import com.mbc.clickclinic.entities.Payment;
import org.springframework.stereotype.Service;
import java.util.List;


public interface PaymentService {
    Payment savePayment(Payment payment);
    void deletePayment(Payment payment);
    Payment updatePayment(Payment payment);
    List<Payment> payments();
    Payment paymentById(Long id);
}
