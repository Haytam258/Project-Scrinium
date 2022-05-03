package com.mbc.clickclinic.service;


import com.mbc.clickclinic.entities.CategorieMedicament;
import com.mbc.clickclinic.entities.Medicament;
import com.mbc.clickclinic.entities.TypeMedicament;
import org.springframework.stereotype.Service;
import java.util.List;


public interface MedicamentService {
    Medicament saveMedicament(Medicament medicament);
    void deleteMedicament(Medicament medicament);
    Medicament updateMedicament(Medicament medicament);
    Medicament findMedicamentBykw(String kw);
    Medicament findMedicamentByNom(String Nom);
    List<Medicament> Medicaments();
    Medicament MedicamentById(int id);
    Medicament MedicamentByCodeATC(String codeATC);
    TypeMedicament saveType(TypeMedicament typeMedicament);
    CategorieMedicament saveCategorie(CategorieMedicament categorieMedicament);
    void deleteType(TypeMedicament typeMedicament);
    void deleteCategorie(CategorieMedicament categorieMedicament);
    List<TypeMedicament> typeMedicaments();
    List<CategorieMedicament> categorieMedicaments();
}
