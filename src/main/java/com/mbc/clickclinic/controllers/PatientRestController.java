package com.mbc.clickclinic.controllers;


import com.mbc.clickclinic.entities.Patient;
import com.mbc.clickclinic.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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

    @PostMapping("/createPatient")
    public Patient createPatient(@RequestBody Patient patient){
        return patientService.savePatient(patient);
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
