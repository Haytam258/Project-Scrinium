package com.mbc.clickclinic.service;

import com.mbc.clickclinic.entities.Conges;
import com.mbc.clickclinic.entities.Medecin;

import java.util.List;

public interface CongesService {

    public Conges createConges(Conges conges);
    public Conges getCongesById(int id);
    public List<Conges> getCongesByMedecin(Medecin medecin);
}
