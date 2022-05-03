package com.mbc.clickclinic.service;

import com.mbc.clickclinic.dao.CategorieMedicamentRepository;
import com.mbc.clickclinic.dao.MedicamentRepository;
import com.mbc.clickclinic.dao.TypeMedicamentRepository;
import com.mbc.clickclinic.entities.CategorieMedicament;
import com.mbc.clickclinic.entities.Medicament;
import com.mbc.clickclinic.entities.TypeMedicament;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicamentImple implements MedicamentService{

    private final MedicamentRepository medicamentRepository;
    private final CategorieMedicamentRepository categorieMedicamentRepository;
    private final TypeMedicamentRepository typeMedicamentRepository;

    @Autowired
    public MedicamentImple(MedicamentRepository medicamentRepository, CategorieMedicamentRepository categorieMedicamentRepository, TypeMedicamentRepository typeMedicamentRepository){
        this.medicamentRepository = medicamentRepository;
        this.categorieMedicamentRepository = categorieMedicamentRepository;
        this.typeMedicamentRepository = typeMedicamentRepository;
    }

    public TypeMedicament saveType(TypeMedicament typeMedicament){
        return typeMedicamentRepository.saveAndFlush(typeMedicament);
    }

    public CategorieMedicament saveCategorie(CategorieMedicament categorieMedicament){
        return categorieMedicamentRepository.saveAndFlush(categorieMedicament);
    }

    public void deleteType(TypeMedicament typeMedicament){
        typeMedicamentRepository.delete(typeMedicament);
    }

    public void deleteCategorie(CategorieMedicament categorieMedicament){
        categorieMedicamentRepository.delete(categorieMedicament);
    }

    public List<TypeMedicament> typeMedicaments(){
        return typeMedicamentRepository.findAll();
    }

    public List<CategorieMedicament> categorieMedicaments(){
        return categorieMedicamentRepository.findAll();
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
    public Medicament MedicamentById(int id) {
        return medicamentRepository.findById(id).get();
    }

    @Override
    public Medicament MedicamentByCodeATC(String codeATC) {
        return medicamentRepository.findMedicamentByCodeATC(codeATC);
    }
}
