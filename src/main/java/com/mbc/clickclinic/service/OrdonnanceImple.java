package com.mbc.clickclinic.service;

import com.mbc.clickclinic.dao.OrdonnanceRepository;
import com.mbc.clickclinic.entities.Medicament;
import com.mbc.clickclinic.entities.Ordonnance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdonnanceImple implements OrdonnanceService{

    private final OrdonnanceRepository ordonnanceRepository;

    private final MedicamentService medicamentService;

    @Autowired
    public OrdonnanceImple(OrdonnanceRepository ordonnanceRepository, MedicamentService medicamentService){
        this.ordonnanceRepository = ordonnanceRepository;
        this.medicamentService = medicamentService;
    }

    @Override
    public Ordonnance saveOrdonnance(Ordonnance ordonnance) {
        return ordonnanceRepository.saveAndFlush(ordonnance);
    }

    @Override
    public void deleteOrdonnance(Ordonnance ordonnance) {
        ordonnanceRepository.delete(ordonnance);
    }

    @Override
    public Ordonnance updateOrdonnance(Ordonnance ordonnance) {
        return null;
    }

    @Override
    public List<Ordonnance> Ordonnances() {
        return ordonnanceRepository.findAll();
    }

    @Override
    public Ordonnance OrdonnanceById(int id) {
        return ordonnanceRepository.findById(id).get();
    }

    public Ordonnance addMedicamentToOrdonnance(Medicament medicament, Ordonnance ordonnance){
        if(ordonnance.getMedicamentList().contains(medicament)){
            return ordonnance;
        }
        else {
            ordonnance.getMedicamentList().add(medicament);
            return ordonnanceRepository.saveAndFlush(ordonnance);
        }
    }
}
