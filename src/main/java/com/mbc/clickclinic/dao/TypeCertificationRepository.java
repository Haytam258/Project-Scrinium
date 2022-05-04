package com.mbc.clickclinic.dao;

import com.mbc.clickclinic.entities.TypeCertification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeCertificationRepository extends JpaRepository<TypeCertification, Integer> {
}
