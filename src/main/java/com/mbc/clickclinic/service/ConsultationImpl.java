package com.mbc.clickclinic.service;

import com.mbc.clickclinic.dao.ConsultationRepository;
import com.mbc.clickclinic.entities.Consultation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Consultation ConsultationlById(Long id) {
        return consultationRepository.findById(id).get();
    }
}
