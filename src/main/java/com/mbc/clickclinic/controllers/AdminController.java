package com.mbc.clickclinic.controllers;


import com.mbc.clickclinic.dao.MedecinRepository;
import com.mbc.clickclinic.entities.CustomUser;
import com.mbc.clickclinic.entities.Medecin;
import com.mbc.clickclinic.entities.Personne;
import com.mbc.clickclinic.service.AdminService;
import com.mbc.clickclinic.service.MedecinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@Controller
public class AdminController {

    private AdminService adminService;
    private final MedecinRepository medecinService;

    @Autowired
    public AdminController(AdminService adminService, MedecinRepository medecinService){
        this.medecinService = medecinService;
        this.adminService = adminService;
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/adminDashboard")
    public String dashboard(Model model){
        CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Medecin medecin = medecinService.findMedecinByEmail(user.getUsername());
        if(medecin != null){
            model.addAttribute("test", medecin);
        }
        model.addAttribute("paymentDataPerMonth",adminService.getPayementPerMonth().values());
        model.addAttribute("rendezvousPerMonth", adminService.getRendezvousCountByMonth());
        model.addAttribute("thisYearRevenue",adminService.getThisYearTotalPayment());
        model.addAttribute("thisYear", LocalDate.now().getYear());
        model.addAttribute("patientNumber", adminService.getPatientCount());
        model.addAttribute("patientByGender", adminService.patientCountByGender());
        model.addAttribute("genderPercent", adminService.patientGenderPercent());
        model.addAttribute("remainingMontant", adminService.getRemainingPayment());
        model.addAttribute("remainingThisYear", adminService.getThisYearRemainingPayment());
        return "admin/adminDashboard";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/createAdmin")
    public String createAdmin(Model model){
        model.addAttribute("adminPerson", new Personne());
        return "admin/createAdmin";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/saveAdmin")
    public String createAdmin(@ModelAttribute("adminPerson") Personne admin, Model model){
        if(adminService.createAdmin(admin,model) == null){
            model.addAttribute("createFailed","Création admin échoué !");
        }
        else {
            model.addAttribute("createSuccess", "Admin créé avec succès !");
        }
        return "redirect:/createAdmin";
    }


    @GetMapping("/forbidden")
    public String forbiddenPage(Model model){
        return "forbidden";
    }
}
