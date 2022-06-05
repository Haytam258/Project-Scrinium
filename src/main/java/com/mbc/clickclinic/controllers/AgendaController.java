package com.mbc.clickclinic.controllers;

import com.mbc.clickclinic.entities.Agenda;
import com.mbc.clickclinic.service.AgendaService;
import com.mbc.clickclinic.service.MedecinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AgendaController {


    private AgendaService agendaService;
    private MedecinService medecinService;

    @Autowired
    public AgendaController(AgendaService agendaService, MedecinService medecinService){
        this.medecinService = medecinService;
        this.agendaService = agendaService;
    }

    //Vaut mieux considérer que ce controller est spécifique au médecin, on obtiendra son id à partir de Spring Security.
    @GetMapping("/agenda/index")
    public String index(Model model){
        //model.addAttribute("agendaMedecin",Spring Security User Details id);
        model.addAttribute("agendaMedecin", agendaService.allAgenda());
        return "agenda/index";
    }

    //Dans medecinList, on aura un bouton qui mène vers cette adresse. Cet controller existe pour la secrétaire.
    @GetMapping("/agenda/index/{id}")
    public String indexAgendaMedecin(Model model, @PathVariable(value = "id") Integer id){
        model.addAttribute("agendaMedecin",agendaService.getAgendaByMedecin(medecinService.medecinById(id)));
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
