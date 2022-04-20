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


import java.time.LocalDateTime;
import java.util.List;

//@Controller
@RestController
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
    public List<Rendezvous> getAllRendezvous(){
        return rendezvousService.Rendezvouss();
    }

    @GetMapping("/allRendezvous/{id}")
    public Rendezvous getRendezvous(@PathVariable Long id){
        return rendezvousService.RendezvousById(id);
    }

   /* @GetMapping("/createRendezvous")
    public String createRendezPage(Model model){
        model.addAttribute("rendezvous", new Rendezvous());
        return "rendezvoustest";
    }
    Fonctions pour tester les dates, il y a un problème au niveau du transfer html, il faudra séparer la date et heure.
    @PostMapping("/createRendezvous")
    public String createRendezvous(Model model, @ModelAttribute Rendezvous rendezvous){
        LocalDateTime localDateTime = LocalDateTime.parse(rendezvous.getDateRv().toString());
        rendezvous.setDateRv(localDateTime);
        rendezvousService.saveRendezvous(rendezvous);
        return "rendezvoustest";
    }
    */

    @PostMapping("/createRendezvous")
    public Rendezvous createRendezvous(@RequestBody Rendezvous rendezvous){
        rendezvous.setHeure(rendezvous.getDateRv().getHour());
        return rendezvousService.saveRendezvous(rendezvous);
    }

    @PostMapping("/updateRendezvous")
    public Rendezvous updateRendezvous(@RequestBody Rendezvous rendezvous){
        return rendezvousService.updateRendezvous(rendezvous);
    }

    @PostMapping("/deleteRendezvous/{id}")
    public void deleteRendezvous(@PathVariable Long id){
        rendezvousService.deleteRendezvous(rendezvousService.RendezvousById(id));
    }

    @PostMapping("/rendezvous/medecin/")
    public Rendezvous addMedecinToRendezVous(@RequestParam Integer idr, @RequestParam Integer idm){
        return rendezvousService.AddMedecinToPatient(medecinService.medecinById(idm.longValue()), rendezvousService.RendezvousById(idr.longValue()));
    }

    @PostMapping("/rendezvous/patient/")
    public Rendezvous addPatientToRendezVous(@RequestParam Integer idr, @RequestParam Integer idp){
        return rendezvousService.AddPatientToRendezVous(patientService.PatientById(idp), rendezvousService.RendezvousById(idr.longValue()));
    }

    @GetMapping("/rendezvous/patient/{id}")
    public Rendezvous getRendezvousByPatient(@PathVariable Long id){
        return rendezvousService.getRendezvousByPatient(patientService.PatientById(id.intValue()));
    }

    @PostMapping("/rendezvous/consultation")
    public Rendezvous addConsultationToRendezvous(@RequestParam Long idc, @RequestParam Long idr){
        return rendezvousService.AddConsultationToRendezVous(consultationService.ConsultationlById(idc), rendezvousService.RendezvousById(idr));
    }
}
