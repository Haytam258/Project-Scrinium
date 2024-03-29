package com.mbc.clickclinic.dao;

import com.mbc.clickclinic.entities.Medecin;
import com.mbc.clickclinic.entities.Patient;
import com.mbc.clickclinic.entities.Rendezvous;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Repository
public interface RendezvousRepository extends JpaRepository<Rendezvous, Integer> {

    public List<Rendezvous> findRendezvousByDateRv(LocalDate rendezvous);
    public List<Rendezvous> findRendezvousByPatient(Patient patient);
    List<Rendezvous> findRendezvousByDateRvBetween(LocalDate date1, LocalDate date2);
    List<Rendezvous> findRendezvousByDateRvAndMedecin(LocalDate rendezvous, Medecin medecin);
    List<Rendezvous> findRendezvousByMedecin(Medecin medecin);
    List<Rendezvous> findRendezvousByDateRvAndStatut(LocalDate date, Integer statut);
    List<Rendezvous> findRendezvousByStatut(Integer statut);
    List<Rendezvous> findRendezvousByPatientAndStatut(Patient patient, Integer statut);
}
