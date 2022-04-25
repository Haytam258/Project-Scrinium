package com.mbc.clickclinic.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
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
    private Date date;
    private int nbrJours;
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
