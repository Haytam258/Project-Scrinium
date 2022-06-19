package com.mbc.clickclinic.controllers;

import com.mbc.clickclinic.entities.*;
import com.mbc.clickclinic.service.ConsultationService;
import com.mbc.clickclinic.service.DossierMedicalService;
import com.mbc.clickclinic.service.MedecinService;
import com.mbc.clickclinic.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @Autowired
    private ConsultationService consultationService;

    @PreAuthorize("hasAuthority('MEDECIN')")
    @GetMapping("/dossier/index")
    public String index(Model model){
        //fixed idMedecin here til we get it with spring security
        //Need to remember that this only gets DossierMedicals of patients that have a rendez vous, not all Dossiers.
        CustomUser customUser = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Medecin medecin = medecinService.medecinById(customUser.getId());
        List<DossierMedicale> dossiers = dossierMedicalService.getAllDossiersByMedecin(medecin);
        model.addAttribute("dossiers", dossiers);
        return "dossierMedical/indexMedecin";
    }

    @PreAuthorize("hasAuthority('MEDECIN')")
    @GetMapping("/dossier/create")
    public String createDossier(Model model){
        DossierMedicale dossierMedical = new DossierMedicale();
        List<Patient> patients = patientService.patients();
        model.addAttribute("dossierMedical", dossierMedical);
        model.addAttribute("patients", patients);
        return "dossierMedical/create";
    }

    //Ici, on aura l'id du médecin connecté qui a créé le dossier médicale
    @PreAuthorize("hasAuthority('MEDECIN')")
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

    @PreAuthorize("hasAuthority('MEDECIN')")
    @GetMapping("/dossier/update/{id}")
    public String update(@PathVariable(value = "id") int id, Model model){
        DossierMedicale dossierMedical = dossierMedicalService.getDossierById(id);
        Patient patient = dossierMedical.getPatient();
        model.addAttribute("dossierMedical", dossierMedical);
        model.addAttribute("patient", patient);
        return "dossierMedical/update";
    }

    @PreAuthorize("hasAuthority('MEDECIN')")
    @PostMapping("/dossier/update")
    public String updateDossier(@ModelAttribute DossierMedicale dossierMedicale){
        dossierMedicalService.updateDossier(dossierMedicale);
        return "redirect:/dossier/index";
    }

    @PreAuthorize("hasAuthority('MEDECIN')")
    @GetMapping("/dossier/delete/{id}")
    public String deleteDossier(@PathVariable(value = "id") Integer id){
        dossierMedicalService.deleteDossier(dossierMedicalService.getDossierById(id));
        return "redirect:/dossier/index";
    }

    @PreAuthorize("hasAnyAuthority('MEDECIN','PATIENT','ADMIN')")
    @GetMapping("/dossier/show/{id}")
    public String showDossier(@PathVariable(value = "id") int id, Model model){
        DossierMedicale dossier= dossierMedicalService.getDossierById(id);
        List<Consultation> consultations = consultationService.getAllConsultationsByDossierMedicale(dossierMedicalService.getDossierById(id));
        model.addAttribute("dossier", dossier);
        model.addAttribute("consultations", consultations);
        return "dossierMedical/show";
    }
}
