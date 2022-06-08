package com.mbc.clickclinic.controllers;


import com.mbc.clickclinic.entities.Rendezvous;
import com.mbc.clickclinic.service.ConsultationService;
import com.mbc.clickclinic.service.MedecinService;
import com.mbc.clickclinic.service.PatientService;
import com.mbc.clickclinic.service.RendezvousService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;

@Controller
//@RestController
public class RendezvousRestController {

    private final RendezvousService rendezvousService;
    private final MedecinService medecinService;
    private final PatientService patientService;
    private final ConsultationService consultationService;

    @Autowired
    public RendezvousRestController(RendezvousService rendezvousService, PatientService patientService, MedecinService medecinService, ConsultationService consultationService){
        this.rendezvousService = rendezvousService;
        this.patientService = patientService;
        this.medecinService = medecinService;
        this.consultationService = consultationService;
    }

    @GetMapping("/allRendezvous")
    public String getAllRendezvous(Model model){
        model.addAttribute("rendezvousList", rendezvousService.Rendezvouss());
        return "rendezvous/rendezvousList";
    }

    @GetMapping("/allRendezvous/{id}")
    public Rendezvous getRendezvous(@PathVariable Long id){
        return rendezvousService.RendezvousById(id.intValue());
    }

   @GetMapping("/createRendezvous")
    public String createRendezPage(Model model){
        model.addAttribute("rendezvous", new Rendezvous());
        model.addAttribute("medecinList", medecinService.medecins());
        model.addAttribute("patientList", patientService.patients());
        return "rendezvous/createRendezvous";
    }
    //Fonctions pour tester les dates, il y a un problème au niveau du transfer html, il faudra séparer la date et heure.
    @PostMapping("/createRendezvous")
    public String createRendezvous(Model model, @ModelAttribute Rendezvous rendezvous){
        LocalDate localDateTime = LocalDate.parse(rendezvous.getDateRv().toString());
        rendezvous.setDateRv(localDateTime);
        Rendezvous rendezvous1 = rendezvousService.saveRendezvous(rendezvous, model);
        if(rendezvous1 != null){
            model.addAttribute("rendezCreated", "rendez vous créé avec succès !");
        }
        model.addAttribute("medecinList", medecinService.medecins());
        model.addAttribute("patientList", patientService.patients());
        return "rendezvous/createRendezvous";
    }

    //Demander rendez vous pour les patients
    @GetMapping("/demanderRendezvous")
    public String demandeRendezPage(Model model){
        model.addAttribute("rendezvous", new Rendezvous());
        model.addAttribute("medecinList", medecinService.medecins());
        model.addAttribute("patientList", patientService.patients());
        return "rendezvous/demanderRendezvous";
    }

    @PostMapping("/demanderRendezvous")
    public String demandeRendezvous(Model model, @ModelAttribute Rendezvous rendezvous){
        LocalDate localDateTime = LocalDate.parse(rendezvous.getDateRv().toString());
        rendezvous.setDateRv(localDateTime);
        Rendezvous rendezvous1 = rendezvousService.saveRendezvous(rendezvous, model);
        if(rendezvous1 != null){
            model.addAttribute("rendezCreated", "rendez vous créé avec succès !");
            rendezvous.setStatut(2);
            rendezvousService.updateRendezvous(rendezvous);
        }
        model.addAttribute("medecinList", medecinService.medecins());
        model.addAttribute("patientList", patientService.patients());
        return "rendezvous/demanderRendezvous";
    }

    @GetMapping("/demandesRendezvous")
    public String demandesRendevous(Model model){
        model.addAttribute("demandes", rendezvousService.rendezvousByStatut(2));
        return "rendezvous/demandesRendezvous";
    }

    @GetMapping("/demandesRendezvous/accept/{id}")
    public String demandeAccepte(Model model, @PathVariable("id") Integer id){
        Rendezvous rendezvous = rendezvousService.RendezvousById(id);
        rendezvous.setStatut(0);
        rendezvousService.updateRendezvous(rendezvous);
        return "redirect:/demandesRendezvous";
    }


   /* @PostMapping("/createRendezvous")
    public Rendezvous createRendezvous(@RequestBody Rendezvous rendezvous){
        rendezvous.setHeure(rendezvous.getDateRv().getHour());
        return rendezvousService.saveRendezvous(rendezvous);
    }

    */

    @PostMapping("/updateRendezvous")
    public Rendezvous updateRendezvous(@RequestBody Rendezvous rendezvous){
        return rendezvousService.updateRendezvous(rendezvous);
    }

    @GetMapping("/deleteRendezvous/{id}")
    public String deleteRendezvous(@PathVariable Integer id){
        rendezvousService.deleteRendezvous(rendezvousService.RendezvousById(id));
        return "redirect:/allRendezvous";
    }

    @PostMapping("/rendezvous/medecin/")
    public Rendezvous addMedecinToRendezVous(@RequestParam Integer idr, @RequestParam Integer idm){
        return rendezvousService.AddMedecinToPatient(medecinService.medecinById(idm), rendezvousService.RendezvousById(idr));
    }

    @PostMapping("/rendezvous/patient/")
    public Rendezvous addPatientToRendezVous(@RequestParam Integer idr, @RequestParam Integer idp){
        return rendezvousService.AddPatientToRendezVous(patientService.PatientById(idp), rendezvousService.RendezvousById(idr));
    }

    @GetMapping("/rendezvous/patient/{id}")
    public Rendezvous getRendezvousByPatient(@PathVariable Long id){
        return rendezvousService.getRendezvousByPatient(patientService.PatientById(id.intValue()));
    }

    @PostMapping("/rendezvous/consultation")
    public Rendezvous addConsultationToRendezvous(@RequestParam Long idc, @RequestParam Long idr){
        return rendezvousService.AddConsultationToRendezVous(consultationService.ConsultationlById(idc.intValue()), rendezvousService.RendezvousById(idr.intValue()));
    }
}
