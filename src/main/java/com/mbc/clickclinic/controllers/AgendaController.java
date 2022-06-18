package com.mbc.clickclinic.controllers;

import com.mbc.clickclinic.entities.Agenda;
import com.mbc.clickclinic.entities.Conges;
import com.mbc.clickclinic.service.AgendaService;
import com.mbc.clickclinic.service.CongesService;
import com.mbc.clickclinic.service.MedecinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.temporal.ChronoUnit;

@Controller
public class AgendaController {


    private final AgendaService agendaService;
    private final MedecinService medecinService;
    private final CongesService congesService;

    @Autowired
    public AgendaController(AgendaService agendaService, MedecinService medecinService, CongesService congesService){
        this.medecinService = medecinService;
        this.agendaService = agendaService;
        this.congesService = congesService;
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
        model.addAttribute("medecins", medecinService.medecins());
        return "agenda/create";
    }

    @PostMapping("/agenda/save")
    public String saveAgenda(@ModelAttribute("agenda")Agenda agenda){
        agenda.setStatut(1);
        agendaService.createAgenda(agenda);
        return "redirect:/agenda/index";
    }

    @GetMapping("/agenda/delete/{id}")
    public String deleteAgenda(@PathVariable(value = "id")Integer id, Model model){
        agendaService.deleteAgenda(agendaService.getAgendaById(id));
        return "redirect:/agenda/index";
    }

    @GetMapping("/agenda/modify/{id}")
    public String modifyAgenda(@PathVariable(value = "id") Integer id, Model model){
        model.addAttribute("agenda", agendaService.getAgendaById(id));
        return "agenda/modifyAgenda";
    }

    @PostMapping("/modifyAgenda")
    public String modifyAgenda(@ModelAttribute("agenda")Agenda agenda, Model model){
        if(agendaService.createAgenda(agenda) == null){
            model.addAttribute("modificationFailed","La modification a échouée, vérifiez les informations saisies !");
            return "agenda/modifyAgenda";
        }
        Conges conges = agenda.getConges();
        if(conges != null){
            conges.setDate(agenda.getDateDebut());
            conges.setNbrJours((int) ChronoUnit.DAYS.between(agenda.getDateDebut(), agenda.getDateFin()));
            congesService.updateConges(conges);
        }
        return "redirect:/agenda/index";
    }
}
