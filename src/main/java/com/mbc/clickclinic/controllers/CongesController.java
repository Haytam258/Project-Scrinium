package com.mbc.clickclinic.controllers;

import com.mbc.clickclinic.entities.Conges;
import com.mbc.clickclinic.entities.CustomUser;
import com.mbc.clickclinic.entities.Medecin;
import com.mbc.clickclinic.service.CongesService;
import com.mbc.clickclinic.service.MedecinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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


    @PreAuthorize("hasAuthority('MEDECIN')")
    @GetMapping("/conges/index")
    public String index(Model model){
        CustomUser customUser = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("mesConges", congesService.getCongesByMedecin(medecinService.medecinById(customUser.getId())));
        return "conges/index";
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/admin/conges/index")
    public String indexAdmin(Model model){
        List<Conges> congesList = congesService.getAllConges();
        model.addAttribute("congesList", congesList);
        return "conges/indexAdmin";
    }

    @PreAuthorize("hasAuthority('MEDECIN')")
    @GetMapping("/conges/create")
    public String create(Model model){
        Conges conges = new Conges();
        model.addAttribute("conges", conges);
        CustomUser customUser = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("medecin", medecinService.medecinById(customUser.getId()));
        return "conges/create";
    }

    @PreAuthorize("hasAuthority('MEDECIN')")
    @PostMapping(path = "/conges/save")
    public String saveConges(@ModelAttribute("conges")Conges conges) {
        CustomUser customUser = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Medecin medecin = medecinService.medecinById(customUser.getId());
        conges.setMedecin(medecin);
        congesService.createConges(conges, conges.getMedecin());
        return "redirect:/admin/conges/index";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/conges/accept/{id}")
    public String acceptConges(@PathVariable(value = "id") int id){
        Conges conges = congesService.acceptConges(id);
        return "redirect:/admin/conges/index";
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','MEDECIN')")
    @GetMapping("/conges/refuse/{id}")
    public String refuseConges(@PathVariable(value = "id") int id){
        congesService.refuseConges(id);
        return "redirect:/admin/conges/index";
    }
}
