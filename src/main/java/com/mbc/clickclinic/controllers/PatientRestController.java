package com.mbc.clickclinic.controllers;


import com.mbc.clickclinic.entities.Patient;
import com.mbc.clickclinic.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
//@RestController
public class PatientRestController {

    private final PatientService patientService;

    @Autowired
    public PatientRestController(PatientService patientService){
        this.patientService = patientService;
    }

    @GetMapping("/patients")
    public List<Patient> getPatients(){
        return patientService.patients();
    }

    @GetMapping("/patients/{id}")
    public Patient getPatient(@PathVariable Long id){
        return patientService.PatientById(Math.toIntExact(id));
    }

    /*@PostMapping("/createPatient")
    public Patient createPatient(@RequestBody Patient patient){
        return patientService.savePatient(patient);
    }*/

    @GetMapping("/createPatient")
    public String createPatient(Model model){
        model.addAttribute("patient", new Patient());
        return "patient/createPatient";
    }

    @PostMapping("/createPatient")
    public String createPatient(Model model, @ModelAttribute Patient patient){
        patientService.savePatient(patient, model);
        return "patient/createPatient";
    }

    @GetMapping("/todayPatient")
    public String getPatientOfToday(Model model){
        List<Patient> patientList = patientService.getPatientsOfToday(LocalDate.now());
        if(patientList.size() != 0){
            model.addAttribute("patientsOfToday", patientList);
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

    @PostMapping("/deletePatient/{id}")
    public void deletePatient(@PathVariable Long id){
        patientService.deletePatient(patientService.PatientById(Math.toIntExact(id)));
    }


}
