package com.mbc.clickclinic.service;

import com.mbc.clickclinic.dao.PaymentRepository;
import com.mbc.clickclinic.entities.Consultation;
import com.mbc.clickclinic.entities.Patient;
import com.mbc.clickclinic.entities.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentImple implements PaymentService{

    private final PaymentRepository paymentRepository;
    private final RendezvousService rendezvousService;
    private final ConsultationService consultationService;

    @Autowired
    public PaymentImple(PaymentRepository paymentRepository, RendezvousService rendezvousService, ConsultationService consultationService){
        this.paymentRepository = paymentRepository;
        this.rendezvousService = rendezvousService;
        this.consultationService = consultationService;
    }
    @Override
    public Payment savePayment(Payment payment) {
        payment.setEquilibre(payment.getTotalBrut() - payment.getMontantDepose());
        return paymentRepository.saveAndFlush(payment);
    }

    @Override
    public void deletePayment(Payment payment) {
        paymentRepository.delete(payment);
    }

    @Override
    public Payment updatePayment(Payment payment) {
        payment.setEquilibre(payment.getTotalBrut() - payment.getMontantDepose());
        return paymentRepository.saveAndFlush(payment);
    }

    @Override
    public List<Payment> payments() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment paymentById(int id) {
        return paymentRepository.findById(id).get();
    }

    /*public Optional<Payment> getPayementOfPatient(Patient patient){
        return paymentRepository.findById(rendezvousService.getRendezvousByPatient(patient).getConsultation().getPayment().getId());
    }*/

    public Payment setConsultationForPaiement(Consultation consultation, Payment payment){
        payment.setConsultation(consultation);
        consultation.setPayment(payment);
        consultationService.saveConsultation(consultation);
        return paymentRepository.saveAndFlush(payment);
    }

    public Payment getPaymentByConsultation(Consultation consultation){
        return paymentRepository.findPaymentByConsultation(consultation);
    }
}
