package com.mbc.clickclinic.controllers;


import com.mbc.clickclinic.entities.Patient;
import com.mbc.clickclinic.service.MedecinService;
import com.mbc.clickclinic.service.PatientService;
import com.mbc.clickclinic.service.RendezvousService;
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

    @Autowired
    public PatientRestController(PatientService patientService, MedecinService medecinService, @Lazy RendezvousService rendezvousService){
        this.patientService = patientService;
        this.medecinService = medecinService;
        this.rendezvousService = rendezvousService;
    }

    @GetMapping("/patients")
    public String getPatients(Model model){
        model.addAttribute("allPatients", patientService.patients());
        return "patient/patientList";
    }

    @GetMapping("/patients/{id}")
    public Patient getPatient(@PathVariable Long id){
        return patientService.PatientById(Math.toIntExact(id));
    }


    @GetMapping("/createPatient")
    public String createPatient(Model model){
        model.addAttribute("patient", new Patient());
        return "patient/createPatient";
    }

    @PostMapping("/createPatient")
    public String createPatient(Model model, @ModelAttribute Patient patient){
        if(patientService.savePatient(patient, model) != null){
            model.addAttribute("patientCreated", "Le patient a été enregistré !");
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

    @PostMapping("/modifyPatient")
    public Patient modifyPatient(@RequestBody Patient patient){
        return patientService.updatePatient(patient);
    }

    @GetMapping("/deletePatient/{id}")
    public String deletePatient(@PathVariable Integer id){
        patientService.deletePatient(patientService.PatientById(id));
        return "redirect:/patients";
    }


}
