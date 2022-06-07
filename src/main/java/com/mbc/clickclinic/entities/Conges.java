package com.mbc.clickclinic.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
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

    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private LocalDate date;

    private int nbrJours;
    // Acceptee - Refusee - Default-value: En cours
    private String reponse;

    @ManyToOne
    @JoinColumn(name = "medecin_id")
    //@JsonBackReference(value = "conges_medecin")
    private Medecin medecin;

    @OneToOne(cascade = CascadeType.ALL)
    @JsonBackReference("agenda_conges")
    private Agenda agenda;

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
