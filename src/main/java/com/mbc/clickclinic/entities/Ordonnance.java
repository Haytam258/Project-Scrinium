package com.mbc.clickclinic.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Ordonnance {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    //private List<Medicament> medicaments;
    private String posologie; //Nombre de géllule à prendre à la fois par exemple
    private int nbrFoisParJour;
    private String dureeTraitement;
    private int dose;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "medicament_ordonance",
    joinColumns = @JoinColumn(name = "ordonnance_id"),
    inverseJoinColumns = @JoinColumn(name = "medicament_id"))
    private List<Medicament> medicamentList;

    public void add(Medicament medicament){
        if(this.medicamentList == null ){
            medicamentList = new ArrayList<>();
        }
        medicamentList.add(medicament);
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "consultation_id")
    @JsonBackReference(value = "consultation_ordonnance")
    private Consultation consultation;
}
