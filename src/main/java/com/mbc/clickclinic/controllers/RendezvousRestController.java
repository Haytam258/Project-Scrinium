package com.mbc.clickclinic.controllers;


import com.mbc.clickclinic.entities.Annonce;
import com.mbc.clickclinic.entities.CustomUser;
import com.mbc.clickclinic.entities.Patient;
import com.mbc.clickclinic.entities.Rendezvous;
import com.mbc.clickclinic.service.*;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import java.time.LocalDate;
import java.util.List;

@Controller
//@RestController
public class RendezvousRestController {

    private final RendezvousService rendezvousService;
    private final MedecinService medecinService;
    private final PatientService patientService;
    private final ConsultationService consultationService;
    private final EmailService emailService;
    private final AnnonceService annonceService;

    @Autowired
    public RendezvousRestController(RendezvousService rendezvousService, PatientService patientService, MedecinService medecinService, ConsultationService consultationService, EmailService emailService, @Lazy AnnonceService annonceService){
        this.rendezvousService = rendezvousService;
        this.patientService = patientService;
        this.medecinService = medecinService;
        this.consultationService = consultationService;
        this.emailService = emailService;
        this.annonceService = annonceService;
    }

    @PreAuthorize("hasAnyAuthority('SECRETAIRE','MEDECIN','ADMIN')")
    @GetMapping("/allRendezvous")
    public String getAllRendezvous(Model model){
        model.addAttribute("rendezvousList", rendezvousService.Rendezvouss());
        return "rendezvous/rendezvousList";
    }

    @PreAuthorize("hasAuthority('PATIENT')")
    @GetMapping("/myRendezvous")
    public String myRendezvous(Model model){
        CustomUser customUser = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("rendezvousList", rendezvousService.getRendezvousByPatient(patientService.PatientById(customUser.getId())));
        return "rendezvous/mesRendezvous";
    }

    @PreAuthorize("hasAuthority('PATIENT')")
    @GetMapping("/myDemandesRendezvous")
    public String mesDemandesRendezvous(Model model){
        CustomUser customUser = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("mydemandes", rendezvousService.getDemandesRendezByPatient(patientService.PatientById(customUser.getId())));
        return "rendezvous/mesDemandesRendezvous";
    }

    @PreAuthorize("hasAuthority('PATIENT')")
    @GetMapping("/historiqueRendezVous")
    public String historiqueRendezvous(Model model){
        CustomUser customUser = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Patient patient = patientService.PatientById(customUser.getId());
        List<Rendezvous> rendezvous = rendezvousService.getRendezvousHistoriqueOfPatient(patient);
        model.addAttribute("rendezvousList", rendezvous);
        return "rendezvous/monHistorique";
    }

    @PreAuthorize("hasAuthority('SECRETAIRE')")
    @GetMapping("/modifyRendezvous/{id}")
    public String modifyRendezvous(@PathVariable(value = "id") Integer id, Model model){
        Rendezvous rendezvous = rendezvousService.RendezvousById(id);
        model.addAttribute("patient", rendezvous.getPatient());
        model.addAttribute("medecins", medecinService.getMedecinBySpecialty(rendezvous.getMedecin().getSpecialite()));
        model.addAttribute("rendezvous", rendezvous);
        return "rendezvous/modifyRendezvous";
    }

    @PreAuthorize("hasAuthority('SECRETAIRE')")
    @PostMapping("/modifyRendezvous")
    public String modifyRendezvous(@ModelAttribute("rendezvous")Rendezvous rendezvous, RedirectAttributes redirectAttributes){
        LocalDate localDateTime = LocalDate.parse(rendezvous.getDateRv().toString());
        rendezvous.setDateRv(localDateTime);
        Rendezvous rendezvous1 = rendezvousService.saveRendezvous(rendezvous, redirectAttributes);
        if(rendezvous1 != null){
            redirectAttributes.addFlashAttribute("rendezCreated", "rendez vous modifié avec succès !");
            String body = "Bonjour " + rendezvous1.getPatient().getNom() + "! \nVotre dernier rendez vous a été modifié et " +
                    "vous aurez un rendez vous le " + rendezvous1.getDateRv() + " à " + rendezvous1.getHeure() + " avec le médecin " + rendezvous1.getMedecin().getSpecialite() + " " +rendezvous1.getMedecin().getNom() +
                    "! \nLa clinique Scrinium vous souhaite une bonne continuation !\n-Scrinium";
            emailService.sendSimpleMessage(rendezvous1.getPatient().getEmail(), body , "Rendez vous modifié");
            return "redirect:/allRendezvous";
        }
        return "redirect:/modifyRendezvous/" + rendezvous.getId();
    }


    @PreAuthorize("hasAuthority('SECRETAIRE')")
   @GetMapping("/createRendezvous")
    public String createRendezPage(Model model){
        model.addAttribute("rendezvous", new Rendezvous());
        model.addAttribute("medecinList", medecinService.medecins());
        model.addAttribute("patientList", patientService.patients());
        return "rendezvous/createRendezvous";
    }
    //Fonctions pour tester les dates, il y a un problème au niveau du transfer html, il faudra séparer la date et heure.
    @PreAuthorize("hasAuthority('SECRETAIRE')")
    @PostMapping("/createRendezvous")
    public String createRendezvous(Model model, @ModelAttribute Rendezvous rendezvous){
        LocalDate localDateTime = LocalDate.parse(rendezvous.getDateRv().toString());
        rendezvous.setDateRv(localDateTime);
        Rendezvous rendezvous1 = rendezvousService.saveRendezvous(rendezvous, model);
        if(rendezvous1 != null){
            model.addAttribute("rendezCreated", "rendez vous créé avec succès !");
            String body = "Bonjour " + rendezvous1.getPatient().getNom() + "! \nVotre rendez vous a été décidé " +
                    "vous aurez un rendez vous le " + rendezvous1.getDateRv() + " à " + rendezvous1.getHeure() + " avec le médecin " + rendezvous1.getMedecin().getSpecialite() + " " +rendezvous1.getMedecin().getNom() +
                    "! \nLa clinique Scrinium vous souhaite une bonne continuation !\n-Scrinium";
            emailService.sendSimpleMessage(rendezvous1.getPatient().getEmail(),body, "Rendez vous créé");
        }
        model.addAttribute("medecinList", medecinService.medecins());
        model.addAttribute("patientList", patientService.patients());
        return "rendezvous/createRendezvous";
    }

    //Demander rendez vous pour les patients
    @PreAuthorize("hasAuthority('PATIENT')")
    @GetMapping("/demanderRendezvous")
    public String demandeRendezPage(Model model){
        model.addAttribute("rendezvous", new Rendezvous());
        CustomUser customUser = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("medecinList", medecinService.medecins());
        model.addAttribute("patient", patientService.PatientById(customUser.getId()));
        return "rendezvous/demanderRendezvous";
    }

    @PreAuthorize("hasAuthority('PATIENT')")
    @PostMapping("/demanderRendezvous")
    public String demandeRendezvous(Model model, @ModelAttribute Rendezvous rendezvous){
        LocalDate localDateTime = LocalDate.parse(rendezvous.getDateRv().toString());
        CustomUser customUser = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Patient patient = patientService.PatientById(customUser.getId());
        rendezvous.setPatient(patient);
        rendezvous.setDateRv(localDateTime);
        Rendezvous rendezvous1 = rendezvousService.saveRendezvous(rendezvous, model);
        if(rendezvous1 != null){
            model.addAttribute("rendezCreated", "demande rendez vous créé avec succès !");
            rendezvous.setStatut(2);
            rendezvousService.updateRendezvous(rendezvous);
        }
        model.addAttribute("medecinList", medecinService.medecins());
        model.addAttribute("patient", patient);
        return "rendezvous/demanderRendezvous";
    }

    @PreAuthorize("hasAuthority('SECRETAIRE')")
    @GetMapping("/demandesRendezvous")
    public String demandesRendevous(Model model){
        model.addAttribute("demandes", rendezvousService.rendezvousByStatut(2));
        return "rendezvous/demandesRendezvous";
    }

    @PreAuthorize("hasAuthority('SECRETAIRE')")
    @GetMapping("/demandesRendezvous/accept/{id}")
    public String demandeAccepte(Model model, @PathVariable("id") Integer id){
        Rendezvous rendezvous = rendezvousService.RendezvousById(id);
        Annonce annonce = new Annonce();
        annonce.setPatient(rendezvous.getPatient());
        annonce.setObjet("Demande rendez vous acceptée");
        annonce.setMessage("Rendez vous le : " + rendezvous.getDateRv() +" à " + rendezvous.getHeure());
        annonce.setDateCreation(LocalDate.now());
        annonceService.saveNotification(annonce);
        String body = "Bonjour " + rendezvous.getPatient().getNom() + "! \nVotre demande de rendez vous a été acceptée et " +
                "vous aurez un rendez vous le " + rendezvous.getDateRv() + " à " + rendezvous.getHeure() + " avec le médecin " + rendezvous.getMedecin().getSpecialite() + " " +rendezvous.getMedecin().getNom() +
                "! \nLa clinique Scrinium vous souhaite une bonne continuation !\n-Scrinium";
        emailService.sendSimpleMessage(rendezvous.getPatient().getEmail(),body,"Demande de rendez vous acceptée");
        rendezvous.setStatut(0);
        rendezvousService.updateRendezvous(rendezvous);
        return "redirect:/demandesRendezvous";
    }



    @PreAuthorize("hasAuthority('PATIENT')")
    @GetMapping("/deleteDemandeRendezvous/{id}")
    public String deleteDemande(@PathVariable(value = "id") Integer id){
        rendezvousService.deleteRendezvous(rendezvousService.RendezvousById(id));
        return "redirect:/myDemandesRendezvous";
    }

    @PreAuthorize("hasAnyAuthority('SECRETAIRE','MEDECIN')")
    @GetMapping("/deleteRendezvous/{id}")
    public String deleteRendezvous(@PathVariable Integer id){
        Rendezvous rendezvous = rendezvousService.RendezvousById(id);
        Annonce annonce = new Annonce();
        annonce.setPatient(rendezvous.getPatient());
        annonce.setObjet("Rendez vous demandé supprimé");
        annonce.setMessage("le rendez vous du  : " + rendezvous.getDateRv() + "a été refusé ou supprimé");
        annonce.setDateCreation(LocalDate.now());
        annonceService.saveNotification(annonce);
        String body = "Bonjour " + rendezvous.getPatient().getNom() + "! \nVotre rendez vous du " + rendezvous.getDateRv() + " à l'heure " +
                rendezvous.getHeure() + " avec le médecin "+ rendezvous.getMedecin().getNom() + " spécialité "+ rendezvous.getMedecin().getSpecialite()
                +" a été refusé / supprimé ! Il se peut qu'il y a un empêchement ce jour-ci, veuillez redemander un rendez vous pour un autre jour " +
                "! \nLa clinique Scrinium vous souhaite une bonne continuation !\n-Scrinium";
        emailService.sendSimpleMessage(rendezvous.getPatient().getEmail(),body,"Rendez vous refusé / supprimé");
        rendezvousService.deleteRendezvous(rendezvous);
        return "redirect:/allRendezvous";
    }

    @PreAuthorize("hasAuthority('PATIENT')")
    @GetMapping("/deleteMonRendezvous/{id}")
    public String deleteMonRendezvous(@PathVariable(value = "id") Integer id){
        rendezvousService.deleteRendezvous(rendezvousService.RendezvousById(id));
        return "redirect:/myRendezvous";
    }

}
