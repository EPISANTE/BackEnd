package Episante.back.Models;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
@Data
@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date; // Date de la notification

    @ManyToOne
    @JoinColumn(name = "rendezVous_id", nullable = false)
    private RendezVous rendezVous;

}
