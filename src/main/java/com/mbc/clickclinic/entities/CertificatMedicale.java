package com.mbc.clickclinic.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class CertificatMedicale {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate dateCreation;
    private String repos; //La raison pour le certificat

    @ManyToOne
    @JoinColumn(name = "patient_id")
    @JsonBackReference(value = "patient_certificat")
    private Patient patient;

    @ManyToOne
    @JoinColumn(name = "type_id")
    @JsonBackReference(value = "type_certificat")
    private TypeCertification typeCertification;

    @ManyToOne
    @JsonBackReference(value = "medecin_certificat")
    private Medecin medecin;
}
