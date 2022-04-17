package com.mbc.clickclinic.entities;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class TypeMedicament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String form;

    @OneToMany(mappedBy = "typeMedicament")
    @JsonManagedReference(value = "type_medicament")
    private List<Medicament> medicamentList;

    public void add(Medicament medicament){
        if(this.medicamentList == null){
            medicamentList = new ArrayList<>();
        }
        medicamentList.add(medicament);
    }
}
