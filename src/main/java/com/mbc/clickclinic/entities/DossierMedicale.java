package com.mbc.clickclinic.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
public class DossierMedicale {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String antecedent; //billan de maladie
    private String observations;

    @OneToMany(mappedBy = "dossierMedicale")
    @JsonManagedReference(value = "dossier_consultation")
    private List<Consultation> consultationList;

    public void add(Consultation consultation){
        if(this.consultationList == null){
            consultationList = new ArrayList<>();
        }
        consultationList.add(consultation);
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id")
    @JsonBackReference(value = "patient_dossier")
    private Patient patient;


}
