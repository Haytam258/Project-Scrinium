package com.mbc.clickclinic.controllers;

import com.mbc.clickclinic.entities.Conges;
import com.mbc.clickclinic.service.CongesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CongesController {

    @Autowired
    private CongesService congesService;

    @GetMapping("/conges/index")
    public String index(){
        return "conges/index";
    }

    @GetMapping("/admin/conges/index")
    public String indexAdmin(Model model){
        List<Conges> congesList = congesService.getAllConges();
        model.addAttribute("congesList", congesList);
        return "conges/indexAdmin";
    }

    @GetMapping("/conges/create")
    public String create(Model model){
        Conges conges = new Conges();
        model.addAttribute("conges", conges);
        return "conges/create";
    }

    @PostMapping(path = "/conges/save")
    public String createConges(@ModelAttribute("conges")Conges conges, @RequestParam("nom") String nom) {
        congesService.createConges(conges, nom);
        return "redirect:/conges/index";
    }
}
