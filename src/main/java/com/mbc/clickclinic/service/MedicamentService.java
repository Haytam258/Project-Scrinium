package com.mbc.clickclinic.service;


import com.mbc.clickclinic.entities.Medicament;
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
}
