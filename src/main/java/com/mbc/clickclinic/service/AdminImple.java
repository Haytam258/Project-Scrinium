package com.mbc.clickclinic.service;


import com.mbc.clickclinic.dao.PersonneRepository;
import com.mbc.clickclinic.entities.Patient;
import com.mbc.clickclinic.entities.Payment;
import com.mbc.clickclinic.entities.Personne;
import com.mbc.clickclinic.security.GeneralRole;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@Service
public class AdminImple implements AdminService{

    private final MedecinService medecinService;
    private final PatientService patientService;
    private final PaymentService paymentService;
    private final ConsultationService consultationService;
    private final PersonneRepository personneRepository;

    public AdminImple(MedecinService medecinService, PatientService patientService, PaymentService paymentService, ConsultationService consultationService, PersonneRepository personneRepository){
        this.medecinService = medecinService;
        this.patientService = patientService;
        this.paymentService = paymentService;
        this.consultationService = consultationService;
        this.personneRepository = personneRepository;
    }


    public Integer getPatientCount(){
        return patientService.patients().size();
    }


    public Integer getPaymentTotal(){
        List<Payment> payments = paymentService.payments();
        int total = 0;
        for (Payment payment : payments) {
            total += payment.getMontantDepose();
        }
        return total;
    }
    //On peut ajouter mois à l'entité Payment afin d'appeler directement Repository ByMonth et faire le calcul, ça peut optimiser la vitesse.
    public HashMap<Integer, Integer> getPayementPerMonth(){
        List<Payment> payments = paymentService.payments();
        HashMap<Integer, Integer> totalPerMonth = new HashMap<>();
        totalPerMonth.put(1,0);
        for(int i = 1; i < 13; i++){
            int total = 0;
            totalPerMonth.put(i,0);
            for(Payment payment: payments){
                if(payment.getDatePaiement().getMonth().getValue() == i){
                    total += payment.getMontantDepose();
                    totalPerMonth.put(i,total);
                }
            }
        }
        return totalPerMonth;

    }

    public Integer getConsultationTotal(){
        return consultationService.Consultations().size();
    }

    public Personne createAdmin(Personne personne, Model model){
        personne.setRole(GeneralRole.ADMIN.getRole());
        if(personneRepository.findPersonneByEmail(personne.getEmail()) != null){
            model.addAttribute("emailAdminExist", "Cet email est déjà utilisé par un autre Admin !");
            return null;
        }
        return personneRepository.saveAndFlush(personne);
    }

}
