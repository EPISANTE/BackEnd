package Episante.back.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
public class RendezVous {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        private LocalDateTime dateHeure;

        @ManyToOne
        @JsonIgnore
        private Patient patient;

        @ManyToOne
        @JsonIgnoreProperties({"disponibilites", "rendezVous"})
        private Medecin medecin;

        @Enumerated(EnumType.STRING)
        private StatutRendezVous statut;

        @OneToOne
        @JoinColumn(name = "disponibilite_id")
        private Disponibilite disponibilite;
}