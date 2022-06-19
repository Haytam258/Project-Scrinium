package com.mbc.clickclinic.controllers;

import com.mbc.clickclinic.entities.CategorieMedicament;
import com.mbc.clickclinic.entities.Medicament;
import com.mbc.clickclinic.entities.TypeMedicament;
import com.mbc.clickclinic.service.MedicamentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MedicamentRestController {

    private final MedicamentService medicamentService;

    @Autowired
    public MedicamentRestController(MedicamentService medicamentService){
        this.medicamentService = medicamentService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/createMedicament")
    public String createMedicament(Model model){
        model.addAttribute("medicament", new Medicament());
        model.addAttribute("typeMedicament", medicamentService.typeMedicaments());
        model.addAttribute("categorieMedicament", medicamentService.categorieMedicaments());
        return "medicament/createMedicament";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/createMedicament")
    public String createMedicament(@ModelAttribute Medicament medicament, Model model){
        if(medicamentService.MedicamentByCodeATC(medicament.getCodeATC()) != null){
            model.addAttribute("medicamentFail", "Ce médicament avec ce Code ATC existe déjà !");
        }
        else {
            medicamentService.saveMedicament(medicament);
            model.addAttribute("medicamentSuccess", "Medicament créé avec succès !");
        }
        return "medicament/createMedicament";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/createTypeMedicament")
    public String createTypeMedicament(Model model){
        model.addAttribute("newTypeMedicament", new TypeMedicament());
        return "medicament/createTypeMedi";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/createTypeMedicament")
    public String createTypeMedicament(@ModelAttribute(value = "newTypeMedicament") TypeMedicament newTypeMedicament, Model model){
        medicamentService.saveType(newTypeMedicament);
        model.addAttribute("typeSuccess", "Type créé avec succès !");
        return "medicament/createTypeMedi";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/createCategorieMedicament")
    public String createCategorieMedicament(Model model){
        model.addAttribute("newCategorie", new CategorieMedicament());
        return "medicament/createCategorieMedi";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/createCategorieMedicament")
    public String createCategorieMedicament(@ModelAttribute(value = "newCategorie") CategorieMedicament newCategorie, Model model){
        medicamentService.saveCategorie(newCategorie);
        model.addAttribute("categorieSuccess", "Catégorie créée avec succès !");
        return "medicament/createCategorieMedi";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/deleteMedicament/{id}")
    public String deleteMedicament(@PathVariable Integer id){
        medicamentService.deleteMedicament(medicamentService.MedicamentById(id));
        return "redirect:/medicaments";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'MEDECIN')")
    @GetMapping("/medicaments")
    public String getMedicaments(Model model){
        model.addAttribute("medicamentList", medicamentService.Medicaments());
        return "medicament/medicamentList";
    }

}
