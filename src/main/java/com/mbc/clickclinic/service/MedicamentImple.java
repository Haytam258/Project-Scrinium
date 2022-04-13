package com.mbc.clickclinic.service;

import com.mbc.clickclinic.dao.MedicamentRepository;
import com.mbc.clickclinic.entities.Medicament;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class MedicamentImple implements MedicamentService{

    private final MedicamentRepository medicamentRepository;

    @Autowired
    public MedicamentImple(MedicamentRepository medicamentRepository){
        this.medicamentRepository = medicamentRepository;
    }
    @Override
    public Medicament saveMedicament(Medicament medicament) {
        return medicamentRepository.saveAndFlush(medicament);
    }

    @Override
    public void deleteMedicament(Medicament medicament) {
        medicamentRepository.delete(medicament);
    }

    @Override
    public Medicament updateMedicament(Medicament medicament) {
        return null;
    }

    @Override
    public Medicament findMedicamentBykw(String kw) {
        return null;
    }

    @Override
    public Medicament findMedicamentByNom(String libelle) {
        return medicamentRepository.findMedicamentByLibelle(libelle);
    }

    @Override
    public List<Medicament> Medicaments() {
        return medicamentRepository.findAll();
    }

    @Override
    public Medicament MedicamentById(Long id) {
        return medicamentRepository.findById(id).get();
    }

    @Override
    public Medicament MedicamentByCodeATC(String codeATC) {
        return medicamentRepository.findMedicamentByCodeATC(codeATC);
    }
}
