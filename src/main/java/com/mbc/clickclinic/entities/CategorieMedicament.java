package com.mbc.clickclinic.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class CategorieMedicament {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String classeTherapeutique;

    @OneToMany(mappedBy = "categorieMedicament")
    private List<Medicament> medicamentList;

    public void add(Medicament medicament){
        if(this.medicamentList == null){
            medicamentList = new ArrayList<>();
        }
        medicamentList.add(medicament);
    }
}
