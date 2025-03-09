package Episante.back.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
public class Disponibilite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date; // Ajout de la propriété date

    @Enumerated(EnumType.STRING)
    private JourSemaine jour;

    @Enumerated(EnumType.STRING)
    private Periode periode;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "medecin_id")
    private Medecin medecin;


    @OneToOne(mappedBy = "disponibilite", cascade = CascadeType.ALL, orphanRemoval = true)
    private RendezVous rendezVous;

    public Disponibilite(LocalDate date, JourSemaine jour, Periode periode, Medecin medecin) {
        this.date = date;
        this.jour = jour;
        this.periode = periode;
        this.medecin = medecin;
    }
}