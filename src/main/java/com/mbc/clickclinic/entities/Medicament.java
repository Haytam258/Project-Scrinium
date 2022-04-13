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
public class Medicament {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String libelle;
    private String form; // Le type de médicament
    private String classeTherapeutique; // La catégorie de médicament
    private String fabriquant;
    private String codeATC;
    private String nomGenerique;
    private int prixAchat; //ppv

}
