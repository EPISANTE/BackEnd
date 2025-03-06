package Episante.back.Models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class Disponibilite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private JourSemaine jour;

    @Enumerated(EnumType.STRING)
    private Periode periode;

    private LocalDate date;
    private LocalDateTime dateHeure;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "medecin_id")
    private Medecin medecin;

    @OneToOne
    @JoinColumn(name = "rendez_vous_id")
    private RendezVous rendezVous;

    public Disponibilite(LocalDate date, JourSemaine jour, Periode periode, Medecin medecin) {

        this.jour = jour;
        this.periode = periode;
        this.medecin = medecin;
        this.date = date;
    }
}
