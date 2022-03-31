package com.mbc.clickclinic.service;



import com.mbc.clickclinic.entities.Rapport;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface RapportService {
    Rapport saveRapport(Rapport rapport);
    void deleteRapport(Rapport rapport);
    Rapport updateRapport(Rapport rapport);
    List<Rapport> Rapports();
    Rapport RapportById(Long id);
}
