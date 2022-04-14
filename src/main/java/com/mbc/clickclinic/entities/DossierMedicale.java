package com.mbc.clickclinic.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class DossierMedicale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String antecedent; //billan de maladie
    private String observations;

    @OneToMany(mappedBy = "dossierMedicale")
    @JsonBackReference
    private List<Consultation> consultationList;

    public void add(Consultation consultation){
        if(this.consultationList == null){
            consultationList = new ArrayList<>();
        }
        consultationList.add(consultation);
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id")
    private Patient patient;


}
