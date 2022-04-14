package com.mbc.clickclinic.entities;

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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "medicament_ordonance",
    joinColumns = @JoinColumn(name = "ordonnance_id"),
    inverseJoinColumns = @JoinColumn(name = "medicament_id"))
    private List<Ordonnance> ordonnanceList;

    public void add(Ordonnance ordonnance){
        if(this.ordonnanceList == null){
            ordonnanceList = new ArrayList<>();
        }
        ordonnanceList.add(ordonnance);
    }

    @ManyToOne
    @JoinColumn(name = "type_id")
    private TypeMedicament typeMedicament;

    @ManyToOne
    @JoinColumn(name = "categorie_id")
    private CategorieMedicament categorieMedicament;

}
