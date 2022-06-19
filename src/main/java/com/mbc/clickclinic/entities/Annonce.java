package com.mbc.clickclinic.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Annonce {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String objet;
    private String message;
    private LocalDate dateCreation;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Annonce annonce = (Annonce) o;
        return id != null && Objects.equals(id, annonce.id);
    }

    @OneToOne
    @JoinColumn(name = "patient_id")
    @JsonManagedReference("annonce_patient")
    private Patient patient;

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
