package Episante.back.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
public class Disponibilite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateHeure;

    @Enumerated(EnumType.STRING)
    private JourSemaine jour;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "medecin_id")
    private Medecin medecin;

    @OneToOne(mappedBy = "disponibilite", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private RendezVous rendezVous;

    public Disponibilite(LocalDateTime dateHeure, JourSemaine jour, Medecin medecin) {
        this.dateHeure = dateHeure;
        this.jour = jour;
        this.medecin = medecin;
    }

    @Transient
    public boolean isReserve() {
        return this.rendezVous != null;
    }
}