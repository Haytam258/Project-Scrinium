package com.mbc.clickclinic.controllers;


import com.mbc.clickclinic.entities.Patient;
import com.mbc.clickclinic.service.*;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class PatientRestController {

    private final PatientService patientService;
    private final MedecinService medecinService;
    private final RendezvousService rendezvousService;
    private final ConsultationService consultationService;
    private final EmailService emailService;

    @Autowired
    public PatientRestController(PatientService patientService, MedecinService medecinService, @Lazy RendezvousService rendezvousService, ConsultationService consultationService, EmailService emailService){
        this.patientService = patientService;
        this.medecinService = medecinService;
        this.rendezvousService = rendezvousService;
        this.consultationService = consultationService;
        this.emailService = emailService;
    }

    @GetMapping("/patients")
    public String getPatients(Model model){
        model.addAttribute("allPatients", patientService.patients());
        return "patient/patientList";
    }

    @GetMapping("/patients/{id}")
    public String getPatient(@PathVariable Integer id,Model model){
        model.addAttribute("patientGet", patientService.PatientById(id));
        if(consultationService.getAllConsultationsByDossierMedicale(patientService.PatientById(id).getDossierMedicale()) == null && patientService.PatientById(id).getDossierMedicale() == null){
            model.addAttribute("noConsultations", false);
        }
        else {
            model.addAttribute("consultations", consultationService.getAllConsultationsByDossierMedicale(patientService.PatientById(id).getDossierMedicale()));
        }
        return "patient/patientInfo";
    }


    @GetMapping("/createPatient")
    public String createPatient(Model model){
        model.addAttribute("patient", new Patient());
        return "patient/createPatient";
    }

    @PostMapping("/createPatient")
    public String createPatient(Model model, @ModelAttribute Patient patient){
        if(!(patient.getMobil().matches("[0][6][0-9]{8}"))){
            model.addAttribute("telInvalid", "Numéro de téléphone invalide !");
        }
        else {
            if(patientService.savePatient(patient, model) != null){
                model.addAttribute("patientCreated", "Le patient a été enregistré !");
                if(EmailValidator.getInstance().isValid(patient.getEmail())){
                    emailService.sendSimpleMessage(patient.getEmail(),"Bienvenu à Scrinium \nVotre compte de la clinique Scrinium a été créé.","Compte Créé");
                }
            }
        }
        return "patient/createPatient";
    }

    @GetMapping("/todayPatient")
    public String getPatientOfToday(Model model){
        List<Patient> patientList = patientService.getPatientsOfToday(LocalDate.now());
        if(patientList.size() != 0){
            model.addAttribute("patientsOfToday", patientList);
            model.addAttribute("medecinList", medecinService.medecins());
            model.addAttribute("rendezvousNow", rendezvousService.RendezvousByDate(LocalDate.now()));
        }
        else {
            model.addAttribute("noPatientsToday", "Pas de patients pour aujourd'hui");
        }
        return "patient/patientParRendezvous";
    }

    @GetMapping("/modifyPatient")
    public String modifyPatient(Model model){
        model.addAttribute("newpatient", new Patient());
        return "patient/patientProfile";
    }

    @PostMapping("/modifyPatient")
    public String modifyPatient(@ModelAttribute("newpatient") Patient patient, Model model){
        Patient patient1 = patientService.PatientById(2);
        patient1.setEmail(patient.getEmail());
        patient1.setPassword(patient.getPassword());
        patient1.setRegion(patient.getRegion());
        patient1.setMobil(patient.getMobil());
        patient1.setCin(patient.getCin());
        if(!(patient.getMobil().matches("[0][6][0-9]{8}"))){
            model.addAttribute("telInvalid", "Numéro de téléphone invalide !");
        }
        else {
            if(patientService.savePatient(patient1, model) != null){
                model.addAttribute("patientCreated", "Vos informations ont été modifiées !");
                if(EmailValidator.getInstance().isValid(patient.getEmail())){
                    emailService.sendSimpleMessage(patient.getEmail(),"Votre compte Scrinium a été modifié avec succès !","Compte Modifié");
                }
            }
        }
        return "patient/patientProfile";
    }

    @GetMapping("/deletePatient/{id}")
    public String deletePatient(@PathVariable Integer id){
        Patient patient = patientService.PatientById(id);
        if(EmailValidator.getInstance().isValid(patient.getEmail())){
            emailService.sendSimpleMessage(patient.getEmail(),"Votre compte Scrinium a été supprimé.","Compte Supprimé Scrinium");
        }
        patientService.deletePatient(patient);
        return "redirect:/patients";
    }


}
