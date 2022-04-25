package com.mbc.clickclinic.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
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
public class Agenda {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date date;
    private String jour;
    private int heureDebut;
    private int heureFin;
    private String description;

    //Statut : 1 = Vacances, Fournisseurs etc (Pas disponible), Statut : 2 = Travail (Disponible),
    private int statut;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "medecin_id")
    @JsonBackReference(value = "medecin_agenda")
    private Medecin medecin;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Agenda agenda = (Agenda) o;
        return id != null && Objects.equals(id, agenda.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
