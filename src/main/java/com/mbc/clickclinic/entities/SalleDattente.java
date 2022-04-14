package com.mbc.clickclinic.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class SalleDattente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   // @OneToMany
   // private Patient patient;

    private String nomMedecin;
    private String heure; // Look for hour datetime

    @OneToMany(mappedBy = "salleDattente")
    @JsonBackReference
    private List<Patient> patientList;

    public void add(Patient patient){
        if(this.patientList == null){
            patientList = new ArrayList<>();
        }
        patientList.add(patient);
    }
}
