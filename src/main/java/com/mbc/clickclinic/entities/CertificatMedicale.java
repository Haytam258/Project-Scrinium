package com.mbc.clickclinic.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class CertificatMedicale {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date dateCreation;
    private String repos;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    @JsonManagedReference
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "type_id")
    @JsonManagedReference
    private TypeCertification typeCertification;
}
