package com.mbc.clickclinic.service;



import com.mbc.clickclinic.entities.Medicament;
import com.mbc.clickclinic.entities.Ordonnance;
import org.springframework.stereotype.Service;
import java.util.List;


public interface OrdonnanceService {
    Ordonnance saveOrdonnance(Ordonnance ordonnance);
    void deleteOrdonnance(Ordonnance ordonnance);
    Ordonnance updateOrdonnance(Ordonnance ordonnance);
    List<Ordonnance> Ordonnances();
    Ordonnance OrdonnanceById(Long id);
    public Ordonnance addMedicamentToOrdonnance(Medicament medicament, Ordonnance ordonnance);
}
