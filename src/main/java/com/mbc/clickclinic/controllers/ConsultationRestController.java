package com.mbc.clickclinic.controllers;

import com.mbc.clickclinic.service.ConsultationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConsultationRestController {

    private final ConsultationService consultationService;

    @Autowired
    public ConsultationRestController(ConsultationService consultationService){
        this.consultationService = consultationService;
    }

}
