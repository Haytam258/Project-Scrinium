package com.mbc.clickclinic.dao;

import com.mbc.clickclinic.entities.Rendezvous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface RendezvousRepository extends JpaRepository<Rendezvous,Long> {

    public Rendezvous findRendezvousByDateRv(Date rendezvous);
}
