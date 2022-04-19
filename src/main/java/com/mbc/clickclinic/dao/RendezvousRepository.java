package com.mbc.clickclinic.dao;

import com.mbc.clickclinic.entities.Patient;
import com.mbc.clickclinic.entities.Rendezvous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Repository
public interface RendezvousRepository extends JpaRepository<Rendezvous,Long> {

    public List<Rendezvous> findRendezvousByDateRv(LocalDateTime rendezvous);
    public Rendezvous findRendezvousByPatient(Patient patient);
}
