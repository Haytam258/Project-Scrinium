package com.mbc.clickclinic.service;



import com.mbc.clickclinic.entities.Consultation;
import com.mbc.clickclinic.entities.Patient;
import com.mbc.clickclinic.entities.Payment;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


public interface PaymentService {
    Payment savePayment(Payment payment);
    void deletePayment(Payment payment);
    Payment updatePayment(Payment payment);
    List<Payment> payments();
    Payment paymentById(Long id);
    public Optional<Payment> getPayementOfPatient(Patient patient);
    public Payment setConsultationForPaiement(Consultation consultation, Payment payment);
    public Payment getPaymentByConsultation(Consultation consultation);
}
