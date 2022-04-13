package com.mbc.clickclinic.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class SalleDattente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

   // @OneToMany
   // private Patient patient;

    private String nomMedecin;
    private String heure; // Look for hour datetime
}
