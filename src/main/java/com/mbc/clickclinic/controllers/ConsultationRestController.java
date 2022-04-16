package com.mbc.clickclinic.controllers;

import com.mbc.clickclinic.entities.Consultation;
import com.mbc.clickclinic.service.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ConsultationRestController {

    private final ConsultationService consultationService;

    @Autowired
    public ConsultationRestController(ConsultationService consultationService){
        this.consultationService = consultationService;
    }

    @GetMapping("/consultations")
    public List<Consultation> getConsultations(){
        return consultationService.Consultations();
    }

    @PostMapping("/createConsultation")
    public Consultation createConsultation(@RequestBody Consultation consultation){
        return consultationService.saveConsultation(consultation);
    }

    @GetMapping("/consultations/{id}")
    public Consultation getConsultation(@PathVariable Long id){
        return consultationService.ConsultationlById(id);
    }

    @PostMapping("/updateConsultation")
    public Consultation updateConsultation(@RequestBody Consultation consultation){
        return consultationService.updateConsultation(consultation);
    }

    @PostMapping("/deleteConsultation/{id}")
    public void deleteConsultation(@PathVariable Long id){
        consultationService.deleteConsultation(consultationService.ConsultationlById(id));
    }

}
