package Episante.back.Models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import lombok.Data;
@Data
@Entity
public class RendezVous {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;
        private LocalDateTime dateHeure;

        @ManyToOne
        @JoinColumn(name = "patient_id")
        private Patient patient;


}

