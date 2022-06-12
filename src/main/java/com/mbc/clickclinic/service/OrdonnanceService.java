package com.mbc.clickclinic.service;



import com.mbc.clickclinic.entities.Medicament;
import com.mbc.clickclinic.entities.Ordonnance;
import com.mbc.clickclinic.entities.OrdonnanceItems;
import org.springframework.stereotype.Service;
import java.util.List;


public interface OrdonnanceService {
    Ordonnance saveOrdonnance(Ordonnance ordonnance);
    void deleteOrdonnance(Ordonnance ordonnance);
    Ordonnance updateOrdonnance(Ordonnance ordonnance);
    List<Ordonnance> Ordonnances();
    Ordonnance OrdonnanceById(int id);
    public OrdonnanceItems saveOrdonnanceItems(OrdonnanceItems ordonnanceItems);
   // public Ordonnance addMedicamentToOrdonnance(Medicament medicament, Ordonnance ordonnance);
}
