package Episante.back.Repository;

import Episante.back.Models.Disponibilite;
import Episante.back.Models.Medecin;
import Episante.back.Models.Specialite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MedecinRepository extends JpaRepository<Medecin, Long> {


    List<Medecin> findBySpecialite(Specialite specialite);


}