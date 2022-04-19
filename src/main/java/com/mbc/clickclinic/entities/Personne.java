package com.mbc.clickclinic.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Personne {
    @Id @GeneratedValue(strategy = GenerationType.TABLE)
    private int id;
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

}
