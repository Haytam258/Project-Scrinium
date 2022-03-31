package com.mbc.clickclinic.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Ordonnance {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    //private List<Medicament> medicaments;
    private String posologie;
    private int nbrUnite;
    private int duree;
}
