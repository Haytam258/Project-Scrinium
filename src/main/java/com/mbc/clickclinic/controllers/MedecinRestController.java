package com.mbc.clickclinic.controllers;


import com.mbc.clickclinic.entities.CustomUser;
import com.mbc.clickclinic.entities.Medecin;
import com.mbc.clickclinic.service.AdminService;
import com.mbc.clickclinic.service.MedecinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Controller
public class MedecinRestController {

    private final MedecinService medecinService;
    private final AdminService adminService;

    @Autowired
    public MedecinRestController(MedecinService medecinService, @Lazy AdminService adminService){
        this.medecinService = medecinService;
        this.adminService = adminService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/createMedecin")
    public String createMedecin(Model model){
        model.addAttribute("medecin", new Medecin());
        return "medecin/createMedecin";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/createMedecin")
    public String createMedecin(@ModelAttribute Medecin medecin, Model model){
        if(!(medecin.getMobil().matches("[0][6][0-9]{8}"))){
            model.addAttribute("telInvalid", "Numéro de téléphone invalide !");
        }
        else {
            if(medecinService.saveMedecin(medecin,model) != null){
                model.addAttribute("medecinSuccess","Médecin créé avec succès !");
            }
            else {
                model.addAttribute("medecinFail","Veuillez vérifier les informations saisies !");
            }
        }
        model.addAttribute("medecin", medecin);
        return "medecin/createMedecin";
    }

    @PreAuthorize("hasAuthority('MEDECIN')")
    @GetMapping("/myRevenu")
    public String getMyRevenu(Model model){
        CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Medecin medecin = medecinService.medecinById(user.getId());
        model.addAttribute("myRevenuData", adminService.getPaymentPerMonthByMedecin(medecinService.medecinById(medecin.getId())));
        model.addAttribute("thisYear", LocalDate.now().getYear());
        model.addAttribute("thisYearPayment", adminService.getThisYearPaymentByMedecin(medecinService.medecinById(medecin.getId())));
        model.addAttribute("thisMonthPayment", adminService.getThisMonthPaymentByMedecin(medecinService.medecinById(medecin.getId())));
        return "medecin/myRevenu";
    }

    @PreAuthorize("hasAuthority('MEDECIN')")
    @GetMapping("/myMedecinProfil")
    public String myMedecinProfil(Model model){
        CustomUser customUser = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("myInfo", medecinService.medecinById(customUser.getId()));
        return "medecin/myMedecinProfil";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/deleteMedecin/{id}")
    public String deleteMedecin(@PathVariable Integer id){
        medecinService.deleteMedecin(medecinService.medecinById(id));
        return "redirect:/medecins";
    }

    @GetMapping("/medecins/{id}")
    public String getMedecin(@PathVariable Integer id, Model model){
        model.addAttribute("medecin", medecinService.medecinById(id));
        return "medecin/medecinProfile";
    }

    @PreAuthorize("hasAnyAuthority('SECRETAIRE','ADMIN')")
    @GetMapping("/medecins")
    public String getMedecins(Model model){
        model.addAttribute("allMedecins",medecinService.medecins());
        return "medecin/medecinList";
    }

}
