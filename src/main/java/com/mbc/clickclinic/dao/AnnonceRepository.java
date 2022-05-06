package com.mbc.clickclinic.dao;

import com.mbc.clickclinic.entities.Annonce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AnnonceRepository extends JpaRepository<Annonce,Integer> {

    List<Annonce> findAnnoncesByDateCreationBetween(LocalDate date1, LocalDate date2);
}
