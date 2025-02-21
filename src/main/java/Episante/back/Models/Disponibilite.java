package Episante.back.Models;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import lombok.*;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Disponibilite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private JourSemaine jour;

    @Enumerated(EnumType.STRING)
    private Periode periode;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "medecin_id")
    private Medecin medecin;

 public Disponibilite(JourSemaine jour, Periode periode, Medecin medecin) {
        this.jour = jour;
        this.periode = periode;
        this.medecin = medecin;
    }
}
