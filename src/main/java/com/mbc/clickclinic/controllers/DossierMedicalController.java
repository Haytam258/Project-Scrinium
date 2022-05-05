package com.mbc.clickclinic.controllers;

import com.mbc.clickclinic.entities.DossierMedicale;
import com.mbc.clickclinic.entities.Patient;
import com.mbc.clickclinic.service.DossierMedicalService;
import com.mbc.clickclinic.service.MedecinService;
import com.mbc.clickclinic.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class DossierMedicalController {

    @Autowired
    private DossierMedicalService dossierMedicalService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private MedecinService medecinService;

    @GetMapping("/dossier/index")
    public String index(Model model){
        //fixed idMedecin here til we get it with spring security
        List<DossierMedicale> dossiers = dossierMedicalService.getAllDossiersByMedecin(medecinService.medecinById(1));
        System.out.println(dossiers);
        model.addAttribute("dossiers", dossiers);
        return "dossierMedical/indexMedecin";
    }

    @GetMapping("/dossier/create")
    public String createDossier(Model model){
        DossierMedicale dossierMedical = new DossierMedicale();
        List<Patient> patients = patientService.patients();
        model.addAttribute("dossierMedical", dossierMedical);
        model.addAttribute("patients", patients);
        return "dossierMedical/create";
    }

    @PostMapping("/dossier/save")
    public String saveDossier(@ModelAttribute DossierMedicale dossierMedicale){
        dossierMedicalService.create(dossierMedicale);
        return "redirect:/dossier/index";
    }
}
