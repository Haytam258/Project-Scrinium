package com.mbc.clickclinic.service;


import com.mbc.clickclinic.dao.PersonneRepository;
import com.mbc.clickclinic.entities.Patient;
import com.mbc.clickclinic.entities.Payment;
import com.mbc.clickclinic.entities.Personne;
import com.mbc.clickclinic.entities.Rendezvous;
import com.mbc.clickclinic.security.GeneralRole;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.*;

@Service
public class AdminImple implements AdminService{

    private final MedecinService medecinService;
    private final PatientService patientService;
    private final PaymentService paymentService;
    private final ConsultationService consultationService;
    private final PersonneRepository personneRepository;
    private final RendezvousService rendezvousService;

    public AdminImple(MedecinService medecinService, PatientService patientService, PaymentService paymentService, ConsultationService consultationService, PersonneRepository personneRepository, @Lazy RendezvousService rendezvousService){
        this.medecinService = medecinService;
        this.patientService = patientService;
        this.paymentService = paymentService;
        this.consultationService = consultationService;
        this.personneRepository = personneRepository;
        this.rendezvousService = rendezvousService;
    }


    public Integer getPatientCount(){
        return patientService.patients().size();
    }

    public List<Integer> getRendezvousCountByMonth(){
        List<Rendezvous> rendezvousList = rendezvousService.Rendezvouss();
        List<Integer> rendezCount = new ArrayList<>();
        for(int i = 0; i < 12; i++){
            int total = 0;
            for(Rendezvous rendezvous : rendezvousList){
                if(rendezvous.getDateRv().getMonth().getValue() == i+1){
                    total += 1;
                }
            }
            rendezCount.add(total);
        }
        return rendezCount;
    }

    public Integer getThisYearRemainingPayment(){
        List<Payment> payments = paymentService.payments();
        int total = 0;
        for(Payment payment : payments){
            if(payment.getDatePaiement().getYear() == LocalDate.now().getYear()){
                total += payment.getEquilibre();
            }
        }
        return total;
    }

    public Integer getRemainingPayment(){
        List<Payment> payments = paymentService.payments();
        int total = 0;
        for(Payment payment : payments){
            total += payment.getEquilibre();
        }
        return total;
    }


    public Integer getPaymentTotal(){
        List<Payment> payments = paymentService.payments();
        int total = 0;
        for (Payment payment : payments) {
            total += payment.getMontantDepose();
        }
        return total;
    }

    public List<String> patientGenderPercent(){
        List<Integer> genders = patientCountByGender();
        int total = patientService.patients().size();
        List<String> genderPercent = new ArrayList<>();
        String genderMale = "0%";
        String genderFemale = "0%";
        if(total != 0){
            int percentMale = genders.get(0)/total*100;
            int percentFemale = genders.get(1)/total*100;
            genderMale = String.valueOf(percentMale);
            genderFemale = String.valueOf(percentFemale);
        }
        genderPercent.add(genderMale);
        genderPercent.add(genderFemale);
        return genderPercent;
    }

    public List<Integer> patientCountByGender(){
        List<Patient> patientList = patientService.patients();
        List<Integer> genderCount = new ArrayList<>();
        int countMale = 0;
        int countFemale = 0;
        for(Patient patient : patientList){
            if(Objects.equals(patient.getSexe(), "Male")){
                countMale += 1;
            }
            if(Objects.equals(patient.getSexe(), "Female")){
                countFemale += 1;
            }
        }
        genderCount.add(countMale);
        genderCount.add(countFemale);
        return genderCount;
    }

    public Integer getThisYearTotalPayment(){
        List<Payment> payments = paymentService.payments();
        int total = 0;
        for(Payment payment : payments){
            if(payment.getDatePaiement().getYear() == LocalDate.now().getYear()){
                total += payment.getMontantDepose();
            }
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
