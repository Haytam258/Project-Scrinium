package com.mbc.clickclinic.controllers;

import com.mbc.clickclinic.entities.DossierMedicale;
import com.mbc.clickclinic.entities.Patient;
import com.mbc.clickclinic.service.DossierMedicalService;
import com.mbc.clickclinic.service.MedecinService;
import com.mbc.clickclinic.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        //Need to remember that this only gets DossierMedicals of patients that have a rendez vous, not all Dossiers.
        List<DossierMedicale> dossiers = dossierMedicalService.getAllDossiersByMedecin(medecinService.medecinById(5));
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
        if(patientService.PatientById(dossierMedicale.getPatient().getId()).getDossierMedicale() == null){
            dossierMedicalService.create(dossierMedicale);
            Patient patient = patientService.PatientById(dossierMedicale.getPatient().getId());
            patient.setDossierMedicale(dossierMedicale);
            patientService.savePatient(patient);
        }
        return "redirect:/dossier/index";
    }

    @GetMapping("/dossier/update/{id}")
    public String update(@PathVariable(value = "id") int id, Model model){
        DossierMedicale dossierMedical = dossierMedicalService.getDossierById(id);
        model.addAttribute("dossierMedical", dossierMedical);
        return "dossierMedical/update";
    }

    @PostMapping("/dossier/update")
    public String updateDossier(@ModelAttribute DossierMedicale dossierMedicale){
        dossierMedicalService.updateDossier(dossierMedicale);
        return "redirect:/dossier/index";
    }

    @GetMapping("/dossier/delete/{id}")
    public String deleteDossier(@PathVariable(value = "id") int id){
        dossierMedicalService.deleteDossier(id);
        return "redirect:/dossier/index";
    }
}
