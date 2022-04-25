package com.mbc.clickclinic.dao;

import com.mbc.clickclinic.entities.Conges;
import com.mbc.clickclinic.entities.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CongeRepository extends JpaRepository<Conges,Long> {

    public List<Conges> findCongesByMedecin(Medecin medecin);
}
