package com.mbc.clickclinic.dao;

import com.mbc.clickclinic.entities.Annonce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnonceRepository extends JpaRepository<Annonce,Integer> {
}
