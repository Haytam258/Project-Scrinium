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


    @ManyToOne
    @JoinColumn(name = "type_id")
    @JsonBackReference(value = "type_medicament")
    private TypeMedicament typeMedicament;

    @ManyToOne
    @JoinColumn(name = "categorie_id")
    @JsonBackReference(value = "categorie_medicament")
    private CategorieMedicament categorieMedicament;

}
