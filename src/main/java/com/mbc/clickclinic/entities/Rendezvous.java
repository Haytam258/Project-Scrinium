package com.mbc.clickclinic.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor

//On utilise JsonIdentityInfo si on veut avoir une bi-directionnelle relation et voir les entités dans le JSON renvoyé par Postman
//Si on ne veut pas, le BackReference marchera toujours, on ne va tout simplement pas voir sur Rendezvous le medecin et le patient, mais ils existent toujours dans l'objet
//rendezvous.
public class Rendezvous {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    //Pour HTML, DateTimeFormat est yyyy-MM-dd et on va travailler avec localtime et localdate, ça marche en base de données
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm", iso = DateTimeFormat.ISO.DATE_TIME)
    //@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
    //private LocalDateTime dateRv;
    private LocalDate dateRv;
    @DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
    private LocalTime heure;
    //private int heure;
    private int statut; //Statut : 1 = Fini, Statut : 0 = A faire, Statut : 2 = Demande de rendez vous

    @ManyToOne
    @JoinColumn(name = "medecin_id")
    @JsonBackReference(value = "medecin_rendez")
    //@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Medecin.class)
    private Medecin medecin;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    @JsonBackReference(value = "patient_rendez")
    //@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Patient.class)
    private Patient patient;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "consultation_id")
    @JsonBackReference(value = "consultation_rendez")
    //@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id", scope = Consultation.class)
    private Consultation consultation;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Rendezvous that = (Rendezvous) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
