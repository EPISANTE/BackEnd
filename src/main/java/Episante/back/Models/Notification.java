package Episante.back.Models;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;
    private LocalDateTime dateCreation;

    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;



    @ManyToOne
    @JoinColumn(name = "rendezvous_id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private RendezVous rendezVous;

    public void setDateRdv(LocalDateTime dateHeure) {
    }
}