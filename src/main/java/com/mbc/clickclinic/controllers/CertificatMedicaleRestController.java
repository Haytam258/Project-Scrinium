package com.mbc.clickclinic.controllers;


import com.mbc.clickclinic.entities.*;
import com.mbc.clickclinic.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class CertificatMedicaleRestController {

    private final CertificatMedicaleService certificatMedicaleService;
    private final MedecinService medecinService;
    private final DemandeCertificatService demandeCertificatService;
    private final PatientService patientService;
    private final AnnonceService annonceService;

    @Autowired
    public CertificatMedicaleRestController(CertificatMedicaleService certificatMedicaleService, MedecinService medecinService, DemandeCertificatService demandeCertificatService,@Lazy PatientService patientService, @Lazy AnnonceService annonceService){
        this.certificatMedicaleService = certificatMedicaleService;
        this.medecinService = medecinService;
        this.demandeCertificatService = demandeCertificatService;
        this.patientService = patientService;
        this.annonceService = annonceService;
    }

    @PreAuthorize("hasAuthority('MEDECIN')")
    @GetMapping("/createCertificat")
    public String createCertificat(){
        return "redirect:/demandesCertificat";
    }

    @PreAuthorize("hasAuthority('MEDECIN')")
    @GetMapping("/deleteDemande/{id}")
    public String deleteDemande(@PathVariable(value = "id")Integer id, Model model){
        Annonce annonce = new Annonce();
        DemandeCertificat demandeCertificat = demandeCertificatService.getDemandeCertificatById(id);
        annonce.setPatient(demandeCertificat.getPatient());
        annonce.setObjet("Votre certificat a été refusé ");
        annonce.setMessage("Le certificat demandé au médecin " + demandeCertificat.getMedecin().getNom() + " a été refusé ");
        annonce.setDateCreation(LocalDate.now());
        annonceService.saveNotification(annonce);
        demandeCertificatService.deleteDemande(demandeCertificat);
        return "redirect:/createCertificat";
    }

    @PreAuthorize("hasAuthority('PATIENT')")
    @GetMapping("/maDemandeCertificat")
    public String maDemandeCertificat(Model model){
        CustomUser customUser = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Patient patient = patientService.PatientById(customUser.getId());
        model.addAttribute("maDemande", demandeCertificatService.getDemandeByPatientAndStatus(patient,0));
        return "certificatMedicale/maDemandeCertificat";
    }

    @PreAuthorize("hasAuthority('PATIENT')")
    @GetMapping("/deleteMyDemande/{id}")
    public String deleteMyDemande(@PathVariable(value = "id") Integer id, Model model){
        demandeCertificatService.deleteDemande(demandeCertificatService.getDemandeCertificatById(id));
        return "redirect:/maDemandeCertificat";
    }

    @PreAuthorize("hasAuthority('MEDECIN')")
    @PostMapping("/createCertificat")
    public String createCertificat(@ModelAttribute(value = "certificatMedical") CertificatMedicale certificatMedicale, Model model){
        CustomUser customUser = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Medecin medecin = medecinService.medecinById(customUser.getId());
        model.addAttribute("allDemandes", demandeCertificatService.getDemandesByMedecin(medecin));
        model.addAttribute("typeCertList", demandeCertificatService.getTypesCertificat());
        model.addAttribute("patientList", demandeCertificatService.getPatientsWithDemandeForMedecin(medecin));
        model.addAttribute("medecin", medecin);
        certificatMedicale.setMedecin(medecin);
       DemandeCertificat demandeCertificat = demandeCertificatService.getDemandeByPatientAndMedecinAndStatus(certificatMedicale.getPatient(), certificatMedicale.getMedecin(),0);
       if(demandeCertificat != null){
           if(certificatMedicale != null){
               model.addAttribute("certificatSuccess", "certificat créé avec succès !");
               Annonce annonce = new Annonce();
               annonce.setPatient(certificatMedicale.getPatient());
               annonce.setObjet("Certificat créé");
               annonce.setMessage("Votre certificat de type " + certificatMedicale.getTypeCertification().getType() + " demandé du médecin " + certificatMedicale.getMedecin().getNom() + " a été créé");
               annonce.setDateCreation(LocalDate.now());
               annonceService.saveNotification(annonce);
           }
           else {
               model.addAttribute("certificatFail", "Création échouée, assurez vous des données !");
           }
           demandeCertificat.getPatient().setDemandeCertificat(null);
           certificatMedicale.setDateCreation(LocalDate.now());
           certificatMedicaleService.saveCertificatMedical(certificatMedicale);
           patientService.savePatient(demandeCertificat.getPatient());
           demandeCertificat.setTypeCertification(null);
           demandeCertificatService.deleteDemande(demandeCertificat);
       }
       else {
           model.addAttribute("demandeNotThere", "Ce patient n'a pas fait de demande ");
       }
       return "certificatMedicale/demandesCertificat";
    }

    @PreAuthorize("hasAuthority('PATIENT')")
    @GetMapping("/createDemandeCertificat")
    public String createDemandeCertificat(Model model){
        model.addAttribute("demandeCertificatMedical", new DemandeCertificat());
        model.addAttribute("medecinListC", medecinService.medecins());
        model.addAttribute("typeCertList", demandeCertificatService.getTypesCertificat());
        CustomUser customUser = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("patient", patientService.PatientById(customUser.getId()));
        return "certificatMedicale/demanderCertificat";
    }

    @PreAuthorize("hasAuthority('PATIENT')")
    @PostMapping("/createDemandeCertificat")
    public String createDemandeCertificat(@ModelAttribute(value = "demandeCertificatMedical") DemandeCertificat demandeCertificat, Model model){
        CustomUser customUser = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Patient patient = patientService.PatientById(customUser.getId());
        model.addAttribute("patient", patient);
        if(patient.getDemandeCertificat() == null){
            demandeCertificat.setStatus(0);
            demandeCertificat.setPatient(patient);
            patientService.PatientById(customUser.getId()).setDemandeCertificat(demandeCertificat);
            DemandeCertificat demande = demandeCertificatService.saveDemandeCertificat(demandeCertificat);
            patientService.savePatient(patient);
            if(demande != null){
                model.addAttribute("demandeSuccess", "Demande effectuée avec succès !");
            }
            else {
                model.addAttribute("demandeFail", "Création échouée, assurez vous que les données de la demande sont correctes !");
            }
        }
        else {
            model.addAttribute("demandeFail", "Vous n'avez droit qu'à une seule demande de certificat ! Si votre demande est acceptée par le médecin, vous le verez dans vos certificats, sinon veuillez attendre sa réponse");
        }
        return "certificatMedicale/demanderCertificat";
    }

    @PreAuthorize("hasAnyAuthority('MEDECIN', 'ADMIN')")
    @GetMapping("/createTypeCertificat")
    public String createTypeCertificat(Model model){
        model.addAttribute("typeCertificat", new TypeCertification());
        return "certificatMedicale/createTypeCertificat";
    }

    @PreAuthorize("hasAnyAuthority('MEDECIN', 'ADMIN')")
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

    @PreAuthorize("hasAuthority('MEDECIN')")
    @GetMapping("/demandesCertificat")
    public String getDemandesCertificat(Model model){
        CustomUser customUser = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Medecin medecin = medecinService.medecinById(customUser.getId());
        model.addAttribute("allDemandes", demandeCertificatService.getDemandesByMedecin(medecin));
        model.addAttribute("certificatMedical", new CertificatMedicale());
        model.addAttribute("typeCertList", demandeCertificatService.getTypesCertificat());
        model.addAttribute("patientList", demandeCertificatService.getPatientsWithDemandeForMedecin(medecin));
        model.addAttribute("medecin", medecin);
        return "certificatMedicale/demandesCertificat";
    }


    @PreAuthorize("hasAnyAuthority('MEDECIN', 'ADMIN')")
    @PostMapping("/deleteCertificat/{id}")
    public String deleteCertificat(@PathVariable Integer id){
        certificatMedicaleService.deleteCertificatMedical(certificatMedicaleService.CertificatMedicalById(id));
        return "redirect:/certificats";
    }


    @PreAuthorize("hasAnyAuthority('ADMIN','MEDECIN')")
    @GetMapping("/certificats")
    public String getCertificats(Model model){
        model.addAttribute("allCertificats", certificatMedicaleService.CertificatMedicals());
        return "certificatMedicale/certificatList";
    }

    @PreAuthorize("hasAuthority('PATIENT')")
    @GetMapping("/myCertificats")
    public String myCertificats(Model model){
        CustomUser customUser = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("allMyCertificats", certificatMedicaleService.getCertificatMedicalesOfPatient(patientService.PatientById(customUser.getId())));
        return "certificatMedicale/mesCertificats";
    }
}
