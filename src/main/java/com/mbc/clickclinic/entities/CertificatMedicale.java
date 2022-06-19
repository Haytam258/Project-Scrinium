package com.mbc.clickclinic.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class CertificatMedicale {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate dateCreation;
    @Lob
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

    @OneToOne
    @JsonManagedReference(value = "demande_certificat")
    private DemandeCertificat demandeCertificat;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CertificatMedicale that = (CertificatMedicale) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
