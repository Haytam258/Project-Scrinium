package com.mbc.clickclinic.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
import java.util.TimeZone;


@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Agenda {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /*@JsonFormat
            (shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")*/
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateDebut;
    //ajout d'attribut dateFin => necessaire pour visualiser la fin du conges
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private LocalDate dateFin;
    private String jour;
    private String heureDebut;
    private String heureFin;
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
