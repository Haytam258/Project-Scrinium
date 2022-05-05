package com.mbc.clickclinic.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Consultation {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id ;
    private String motif; //Raison de la visite
    @Lob
    private String diagnostique;
    @Lob
    private String ResultatExmentClinique; //ResultatsDanalyses
    @Lob
    private String remarques;
    private double poids;
    private double talle;
    private double imc;
    private double temperature;
    private int frequenceCardiaque;
    private String pressionArterielle;

    @ManyToOne
    @JoinColumn(name = "dossier_id")
    @JsonBackReference(value = "dossier_consultation")
    private DossierMedicale dossierMedicale;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name ="paiement_id")
    @JsonManagedReference(value = "consultation_payment")
    private Payment payment;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "rendez_id")
    @JsonManagedReference(value = "consultation_rendez")
    private Rendezvous rendezvous;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ordonnance_id")
    @JsonManagedReference(value = "consultation_ordonnance")
    private Ordonnance ordonnance;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Consultation that = (Consultation) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
