package com.mbc.clickclinic.service;


import com.mbc.clickclinic.entities.Patient;
import com.mbc.clickclinic.entities.Payment;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class AdminImple {

    private final MedecinService medecinService;
    private final PatientService patientService;
    private final PaymentService paymentService;
    private final ConsultationService consultationService;

    public AdminImple(MedecinService medecinService, PatientService patientService, PaymentService paymentService, ConsultationService consultationService){
        this.medecinService = medecinService;
        this.patientService = patientService;
        this.paymentService = paymentService;
        this.consultationService = consultationService;
    }


    public Integer getPatientCount(){
        return patientService.patients().size();
    }

   /* public List<Integer> getPatientCountByMonth(){
        List<Patient> patientList = patientService.patients();


    }*/

    public Integer getPaymentTotal(){
        List<Payment> payments = paymentService.payments();
        int total = 0;
        for (Payment payment : payments) {
            total += payment.getMontantDepose();
        }
        return total;
    }

    public Integer getConsultationTotal(){
        return consultationService.Consultations().size();
    }

}
