package com.mbc.clickclinic.controllers;

import com.mbc.clickclinic.entities.Agenda;
import com.mbc.clickclinic.service.AgendaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AgendaController {

    @Autowired
    private AgendaService agendaService;

    @GetMapping("/agenda/index")
    public String index(){

        return "agenda/index";
    }

    @GetMapping("/agenda/create")
    public String create(Model model){
        Agenda agenda =new Agenda();
        model.addAttribute("agenda", agenda);
        return "agenda/create";
    }

    @PostMapping("/agenda/save")
    public String saveAgenda(@ModelAttribute("agenda")Agenda agenda){
        agendaService.createAgenda(agenda);
        return "redirect:/agenda/index";
    }
}
