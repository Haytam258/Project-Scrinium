package com.mbc.clickclinic.controllers;

import com.mbc.clickclinic.entities.Ordonnance;
import com.mbc.clickclinic.service.OrdonnanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class OrdonnanceRestController {

    private final OrdonnanceService ordonnanceService;

    @Autowired
    public OrdonnanceRestController(OrdonnanceService ordonnanceService){
        this.ordonnanceService = ordonnanceService;
    }

}
