package com.mbc.clickclinic.controllers;


import com.mbc.clickclinic.entities.Secretaire;
import com.mbc.clickclinic.service.SecretaireService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/createSecretaire")
    public String createSecretaire(Model model){
        model.addAttribute("newSecretaire", new Secretaire());
        return "secretaire/createSecretaire";
    }

    @PostMapping("/createSecretaire")
    public String createSecretaire(@ModelAttribute(value = "newSecretaire") Secretaire secretaire, Model model){
        if(secretaireService.saveSecretaire(secretaire, model) != null){
            model.addAttribute("secretaireSuccess", "Secrétaire créée avec succès !");
        }
        else {
            model.addAttribute("secretaireFail", "Veuillez vérifier les informations saisies !");
        }
        return "secretaire/createSecretaire";
    }

    @GetMapping("/secretaires")
    public String allSecretaires(Model model){
        model.addAttribute("allSecretaires", secretaireService.Secretaires());
        return "secretaire/secretaireList";
    }

    @GetMapping("/deleteSecretaire/{id}")
    public String deleteSecretaire(@PathVariable Integer id){
        secretaireService.deleteSecretaire(secretaireService.SecretaireById(id));
        return "redirect:/secretaires";
    }

}
