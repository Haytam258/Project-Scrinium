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
    private String nom;
    private String presentation;
    private int dosage;
    private String form;
    private String composition;
    private String classeTherapeutique;
    private String fabriquant;
    private String statut;
    private String codeATC;
    private double ppv;
    private double ph;
    private String indication;
}
