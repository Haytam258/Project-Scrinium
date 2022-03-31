package com.mbc.clickclinic.service;

import com.mbc.clickclinic.entities.Consultation;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface ConsultationService {
    Consultation saveConsultation(Consultation consultation);
    void deleteConsultation(Consultation consultation);
    Consultation updateConsultation(Consultation consultation);
    List<Consultation> Consultations();
    Consultation ConsultationlById(Long id);


}
