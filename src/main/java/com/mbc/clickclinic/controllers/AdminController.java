package com.mbc.clickclinic.controllers;


import com.mbc.clickclinic.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    private AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService){
        this.adminService = adminService;
    }

    @GetMapping("/adminDashboard")
    public String dashboard(Model model){
        System.out.println(adminService.getPayementPerMonth());

        model.addAttribute("paymentDataPerMonth",adminService.getPayementPerMonth().values());
        return "admin/adminDashboard";
    }
}
