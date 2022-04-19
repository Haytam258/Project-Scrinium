package com.mbc.clickclinic.dao;

import com.mbc.clickclinic.entities.Agenda;
import com.mbc.clickclinic.entities.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgendaRepository extends JpaRepository<Agenda, Integer> {

    public List<Agenda> findAgendaByMedecin(Medecin medecin);
}
