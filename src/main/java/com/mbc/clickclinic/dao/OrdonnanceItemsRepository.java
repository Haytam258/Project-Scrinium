package com.mbc.clickclinic.dao;

import com.mbc.clickclinic.entities.OrdonnanceItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrdonnanceItemsRepository extends JpaRepository<OrdonnanceItems, Integer> {
}
