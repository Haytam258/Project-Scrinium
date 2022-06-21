package com.mbc.clickclinic.controllers;


import com.mbc.clickclinic.dao.AnnonceRepository;
import com.mbc.clickclinic.entities.Annonce;
import com.mbc.clickclinic.service.AnnonceService;
import com.mbc.clickclinic.service.EmailService;
import com.mbc.clickclinic.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class AnnonceRestController {

    private final AnnonceService annonceService;
    private final EmailService emailService;
    private final PatientService patientService;

    @Autowired
    public AnnonceRestController(AnnonceService annonceService, EmailService emailService, PatientService patientService){
        this.emailService = emailService;
        this.annonceService = annonceService;
        this.patientService = patientService;
    }

    @PreAuthorize("hasAuthority('SECRETAIRE')")
    @PostMapping("/createAnnonce")
    public String createAnnonce(@ModelAttribute(value = "newAnnonce") Annonce annonce, Model model){
        annonce.setDateCreation(LocalDate.now());
        if(annonceService.saveNotification(annonce) != null){
            model.addAttribute("annonceCreated", "Annonce créée avec succès !");
            //WAY TOO SLOW !!
            /*for(String email : patientService.patientsEmail()){
                emailService.sendSimpleMessage(email,annonce.getMessage(),annonce.getObjet());
            }*/
            //Apparently, there's no way to do this other than Message.RecipientType.BCC with MimeMessage but even that has a limit, so it'll still disclose the other emails .... meaning there's no way to
            //hide the recipients of the message. The first method took more 1 min 20 seconds with only 8 emails... it's too time consuming.
            //This apparently locked the gmail account ...
            emailService.sendToAll(patientService.patientsEmail(),annonce.getMessage(),annonce.getObjet());
        }
        else {
            model.addAttribute("annonceFail", "Veuillez vérifier les informations saisies et respectez la longeur du message et objet (255 caractères)");
        }
        return "annonce/createAnnonce";
    }

    @PreAuthorize("hasAuthority('SECRETAIRE')")
    @GetMapping("/createAnnonce")
    public String createAnnonce(Model model){
        model.addAttribute("newAnnonce", new Annonce());
        return "annonce/createAnnonce";
    }

    @PreAuthorize("hasAuthority('SECRETAIRE')")
    @GetMapping("/annonces")
    public String getAnnonces(Model model){
        model.addAttribute("annonceList",annonceService.getAllAnnonce());
        return "annonce/annonceList";
    }

    @PreAuthorize("hasAuthority('SECRETAIRE')")
    @GetMapping("/deleteAnnonce/{id}")
    public String deleteAnnonce(@PathVariable Integer id){
        annonceService.deleteNotification(annonceService.getAnnonce(id));
        return "redirect:/annonces";
    }
}
