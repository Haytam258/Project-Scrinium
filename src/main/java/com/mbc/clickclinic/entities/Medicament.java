package com.mbc.clickclinic.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Medicament {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Medicament that = (Medicament) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
