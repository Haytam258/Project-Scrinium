package com.mbc.clickclinic.entities;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Personne {
    @Id @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;
    private String nom;
    private String prenom;
    private String cin;
    private LocalDate dateNaissance;
    private String mobil;
    private String email;
    private String etatCivile; //Célibataire, Mariée etc
    private String assurance;
    private String addresse;
    private String region; //Ville
    private String role;
    private String sexe;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Personne personne = (Personne) o;
        return id != null && Objects.equals(id, personne.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
