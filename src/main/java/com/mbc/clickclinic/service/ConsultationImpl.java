package com.mbc.clickclinic.service;

import com.mbc.clickclinic.dao.ConsultationRepository;
import com.mbc.clickclinic.entities.Consultation;
import com.mbc.clickclinic.entities.Payment;
import com.mbc.clickclinic.entities.Rendezvous;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultationImpl implements ConsultationService{

    private final ConsultationRepository consultationRepository;

    @Autowired
    public ConsultationImpl(ConsultationRepository consultationRepository){
        this.consultationRepository = consultationRepository;
    }


    @Override
    public Consultation saveConsultation(Consultation consultation) {
        return consultationRepository.saveAndFlush(consultation);
    }

    @Override
    public void deleteConsultation(Consultation consultation) {
        consultationRepository.delete(consultation);
    }

    //Meme remarque ici (Voir CertificatMedicalImple)
    @Override
    public Consultation updateConsultation(Consultation consultation) {
        return null;
    }

    @Override
    public List<Consultation> Consultations() {
        return consultationRepository.findAll();
    }

    @Override
    public Consultation ConsultationlById(int id) {
        return consultationRepository.findById(id).get();
    }

    public Consultation AddPaiementToConsultation(Payment payment, Consultation consultation){
        consultation.setPayment(payment);
        return consultationRepository.saveAndFlush(consultation);
    }

    public Optional<Consultation> getConsultationByRendezVous(Rendezvous rendezvous){
        return consultationRepository.findById(rendezvous.getConsultation().getId());
    }
}
