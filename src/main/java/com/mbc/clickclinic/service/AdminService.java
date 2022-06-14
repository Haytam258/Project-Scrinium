package com.mbc.clickclinic.service;

import com.mbc.clickclinic.entities.Medecin;
import com.mbc.clickclinic.entities.Personne;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.List;

public interface AdminService {

    public HashMap<Integer, Integer> getPayementPerMonth();
    public Integer getPaymentTotal();
    public Integer getPatientCount();
    public Personne createAdmin(Personne personne, Model model);
    public List<Integer> getRendezvousCountByMonth();
    public Integer getThisYearTotalPayment();
    public List<Integer> patientCountByGender();
    public List<String> patientGenderPercent();
    public Integer getRemainingPayment();
    public Integer getThisYearRemainingPayment();
    public List<Integer> getPaymentPerMonthByMedecin(Medecin medecin);
    public Integer getThisYearPaymentByMedecin(Medecin medecin);
    public Integer getThisMonthPaymentByMedecin(Medecin medecin);
}
