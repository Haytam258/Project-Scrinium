package com.mbc.clickclinic.controllers;


import com.mbc.clickclinic.entities.Medecin;
import com.mbc.clickclinic.service.AdminService;
import com.mbc.clickclinic.service.MedecinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
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

    @GetMapping("/createMedecin")
    public String createMedecin(Model model){
        model.addAttribute("medecin", new Medecin());
        return "medecin/createMedecin";
    }

    @PostMapping("/createMedecin")
    public String createMedecin(@ModelAttribute Medecin medecin, Model model){
        if(medecinService.saveMedecin(medecin,model) != null){
            model.addAttribute("medecinSuccess","Médecin créé avec succès !");
        }
        else {
            model.addAttribute("medecinFail","Veuillez vérifier les informations saisies !");
        }
        model.addAttribute("medecin", medecin);
        return "medecin/createMedecin";
    }

    @GetMapping("/myRevenu")
    public String getMyRevenu(Model model){
        model.addAttribute("myRevenuData", adminService.getPaymentPerMonthByMedecin(medecinService.medecinById(1)));
        model.addAttribute("thisYear", LocalDate.now().getYear());
        model.addAttribute("thisYearPayment", adminService.getThisYearPaymentByMedecin(medecinService.medecinById(1)));
        model.addAttribute("thisMonthPayment", adminService.getThisMonthPaymentByMedecin(medecinService.medecinById(1)));
        return "medecin/myRevenu";
    }

    @PostMapping("/modifyMedecin")
    public Medecin modifyMedecin(@RequestBody Medecin medecin){
        return medecinService.updateMedecin(medecin);
    }

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

    @GetMapping("/medecins")
    public String getMedecins(Model model){
        model.addAttribute("allMedecins",medecinService.medecins());
        return "medecin/medecinList";
    }

}
