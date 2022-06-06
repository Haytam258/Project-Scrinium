package com.mbc.clickclinic.service;

import com.mbc.clickclinic.entities.Personne;
import org.springframework.ui.Model;

import java.util.HashMap;

public interface AdminService {

    public HashMap<Integer, Integer> getPayementPerMonth();
    public Integer getPaymentTotal();
    public Integer getPatientCount();
    public Personne createAdmin(Personne personne, Model model);
}
