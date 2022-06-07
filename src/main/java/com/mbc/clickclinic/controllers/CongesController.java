package com.mbc.clickclinic.controllers;

import com.mbc.clickclinic.entities.Conges;
import com.mbc.clickclinic.service.CongesService;
import com.mbc.clickclinic.service.MedecinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class CongesController {


    private CongesService congesService;
    private MedecinService medecinService;

    @Autowired
    public CongesController(MedecinService medecinService, CongesService congesService){
        this.medecinService = medecinService;
        this.congesService = congesService;
    }


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
        model.addAttribute("medecins", medecinService.medecins());
        return "conges/create";
    }

    @PostMapping(path = "/conges/save")
    public String saveConges(@ModelAttribute("conges")Conges conges) {
        congesService.createConges(conges, conges.getMedecin());
        return "redirect:/admin/conges/index";
    }

    @GetMapping("/conges/accept/{id}")
    public String acceptConges(@PathVariable(value = "id") int id){
        Conges conges = congesService.acceptConges(id);
        return "redirect:/admin/conges/index";
    }

    @GetMapping("/conges/refuse/{id}")
    public String refuseConges(@PathVariable(value = "id") int id){
        congesService.refuseConges(id);
        return "redirect:/admin/conges/index";
    }
}
