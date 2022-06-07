package com.mbc.clickclinic.service;

import com.mbc.clickclinic.entities.Conges;
import com.mbc.clickclinic.entities.Medecin;

import java.util.List;

public interface CongesService {

    public List<Conges> getAllConges();
    public Conges createConges(Conges conges, Medecin medecin);
    public Conges getCongesById(int id);
    public List<Conges> getCongesByMedecin(Medecin medecin);
    public Conges acceptConges(int id);
    public Conges refuseConges(int id);

}
