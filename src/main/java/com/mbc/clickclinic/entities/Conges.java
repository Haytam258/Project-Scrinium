package com.mbc.clickclinic.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Conges {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Integer id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
    private int nbrJours;
    //Si 0 alors non acceptée, si 1 alors acceptée
    private boolean reponse;

    @ManyToOne
    @JoinColumn(name = "medecin_id")
    @JsonBackReference(value = "conges_medecin")
    private Medecin medecin;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Conges conges = (Conges) o;
        return id != null && Objects.equals(id, conges.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
