package com.mbc.clickclinic.controllers;


import com.mbc.clickclinic.entities.Personne;
import com.mbc.clickclinic.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminController {

    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    @GetMapping("/adminDashboard")
    public String dashboard(Model model){
        model.addAttribute("paymentDataPerMonth",adminService.getPayementPerMonth().values());
        model.addAttribute("rendezvousPerMonth", adminService.getRendezvousCountByMonth());
        return "admin/adminDashboard";
    }

    @GetMapping("/createAdmin")
    public String createAdmin(Model model){
        model.addAttribute("adminPerson", new Personne());
        return "admin/createAdmin";
    }

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
}
