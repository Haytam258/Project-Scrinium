package com.mbc.clickclinic.dao;

import com.mbc.clickclinic.entities.Ordonnance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdonnanceRepository extends JpaRepository<Ordonnance,Integer> {
}
