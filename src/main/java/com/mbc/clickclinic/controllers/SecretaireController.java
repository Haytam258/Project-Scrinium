package com.mbc.clickclinic.controllers;


import com.mbc.clickclinic.entities.CustomUser;
import com.mbc.clickclinic.entities.Secretaire;
import com.mbc.clickclinic.service.SecretaireService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SecretaireController {

    private final SecretaireService secretaireService;

    @Autowired
    public SecretaireController(SecretaireService secretaireService){
        this.secretaireService = secretaireService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/createSecretaire")
    public String createSecretaire(Model model){
        model.addAttribute("newSecretaire", new Secretaire());
        return "secretaire/createSecretaire";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/createSecretaire")
    public String createSecretaire(@ModelAttribute(value = "newSecretaire") Secretaire secretaire, Model model){
        if(!(secretaire.getMobil().matches("[0][6][0-9]{8}"))){
            model.addAttribute("telInvalid", "Numéro de téléphone invalide !");
        }
        else {
            if(secretaireService.saveSecretaire(secretaire, model) != null){
                model.addAttribute("secretaireSuccess", "Secrétaire créée avec succès !");
            }
            else {
                model.addAttribute("secretaireFail", "Veuillez vérifier les informations saisies, il se peut que l'émail est déjà utilisé !");
            }
        }
        return "secretaire/createSecretaire";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/secretaires")
    public String allSecretaires(Model model){
        model.addAttribute("allSecretaires", secretaireService.Secretaires());
        return "secretaire/secretaireList";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/deleteSecretaire/{id}")
    public String deleteSecretaire(@PathVariable Integer id){
        secretaireService.deleteSecretaire(secretaireService.SecretaireById(id));
        return "redirect:/secretaires";
    }

    @PreAuthorize("hasAuthority('SECRETAIRE')")
    @GetMapping("/mySecretaireProfil")
    public String mySecretaireProfil(Model model){
        CustomUser customUser = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("myInfo", secretaireService.SecretaireById(customUser.getId()));
        return "secretaire/mySecretaireProfile";
    }

}
