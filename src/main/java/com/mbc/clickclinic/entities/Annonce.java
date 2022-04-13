package com.mbc.clickclinic.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Annonce {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String objet;
    private String message;
    private Date dateCreation;

}
