package com.mbc.clickclinic.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Medecin extends Personne {
    private String specialite;
    private Date dateEntre;
}
