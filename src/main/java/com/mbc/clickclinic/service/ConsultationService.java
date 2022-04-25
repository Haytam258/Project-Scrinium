package com.mbc.clickclinic.service;

import com.mbc.clickclinic.entities.Consultation;
import com.mbc.clickclinic.entities.Payment;
import com.mbc.clickclinic.entities.Rendezvous;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;


public interface ConsultationService {
    Consultation saveConsultation(Consultation consultation);
    void deleteConsultation(Consultation consultation);
    Consultation updateConsultation(Consultation consultation);
    List<Consultation> Consultations();
    Consultation ConsultationlById(int id);
    public Consultation AddPaiementToConsultation(Payment payment, Consultation consultation);
    public Optional<Consultation> getConsultationByRendezVous(Rendezvous rendezvous);

}
