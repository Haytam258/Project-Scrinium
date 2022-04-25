package com.mbc.clickclinic.dao;

import com.mbc.clickclinic.entities.SalleDattente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SalleDattentRepository extends JpaRepository<SalleDattente,Integer> {
}
