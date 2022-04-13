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
    private int id;
    //private List<Medicament> medicaments;
    private String posologie; //Nombre de géllule à prendre à la fois par exemple
    private int nbrFoisParJour;
    private String dureeTraitement;
    private int dose;
}
