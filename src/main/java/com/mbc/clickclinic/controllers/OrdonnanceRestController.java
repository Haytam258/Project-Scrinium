package com.mbc.clickclinic.controllers;

import com.mbc.clickclinic.entities.Ordonnance;
import com.mbc.clickclinic.service.OrdonnanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrdonnanceRestController {

    private final OrdonnanceService ordonnanceService;

    @Autowired
    public OrdonnanceRestController(OrdonnanceService ordonnanceService){
        this.ordonnanceService = ordonnanceService;
    }

    @GetMapping("/ordonnances")
    public List<Ordonnance> getOrdonnances(){
        return ordonnanceService.Ordonnances();
    }

    @GetMapping("/ordonnances/{id}")
    public Ordonnance getOrdonnance(@PathVariable Long id){
        return ordonnanceService.OrdonnanceById(id);
    }

    @PostMapping("/createOrdonnance")
    public Ordonnance createOrdonnance(@RequestBody Ordonnance ordonnance){
        return ordonnanceService.saveOrdonnance(ordonnance);
    }

    @PostMapping("/updateOrdonnance")
    public Ordonnance updateOrdonnance(@RequestBody Ordonnance ordonnance){
        return ordonnanceService.updateOrdonnance(ordonnance);
    }

    @PostMapping("/deleteOrdonnance/{id}")
    public void deleteOrdonnance(@PathVariable Long id){
        ordonnanceService.deleteOrdonnance(ordonnanceService.OrdonnanceById(id));
    }
}
