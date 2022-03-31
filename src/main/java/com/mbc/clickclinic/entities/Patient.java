package com.mbc.clickclinic.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Patient extends Personne{
    private String groupSanguin;
    private String maladiesPermanent;
}
