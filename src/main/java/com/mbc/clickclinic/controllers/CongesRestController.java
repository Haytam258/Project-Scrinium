package com.mbc.clickclinic.controllers;

import com.mbc.clickclinic.entities.Conges;
import com.mbc.clickclinic.service.CongesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CongesRestController {

    @Autowired
    private CongesService congesService;

    @PostMapping("/createConges")
    public Conges createConges(@RequestBody Conges conges) {

        return congesService.createConges(conges);
    }
}
