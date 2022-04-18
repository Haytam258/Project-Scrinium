package com.mbc.clickclinic.controllers;


import com.mbc.clickclinic.entities.Rendezvous;
import com.mbc.clickclinic.service.MedecinService;
import com.mbc.clickclinic.service.PatientService;
import com.mbc.clickclinic.service.RendezvousService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RendezvousRestController {

    private final RendezvousService rendezvousService;
    private final MedecinService medecinService;
    private final PatientService patientService;

    @Autowired
    public RendezvousRestController(RendezvousService rendezvousService, PatientService patientService, MedecinService medecinService){
        this.rendezvousService = rendezvousService;
        this.patientService = patientService;
        this.medecinService = medecinService;
    }

    @GetMapping("/allRendezvous")
    public List<Rendezvous> getAllRendezvous(){
        return rendezvousService.Rendezvouss();
    }

    @GetMapping("/allRendezvous/{id}")
    public Rendezvous getRendezvous(@PathVariable Long id){
        return rendezvousService.RendezvousById(id);
    }

    @PostMapping("/createRendezvous")
    public Rendezvous createRendezvous(@RequestBody Rendezvous rendezvous){
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
}
