package Episante.back.Repository;
import Episante.back.Models.Disponibilite;
import Episante.back.Models.JourSemaine;
import Episante.back.Models.Medecin;
import Episante.back.Models.Periode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;



public interface DisponibiliteRepository extends JpaRepository<Disponibilite, Long> {


        List<Disponibilite> findByMedecinId(Long medecinId);

        List<Disponibilite> findByJour(JourSemaine jour);

        List<Disponibilite> findByMedecinIdAndJour(Long medecinId, JourSemaine jour);


    Collection<Object> findByMedecinAndDateAndPeriode(Medecin medecin, LocalDate date, Periode periode);
}



