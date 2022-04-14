package com.mbc.clickclinic.controllers;


import com.mbc.clickclinic.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PatientRestController {

    private final PatientService patientService;

    @Autowired
    public PatientRestController(PatientService patientService){
        this.patientService = patientService;
    }


}
