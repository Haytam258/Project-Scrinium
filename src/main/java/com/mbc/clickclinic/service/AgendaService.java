package com.mbc.clickclinic.service;

import com.mbc.clickclinic.entities.Agenda;
import com.mbc.clickclinic.entities.Medecin;

import java.util.List;

public interface AgendaService {

    public Agenda createAgenda(Agenda agenda);
    public Agenda getAgendaById(int id);
    public List<Agenda> getAgendaByMedecin(Medecin medecin);
}
