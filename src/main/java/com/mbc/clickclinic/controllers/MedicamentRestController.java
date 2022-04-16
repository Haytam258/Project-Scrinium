package com.mbc.clickclinic.controllers;

import com.mbc.clickclinic.entities.Medicament;
import com.mbc.clickclinic.service.MedicamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MedicamentRestController {

    private final MedicamentService medicamentService;

    @Autowired
    public MedicamentRestController(MedicamentService medicamentService){
        this.medicamentService = medicamentService;
    }

    @PostMapping("/createMedicament")
    public Medicament createMedicament(@RequestBody Medicament medicament){
        return medicamentService.saveMedicament(medicament);
    }

    @PostMapping("/modifyMedicament")
    public Medicament modifyMedicament(@RequestBody Medicament medicament){
        return medicamentService.updateMedicament(medicament);
    }

    @PostMapping("/deleteMedicament/{id}")
    public void deleteMedicament(@PathVariable Long id){
        medicamentService.deleteMedicament(medicamentService.MedicamentById(id));
    }

    @GetMapping("/medicaments")
    public List<Medicament> getMedicaments(){
        return medicamentService.Medicaments();
    }

    @GetMapping("/medicaments/{id}")
    public Medicament getMedicament(@PathVariable Long id){
        return medicamentService.MedicamentById(id);
    }
}
