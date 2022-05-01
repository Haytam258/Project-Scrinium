package com.mbc.clickclinic.controllers;

import com.mbc.clickclinic.entities.Agenda;
import com.mbc.clickclinic.entities.Conges;
import com.mbc.clickclinic.service.CongesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class CongesController {

    @Autowired
    private CongesService congesService;

    @GetMapping("/conges/index")
    public String index(){
        return "conges/index";
    }

    @GetMapping("/conges/create")
    public String create(Model model){
        Conges conges = new Conges();
        model.addAttribute("conges", conges);
        return "conges/create";
    }

    @PostMapping("/conges/save")
    public String createConges(@RequestBody Conges conges) {
        congesService.createConges(conges);
        return "redirect:/conges/index";
    }
}
