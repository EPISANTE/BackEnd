package Episante.back.Repository;

import Episante.back.Models.Medecin;
import Episante.back.Models.Patient;
import Episante.back.Models.RendezVous;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import java.time.LocalDateTime;
import java.util.List;

public interface RendezVousRepository extends JpaRepository<RendezVous, Long> {
    List<RendezVous> findByDateHeureBetween(LocalDateTime start, LocalDateTime end);

    List<RendezVous> findByPatientAndMedecin(Patient patient, Medecin medecin);
}
