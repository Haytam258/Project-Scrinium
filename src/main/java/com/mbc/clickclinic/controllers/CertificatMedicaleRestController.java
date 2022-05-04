package com.mbc.clickclinic.controllers;


import com.mbc.clickclinic.entities.CertificatMedicale;
import com.mbc.clickclinic.entities.DemandeCertificat;
import com.mbc.clickclinic.entities.TypeCertification;
import com.mbc.clickclinic.service.CertificatMedicaleService;
import com.mbc.clickclinic.service.DemandeCertificatService;
import com.mbc.clickclinic.service.MedecinService;
import com.mbc.clickclinic.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CertificatMedicaleRestController {

    private final CertificatMedicaleService certificatMedicaleService;
    private final MedecinService medecinService;
    private final DemandeCertificatService demandeCertificatService;
    private final PatientService patientService;

    @Autowired
    public CertificatMedicaleRestController(CertificatMedicaleService certificatMedicaleService, MedecinService medecinService, DemandeCertificatService demandeCertificatService,@Lazy PatientService patientService){
        this.certificatMedicaleService = certificatMedicaleService;
        this.medecinService = medecinService;
        this.demandeCertificatService = demandeCertificatService;
        this.patientService = patientService;
    }

    @GetMapping("/createCertificat")
    public String createCertificat(){
        return "redirect:/demandesCertificat";
    }

    @PostMapping("/createCertificat")
    public String createCertificat(@ModelAttribute(value = "certificatMedical") CertificatMedicale certificatMedicale, Model model){
        model.addAttribute("allDemandes", demandeCertificatService.getDemandesCertificats());
        model.addAttribute("typeCertList", demandeCertificatService.getTypesCertificat());
        model.addAttribute("patientList", patientService.patients());
        model.addAttribute("medecinList", medecinService.medecins());
       DemandeCertificat demandeCertificat = demandeCertificatService.getDemandeByPatientAndMedecin(certificatMedicale.getPatient(), certificatMedicale.getMedecin());
       if(demandeCertificat != null){
           if(certificatMedicale != null){
               model.addAttribute("certificatSuccess", "certificat créé avec succès !");
           }
           else {
               model.addAttribute("certificatFail", "Création échouée, assurez vous des données !");
           }
           demandeCertificat.setCertificatMedicale(certificatMedicale);
           demandeCertificat.setStatus(1);
           certificatMedicale.setDemandeCertificat(demandeCertificat);
           certificatMedicaleService.saveCertificatMedical(certificatMedicale);
           demandeCertificatService.saveDemandeCertificat(demandeCertificat);
       }
       else {
           model.addAttribute("demandeNotThere", "Ce patient n'a pas fait de demande ");
       }
       return "certificatMedicale/demandesCertificat";
    }

    @GetMapping("/createDemandeCertificat")
    public String createDemandeCertificat(Model model){
        model.addAttribute("demandeCertificatMedical", new DemandeCertificat());
        model.addAttribute("medecinListC", medecinService.medecins());
        model.addAttribute("typeCertList", demandeCertificatService.getTypesCertificat());
        model.addAttribute("patientList", patientService.patients());
        return "certificatMedicale/demanderCertificat";
    }

    @PostMapping("/createDemandeCertificat")
    public String createDemandeCertificat(@ModelAttribute(value = "demandeCertificatMedical") DemandeCertificat demandeCertificat, Model model){
        demandeCertificat.setStatus(0);
        DemandeCertificat demande = demandeCertificatService.saveDemandeCertificat(demandeCertificat);
        if(demande != null){
            model.addAttribute("demandeSuccess", "Demande effectuée avec succès !");
        }
        else {
            model.addAttribute("demandeFail", "Création échouée, assurez vous que les données de la demande sont correctes !");
        }
        return "certificatMedicale/demanderCertificat";
    }

    @GetMapping("/createTypeCertificat")
    public String createTypeCertificat(Model model){
        model.addAttribute("typeCertificat", new TypeCertification());
        return "certificatMedicale/createTypeCertificat";
    }

    @PostMapping("/createTypeCertificat")
    public String createTypeCertificat(@ModelAttribute(value = "typeCertificat") TypeCertification typeCertification, Model model){
        TypeCertification type = demandeCertificatService.saveTypeCertification(typeCertification);
        if(type != null){
            model.addAttribute("typeSuccess", "Type de certification créé avec succès !");
        }
        else {
            model.addAttribute("typeFail", "Création échouée, assurez vous des données rentrées !");
        }
        return "certificatMedicale/createTypeCertificat";

    }

    @GetMapping("/demandesCertificat")
    public String getDemandesCertificat(Model model){
        model.addAttribute("allDemandes", demandeCertificatService.getDemandesCertificats());
        model.addAttribute("certificatMedical", new CertificatMedicale());
        model.addAttribute("typeCertList", demandeCertificatService.getTypesCertificat());
        model.addAttribute("patientList", patientService.patients());
        model.addAttribute("medecinList", medecinService.medecins());
        return "certificatMedicale/demandesCertificat";
    }

    @GetMapping("/certificats/{id}")
    public CertificatMedicale getCertificat(@PathVariable Long id){
        return certificatMedicaleService.CertificatMedicalById(id.intValue());
    }

    @PostMapping("/deleteCertificat/{id}")
    public void deleteCertificat(@PathVariable Long id){
        certificatMedicaleService.deleteCertificatMedical(certificatMedicaleService.CertificatMedicalById(id.intValue()));
    }

    @PostMapping("/updateCertificat")
    public CertificatMedicale updateCertificat(@RequestBody CertificatMedicale certificatMedicale){
        return certificatMedicaleService.updateCertificatMedical(certificatMedicale);
    }
}
